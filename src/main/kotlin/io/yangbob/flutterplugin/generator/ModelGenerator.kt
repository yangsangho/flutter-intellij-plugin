package io.yangbob.flutterplugin.generator

import com.google.common.io.CharStreams
import io.yangbob.flutterplugin.common.toLowerSnakeCase
import io.yangbob.flutterplugin.common.toUpperCamelCase
import org.apache.commons.lang.text.StrSubstitutor
import java.io.InputStreamReader

class ModelGenerator(private val name: String) {
    private val templatePagePascalCase = "page_pascal_case"
    private val templatePageSnakeCase = "page_snake_case"

    private val templateString: String
    private val templateValues: MutableMap<String, String>

    init {
        templateValues = mutableMapOf(
            templatePagePascalCase to pascalCase(), templatePageSnakeCase to snakeCase()
        )
        try {
            val resource = "/templates/model/model.dart.template"
            val resourceAsStream = ModelGenerator::class.java.getResourceAsStream(resource)
            templateString =
                CharStreams.toString(InputStreamReader(resourceAsStream!!, Charsets.UTF_8))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun pascalCase(): String = name.toUpperCamelCase()

    private fun snakeCase(): String = name.toLowerSnakeCase()

    fun generate(): String {
        val substitutor = StrSubstitutor(templateValues).apply {
            escapeChar = '^'
        }
        return substitutor.replace(templateString)
    }
}
