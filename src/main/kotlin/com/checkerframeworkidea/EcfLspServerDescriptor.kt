package com.checkerframeworkidea
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor

class EcfLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "ECF") {
    override fun createCommandLine(): GeneralCommandLine {
        TODO("Not yet implemented")
    }

    override fun isSupportedFile(file: VirtualFile): Boolean {
        TODO("Not yet implemented")
    }

}