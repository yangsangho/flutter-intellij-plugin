package io.yangbob.flutterplugin.generator.components

import io.yangbob.flutterplugin.generator.BasePageGenerator

class BlocStateGenerator(name: String, useBaseBloc: Boolean) :
    BasePageGenerator(name, useBaseBloc, templateName = "state") {
    override fun fileName(): String = "${snakeCase()}_state.${fileExtension()}"
    override fun dirName(): String = "bloc"
}
