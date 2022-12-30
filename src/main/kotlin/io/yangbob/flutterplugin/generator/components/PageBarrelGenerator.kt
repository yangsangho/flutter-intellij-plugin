package io.yangbob.flutterplugin.generator.components

import io.yangbob.flutterplugin.generator.BasePageGenerator

class PageBarrelGenerator(name: String) : BasePageGenerator(name, null, templateName = "page-barrel") {
    override fun fileName(): String = "${snakeCase()}.${fileExtension()}"
    override fun dirName(): String = ""
}
