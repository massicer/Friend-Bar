package org.massicer.infrastructure.http

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response.Status.NOT_FOUND
import jakarta.ws.rs.core.Response.status
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.massicer.domain.Item.Beer
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.User
import org.massicer.domain.useCases.GetSuggestionUseCase
import org.massicer.infrastructure.http.GetRandomBeerController.BeerModel
import org.massicer.infrastructure.http.GetRandomCocktailController.CocktailModel
import org.massicer.infrastructure.http.GetSuggestionController.ErrorResponse.CoctkailNotFound

@Path("/suggestion")
class GetSuggestionController(private val getSuggestionUseCase: GetSuggestionUseCase) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(
        responseCode = "200",
        value = SuggestionResponse::class,
        responseDescription = "The suggestion for the random user. It can be a cocktail or a beer"
    )
    @APIResponses(
        APIResponse(
            responseCode = "404",
            description = "Cocktail not found for user",
            content = [
                Content(
                    mediaType = "application/json",
                    example = """
                        {"error":"CocktailNotFound", "message":"Not found cocktail for user with name: NAME"}
                        """
                )
            ]
        )
    )
    fun getRandom(): Any {
        val suggestion = getSuggestionUseCase.get()
        return suggestion.first?.let {
            when (it) {
                is Beer -> SuggestionResponse(beer = it.toModel())
                is Cocktail -> SuggestionResponse(cocktail = it.toModel())
            }
        } ?: status(NOT_FOUND).entity(CoctkailNotFound(suggestion.second)).build()
    }

    @JsonInclude(NON_NULL)
    class SuggestionResponse(
        @field:Schema(description = "Details about the suggested cocktail. It is null when the beer is not null.")
        val cocktail: CocktailModel? = null,
        @field:Schema(description = "Details about the suggested beer. It is null when the cocktail is not null.")
        val beer: BeerModel? = null
    )

    sealed class ErrorResponse(val error: String, val message: String) {
        class CoctkailNotFound(user: User) :
            ErrorResponse("CocktailNotFound", "Not found cocktail for user with name: ${user.firstName}")
    }
}
