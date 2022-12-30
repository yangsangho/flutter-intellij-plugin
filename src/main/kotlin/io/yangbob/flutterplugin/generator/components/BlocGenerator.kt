package io.yangbob.flutterplugin.generator.components

import io.yangbob.flutterplugin.generator.BasePageGenerator

class BlocGenerator(name: String, useBaseBloc: Boolean) : BasePageGenerator(name, useBaseBloc, templateName = "bloc") {
    override fun fileName(): String = "${snakeCase()}_bloc.${fileExtension()}"
    override fun dirName(): String = "bloc"
}
