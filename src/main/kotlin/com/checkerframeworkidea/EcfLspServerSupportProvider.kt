package com.checkerframeworkidea
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider;

class EcfLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        TODO("Not yet implemented")
    }
}