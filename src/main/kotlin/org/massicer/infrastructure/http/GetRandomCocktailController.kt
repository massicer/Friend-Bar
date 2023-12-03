package org.massicer.infrastructure.http

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.massicer.domain.Item
import org.massicer.domain.useCases.GetRandomCocktailUseCase
import org.massicer.infrastructure.http.GetRandomCocktailController.CocktailModel.Ingredient
import org.massicer.infrastructure.http.GetRandomCocktailController.CocktailModel.Ingredient.Amount
import org.massicer.infrastructure.http.GetRandomCocktailController.CocktailModel.Ingredient.Amount.MeasureUnit

typealias Instruction = String

@Path("/cocktail")
class GetRandomCocktailController(private val getRandomCocktailUseCase: GetRandomCocktailUseCase) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getRandom(): CocktailModel = getRandomCocktailUseCase.get().toModel()

    data class CocktailModel(
        val name: String,
        val tagline: String,
        val ingredients: Set<Ingredient>,
        val instructions: List<Instruction>
    ) {
        data class Ingredient(val name: String, val amount: Amount) {
            data class Amount(val value: Double, val measureUnit: MeasureUnit) {
                enum class MeasureUnit { CL }
            }
        }
    }
}

private fun Item.Cocktail.toModel(): GetRandomCocktailController.CocktailModel {
    return GetRandomCocktailController.CocktailModel(
        name = name,
        tagline = tagline,
        ingredients = ingredients.map {
            Ingredient(
                it.name,
                Amount(it.amount.value, MeasureUnit.valueOf(it.amount.measureUnit.name))
            )
        }.toSet(),
        instructions = instructions
    )
}
