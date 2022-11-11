package com.pawdlers.pawdlersflutterplugin.generator

import com.pawdlers.pawdlersflutterplugin.generator.components.*
import com.pawdlers.pawdlersflutterplugin.generator.components.PageGenerator

object PageGeneratorFactory {
    fun getPageGenerators(
        name: String, useBaseBloc: Boolean
    ): List<com.pawdlers.pawdlersflutterplugin.generator.PageGenerator> {
        val pageBarrel = PageBarrelGenerator(name)
        val viewBarrel = ViewBarrelGenerator(name)
        val page = PageGenerator(name, useBaseBloc)
        val view = ViewGenerator(name)

        val bloc = BlocGenerator(name, useBaseBloc)
        val event = BlocEventGenerator(name, useBaseBloc)
        val state = BlocStateGenerator(name, useBaseBloc)
        return listOf(pageBarrel, viewBarrel, page, view, bloc, event, state)
    }
}