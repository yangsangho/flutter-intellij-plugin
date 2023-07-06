package io.yangbob.flutterplugin.action.generatemodel

import io.yangbob.flutterplugin.common.toLowerSnakeCase
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.fileTypes.PlainTextLanguage
import com.intellij.psi.PsiFileFactory
import io.yangbob.flutterplugin.common.Utils
import io.yangbob.flutterplugin.generator.ModelGenerator

class GenerateModel : AnAction(), GenerateJsonDtoDialog.Listener {
    private lateinit var dataContext: DataContext

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = GenerateJsonDtoDialog(this)
        dialog.show()
    }

    override fun onGenerateModelClicked(modelName: String?) {
        ApplicationManager.getApplication().runWriteAction {
            modelName?.let { name ->
                val generator = ModelGenerator(name)
                val fileName = "${name.toLowerSnakeCase()}.dart"

                val project = CommonDataKeys.PROJECT.getData(dataContext)
                val view = LangDataKeys.IDE_VIEW.getData(dataContext)
                val directory = view?.orChooseDirectory
                if (project == null || directory == null) return@runWriteAction

                val baseDir = Utils.createDir(name.toLowerSnakeCase(), directory)

                ApplicationManager.getApplication().runWriteAction {
                    CommandProcessor.getInstance().executeCommand(
                        project, {
                            val file = PsiFileFactory.getInstance(project).createFileFromText(
                                    fileName, PlainTextLanguage.INSTANCE, generator.generate()
                                )

                            baseDir.add(file)
//                        directory.add(file)
                        }, "Generate Json Dto", null
                    )
                }
            }
        }
    }
}
