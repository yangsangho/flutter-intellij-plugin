package io.yangbob.flutterplugin.generator

import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase
import com.fleshgrinder.extensions.kotlin.toUpperCamelCase
import com.google.common.io.CharStreams
import org.apache.commons.lang.text.StrSubstitutor
import java.io.InputStreamReader

abstract class BasePageGenerator(private val name: String, useBaseBloc: Boolean?, templateName: String) {
    private val templatePagePascalCase = "page_pascal_case"
    private val templatePageSnakeCase = "page_snake_case"

    private val templateString: String
    private val templateValues: MutableMap<String, String>

    init {
        templateValues = mutableMapOf(
            templatePagePascalCase to pascalCase(), templatePageSnakeCase to snakeCase()
        )
        try {
            val templateFolder =
                if (useBaseBloc == null) "common" else if (useBaseBloc) "page_with_base_bloc" else "page_without_base_bloc"
            val resource = "/templates/$templateFolder/$templateName.dart.template"
            val resourceAsStream = BasePageGenerator::class.java.getResourceAsStream(resource)
            templateString = CharStreams.toString(InputStreamReader(resourceAsStream!!, Charsets.UTF_8))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    abstract fun fileName(): String

    abstract fun dirName(): String

    fun generate(): String {
        val substitutor = StrSubstitutor(templateValues).apply {
            escapeChar = '^'
        }
        return substitutor.replace(templateString)
    }

    fun pascalCase(): String = name.toUpperCamelCase()

    fun snakeCase() = name.toLowerSnakeCase()

    fun fileExtension() = "dart"
}
