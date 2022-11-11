package com.pawdlers.pawdlersflutterplugin.generator.components

import com.pawdlers.pawdlersflutterplugin.generator.PageGenerator

class BlocGenerator(name: String, useBaseBloc: Boolean) : PageGenerator(name, useBaseBloc, templateName = "bloc") {
    override fun fileName(): String = "${snakeCase()}_bloc.${fileExtension()}"
    override fun dirName(): String = "bloc"
}