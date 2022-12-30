package io.yangbob.flutterplugin.action

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class GeneratePageDialog(private val listener: Listener) : DialogWrapper(null) {
    private val model = Model()

    init {
        init()
    }

    @Suppress("UnstableApiUsage")
    override fun createCenterPanel(): JComponent {
        return panel {
            row {
                label("Name:")
                textField().focused().bindText(model::pageName)
            }
            row {
                label("Use Base Bloc:")
                checkBox("").bindSelected(model::useBaseBloc)
            }
        }
    }

    override fun doOKAction() {
        super.doOKAction()
        listener.onGeneratePageClicked(model.pageName, model.useBaseBloc)
    }

    interface Listener {
        fun onGeneratePageClicked(pageName: String?, shouldUseBaseBloc: Boolean)
    }
}

internal data class Model(
    var pageName: String = "",
    var useBaseBloc: Boolean = false,
)
