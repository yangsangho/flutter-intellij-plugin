package io.yangbob.flutterplugin.generator.components

import io.yangbob.flutterplugin.generator.BasePageGenerator

class BlocEventGenerator(name: String, useBaseBloc: Boolean) :
    BasePageGenerator(name, useBaseBloc, templateName = "event") {
    override fun fileName(): String = "${snakeCase()}_event.${fileExtension()}"
    override fun dirName(): String = "bloc"
}
