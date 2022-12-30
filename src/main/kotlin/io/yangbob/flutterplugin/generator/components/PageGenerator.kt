package io.yangbob.flutterplugin.generator.components

import io.yangbob.flutterplugin.generator.BasePageGenerator

class PageGenerator(name: String, useBaseBloc: Boolean) : BasePageGenerator(name, useBaseBloc, templateName = "page") {
    override fun fileName(): String = "${snakeCase()}_page.${fileExtension()}"
    override fun dirName(): String = "view"
}
