package io.yangbob.flutterplugin.common

import com.intellij.psi.PsiDirectory


object Utils {
    fun createDir(dirName: String, directory: PsiDirectory): PsiDirectory {
        return if (dirName.isBlank()) directory
        else {
            directory.findSubdirectory(dirName) ?: directory.createSubdirectory(dirName)

        }
    }
}