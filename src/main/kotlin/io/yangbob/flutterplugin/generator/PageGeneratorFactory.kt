package io.yangbob.flutterplugin.generator

import io.yangbob.flutterplugin.generator.components.BlocEventGenerator
import io.yangbob.flutterplugin.generator.components.BlocGenerator
import io.yangbob.flutterplugin.generator.components.BlocStateGenerator
import io.yangbob.flutterplugin.generator.components.PageBarrelGenerator
import io.yangbob.flutterplugin.generator.components.PageGenerator
import io.yangbob.flutterplugin.generator.components.ViewBarrelGenerator
import io.yangbob.flutterplugin.generator.components.ViewGeneratorBase

object PageGeneratorFactory {
    fun getPageGenerators(
        name: String, useBaseBloc: Boolean
    ): List<BasePageGenerator> {
        val pageBarrel = PageBarrelGenerator(name)
        val viewBarrel = ViewBarrelGenerator(name)
        val page = PageGenerator(name, useBaseBloc)
        val view = ViewGeneratorBase(name)

        val bloc = BlocGenerator(name, useBaseBloc)
        val event = BlocEventGenerator(name, useBaseBloc)
        val state = BlocStateGenerator(name, useBaseBloc)
        return listOf(pageBarrel, viewBarrel, page, view, bloc, event, state)
    }
}
