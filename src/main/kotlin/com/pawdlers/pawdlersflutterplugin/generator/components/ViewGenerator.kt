package com.pawdlers.pawdlersflutterplugin.generator.components

import com.pawdlers.pawdlersflutterplugin.generator.PageGenerator

class ViewGenerator(name: String) : PageGenerator(name, null, templateName = "view") {
    override fun fileName(): String = "${snakeCase()}_view.${fileExtension()}"
    override fun dirName(): String = "view"
}