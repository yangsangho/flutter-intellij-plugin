package com.pawdlers.pawdlersflutterplugin.generator.components

import com.pawdlers.pawdlersflutterplugin.generator.PageGenerator

class PageGenerator(name: String, useBaseBloc: Boolean) : PageGenerator(name, useBaseBloc, templateName = "page") {
    override fun fileName(): String = "${snakeCase()}_page.${fileExtension()}"
    override fun dirName(): String = "view"
}