package com.pawdlers.pawdlersflutterplugin.generator.components

import com.pawdlers.pawdlersflutterplugin.generator.PageGenerator

class BlocEventGenerator(name: String, useBaseBloc: Boolean) :
    PageGenerator(name, useBaseBloc, templateName = "event") {
    override fun fileName(): String = "${snakeCase()}_event.${fileExtension()}"
    override fun dirName(): String = "bloc"
}