package org.massicer.infrastructure.http

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema
import org.massicer.domain.Item
import org.massicer.domain.useCases.GetRandomCocktailUseCase
import org.massicer.infrastructure.http.GetRandomCocktailController.CocktailModel.Ingredient

typealias Instruction = String

@Path("/cocktail")
class GetRandomCocktailController(private val getRandomCocktailUseCase: GetRandomCocktailUseCase) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(
        responseCode = "200",
        value = CocktailModel::class,
        responseDescription = "A random Cocktail"
    )
    fun getRandom(): CocktailModel = getRandomCocktailUseCase.get().toModel()

    data class CocktailModel(
        val name: String,
        val tagline: String,
        val ingredients: Set<Ingredient>,
        val instructions: List<Instruction>
    ) {
        data class Ingredient(val name: String, val amount: String)
    }
}

fun Item.Cocktail.toModel(): GetRandomCocktailController.CocktailModel {
    return GetRandomCocktailController.CocktailModel(
        name = name,
        tagline = tagline,
        ingredients = ingredients.map { Ingredient(it.name, it.amount) }.toSet(),
        instructions = instructions
    )
}
