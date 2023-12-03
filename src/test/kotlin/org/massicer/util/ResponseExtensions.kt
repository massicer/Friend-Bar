package org.massicer.util

import com.fasterxml.jackson.databind.JsonNode
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

fun ExtractableResponse<Response>.toJsonNode(): JsonNode = `as`(JsonNode::class.java)
