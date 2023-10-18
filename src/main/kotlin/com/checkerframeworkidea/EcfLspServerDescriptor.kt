package com.checkerframeworkidea
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import java.io.File

class EcfLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "ECF") {
    override fun createCommandLine(): GeneralCommandLine {

        val __dirname = System.getProperty("user.dir")
        val serverFile = File(
            File(__dirname).parent,
            "download/checker-framework-languageserver-0.2.0-all.jar"
        )
        val checkerFile = File(
            File(__dirname).parent,
            "download/checker-framework-3.34.0-eisop1/checker/dist/checker.jar"
        )

        val serverInstalled = serverFile.exists()
        val checkerInstalled = checkerFile.exists()

        if (!checkerInstalled || !serverInstalled) {
            downloadDeps()
        } else {
            println("good to go")
        }

        val commandLine = GeneralCommandLine()
        commandLine.withExePath("java")

        // Add classpath and server main class
        val classpath = "${serverFile.absolutePath}:${checkerFile.absolutePath}"
        commandLine.addParameters("-cp", classpath)
        commandLine.addParameters("org.checkerframework.languageserver.ServerMain")

        val checkerFrameworkPath = File(
            File(__dirname).parent,
            "download/checker-framework-3.34.0-eisop1"
        ).absolutePath

        println(
            "classpath: $classpath\n"+
            "cf path: $checkerFrameworkPath"
        )

        // Add frameworkPath, checkers, and command line options
        commandLine.addParameters(
            "--frameworkPath",
            checkerFrameworkPath,
            "--checkers",
            "org.checkerframework.checker.nullness.NullnessChecker",
        )
        println(commandLine.getCommandLineString())
        return commandLine
    }

    override fun isSupportedFile(file: VirtualFile): Boolean {
        return file.extension == "java"
    }

    fun downloadDeps() {

        var __dirname : String = System.getProperty("user.dir")

        val jarPath = File(
            File(__dirname).parent,
            "checker-framework-languageserver-downloader.jar"
        ).absolutePath

        val destPath = File(
            File(__dirname).parent,
            "download"
        ).absolutePath

        val args = listOf(
            "java",
            "-jar",
            jarPath,
            "-dest",
            destPath
        )

        println("the downloader " + if (File(jarPath).exists()) "exists" else "does not exist")

        println("spawning downloader, args: $args")
        val processBuilder = ProcessBuilder(args).start()

        processBuilder.inputReader().lines().forEach {
            println(it)
        }
        val exitCode = processBuilder.waitFor()
        println("exit code $exitCode")
        TODO("make download automatically")
    }
}