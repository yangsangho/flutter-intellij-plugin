package com.pawdlers.pawdlersflutterplugin.generator.components

import com.pawdlers.pawdlersflutterplugin.generator.PageGenerator

class ViewBarrelGenerator(name: String) : PageGenerator(name, null, templateName = "view-barrel") {
    override fun fileName(): String = "view.${fileExtension()}"
    override fun dirName(): String = "view"
}