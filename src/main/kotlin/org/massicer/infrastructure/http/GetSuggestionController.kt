package org.massicer.infrastructure.http

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.RuntimeType
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.ResponseBuilder
import org.massicer.domain.Item
import org.massicer.domain.Item.Beer
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.User
import org.massicer.domain.useCases.GetRandomBeerUseCase
import org.massicer.domain.useCases.GetSuggestionUseCase
import org.massicer.infrastructure.http.GetSuggestionController.ErrorResponse.CoctkailNotFound

@Path("/suggestion")
class GetSuggestionController(private val getSuggestionUseCase: GetSuggestionUseCase) {

    @GET
    fun getRandom(): Any {
        val suggestion = getSuggestionUseCase.get()
        return suggestion.first?.let {
            when (it) {
                is Beer -> it.toModel()
                is Cocktail -> it.toModel()
            }
        } ?: Response.status(404).entity(CoctkailNotFound(suggestion.second)).build()
    }

    sealed class ErrorResponse(val error: String, val message: String) {
        class CoctkailNotFound(user: User) :
            ErrorResponse("CocktailNotFound", "Not found cocktail for user with name: ${user.firstName}")
    }

}