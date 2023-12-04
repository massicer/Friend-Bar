package org.massicer.infrastructure.http

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.ResponseBuilder
import org.massicer.domain.useCases.GetRandomBeerUseCase
import org.massicer.domain.useCases.GetSuggestionUseCase

@Path("/suggestion")
class GetSuggestionController(private val getSuggestionUseCase: GetSuggestionUseCase) {

    @GET
    fun getRandom(): Response = getSuggestionUseCase.get().let {
        Response.status(200).build()
    }

}