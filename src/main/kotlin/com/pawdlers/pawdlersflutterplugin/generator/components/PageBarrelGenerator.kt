package com.pawdlers.pawdlersflutterplugin.generator.components

import com.pawdlers.pawdlersflutterplugin.generator.PageGenerator

class PageBarrelGenerator(name: String) : PageGenerator(name, null, templateName = "page-barrel") {
    override fun fileName(): String = "${snakeCase()}.${fileExtension()}"
    override fun dirName(): String = ""
}