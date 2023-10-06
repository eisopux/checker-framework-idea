package com.checkerframeworkidea
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import java.io.File

class EcfLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "ECF") {
    override fun createCommandLine(): GeneralCommandLine {

        var serverInstalled = false
        var checkerInstalled = false

        if (TODO("the server exists")) {
            serverInstalled = true
        }
        if (TODO("the checker exists")) {
            checkerInstalled = true
        }

        if (!checkerInstalled || !serverInstalled) {
            downloadDeps()
        }

        val commandLine = GeneralCommandLine()
        commandLine.withExePath("java")

        // Add classpath and server main class
        val classpath = "/Users/ocen/ura-zain/checker-framework-idea/checker-framework-languageserver-0.2.0-all.jar:/Users/ocen/ura-zain/checker-framework-idea/checker-framework-3.34.0-eisop1/checker/dist/checker.jar"
        commandLine.addParameters("-cp", classpath)
        commandLine.addParameters("org.checkerframework.languageserver.ServerMain")

        // Add frameworkPath, checkers, and command line options
        commandLine.addParameters(
            "--frameworkPath",
            "/Users/ocen/ura-zain/checker-framework-idea/checker-framework-3.34.0-eisop1",
            "--checkers",
            "org.checkerframework.checker.nullness.NullnessChecker",
        )
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

        println("spawning downloader, args: $args")
        val processBuilder = ProcessBuilder(args)
        TODO("make download automatically")
    }
}