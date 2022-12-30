package io.yangbob.flutterplugin.action

import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.fileTypes.PlainTextLanguage
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFileFactory
import io.yangbob.flutterplugin.generator.BasePageGenerator
import io.yangbob.flutterplugin.generator.PageGeneratorFactory

class GeneratePage : AnAction(), GeneratePageDialog.Listener {
    private lateinit var dataContext: DataContext

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = GeneratePageDialog(this)
        dialog.show()
    }

    override fun onGeneratePageClicked(pageName: String?, shouldUseBaseBloc: Boolean) {
        pageName?.let { name ->
            val generators = PageGeneratorFactory.getPageGenerators(name, shouldUseBaseBloc)
            generate(name, generators)
        }
    }

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }

    private fun generate(name: String, mainSourceGenerators: List<BasePageGenerator>) {
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
        val directory = view?.orChooseDirectory
        if (project == null || directory == null) return

        val baseDir = createDir(name.toLowerSnakeCase(), directory)
        ApplicationManager.getApplication().runWriteAction {
            CommandProcessor.getInstance().executeCommand(
                project, {
                    mainSourceGenerators.forEach { createSourceFile(project, it, baseDir) }
                }, "Generate a new Page", null
            )
        }
    }

    private fun createSourceFile(project: Project, generator: BasePageGenerator, directory: PsiDirectory) {
        val dir = createDir(generator.dirName(), directory)

        val fileName = generator.fileName()
        val existingPsiFile = dir.findFile(fileName)
        if (existingPsiFile == null) {
            val psiFile = PsiFileFactory.getInstance(project)
                .createFileFromText(fileName, PlainTextLanguage.INSTANCE, generator.generate())
            dir.add(psiFile)
        } else {
            val document = PsiDocumentManager.getInstance(project).getDocument(existingPsiFile)
            document?.insertString(document.textLength, "\n" + generator.generate())
        }
    }

    private fun createDir(dirName: String, directory: PsiDirectory): PsiDirectory =
        if (dirName.isBlank()) directory
        else directory.findSubdirectory(dirName) ?: directory.createSubdirectory(dirName)
}
