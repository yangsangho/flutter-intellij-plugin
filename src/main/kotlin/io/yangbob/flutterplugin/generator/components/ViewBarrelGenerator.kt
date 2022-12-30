package io.yangbob.flutterplugin.generator.components

import io.yangbob.flutterplugin.generator.BasePageGenerator

class ViewBarrelGenerator(name: String) : BasePageGenerator(name, null, templateName = "view-barrel") {
    override fun fileName(): String = "view.${fileExtension()}"
    override fun dirName(): String = "view"
}
