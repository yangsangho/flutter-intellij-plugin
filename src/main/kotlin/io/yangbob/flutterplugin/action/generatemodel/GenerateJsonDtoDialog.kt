package io.yangbob.flutterplugin.action.generatemodel

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class GenerateJsonDtoDialog(private val listener: Listener) : DialogWrapper(null) {
    private var model = Model()

    init {
        init()
    }

    @Suppress("UnstableApiUsage")
    override fun createCenterPanel(): JComponent {
        return panel {
            row {
                label("Model Name:")
                textField().focused().bindText(model::name)
            }
        }
    }

    override fun doOKAction() {
        super.doOKAction()
        listener.onGenerateModelClicked(model.name)
    }

    interface Listener {
        fun onGenerateModelClicked(modelName: String?)
    }
}

internal data class Model(
    var name: String = "",
)
