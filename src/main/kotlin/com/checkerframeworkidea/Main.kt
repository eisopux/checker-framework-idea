import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

const val REPO = "eisopux/checker-framework-languageserver-downloader"
const val API_URL = "https://api.github.com/repos/$REPO/releases/latest"
const val JAR = "./checker-framework-languageserver-downloader.jar"

fun downloadFile(url: String, destination: String, callback: () -> Unit) {
    val connection = URL(url).openConnection() as HttpURLConnection

    try {
        if (connection.responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
            val redirectUrl = connection.getHeaderField("Location")
            downloadFile(redirectUrl, destination, callback)
            return
        }

        val input = connection.inputStream
        val output = FileOutputStream(File(destination))

        input.use { input ->
            output.use { output ->
                input.copyTo(output)
            }
        }

        callback()
    } finally {
        connection.disconnect()
    }
}

fun main() {
    val apiUrl = URL(API_URL)
    apiUrl.openConnection().apply {
        setRequestProperty("User-Agent", "Kotlin")
    }.getInputStream().use { input ->
        val data = input.bufferedReader().use { it.readText() }
        val jsonResponse = Json.parseToJsonElement(data)
        val downloadUrl = jsonResponse
            .jsonObject
            .get("assets")
            ?.jsonArray
            ?.first()
            ?.jsonObject
            ?.get("browser_download_url")
            ?.jsonPrimitive
            ?.content

        if (downloadUrl != null) {
            downloadFile(downloadUrl, JAR) {
                println("Downloaded the latest release to $JAR.")
            }
        }
    }
}
