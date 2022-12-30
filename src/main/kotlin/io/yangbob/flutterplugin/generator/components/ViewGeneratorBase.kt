package io.yangbob.flutterplugin.generator.components

import io.yangbob.flutterplugin.generator.BasePageGenerator

class ViewGeneratorBase(name: String) : BasePageGenerator(name, null, templateName = "view") {
    override fun fileName(): String = "${snakeCase()}_view.${fileExtension()}"
    override fun dirName(): String = "view"
}
