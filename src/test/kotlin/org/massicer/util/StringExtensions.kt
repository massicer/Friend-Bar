package org.massicer.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.assertions.json.shouldEqualJson

fun String.toJsonNode(): JsonNode = ObjectMapper().readTree(this)

infix fun String.shouldMatchJson(expected: String) {
    this.toJsonNode() shouldMatchJson expected.toJsonNode()
}

infix fun Any.shouldMatchJson(expected: JsonNode) {
    (this as JsonNode).toString() shouldEqualJson expected.toString()
}

infix fun Any.shouldMatchJson(expected: String) {
    (this as JsonNode).toString() shouldEqualJson expected
}
