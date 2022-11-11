package com.pawdlers.pawdlersflutterplugin.generator.components

import com.pawdlers.pawdlersflutterplugin.generator.PageGenerator

class BlocStateGenerator(name: String, useBaseBloc: Boolean) :
    PageGenerator(name, useBaseBloc, templateName = "state") {
    override fun fileName(): String = "${snakeCase()}_state.${fileExtension()}"
    override fun dirName(): String = "bloc"
}