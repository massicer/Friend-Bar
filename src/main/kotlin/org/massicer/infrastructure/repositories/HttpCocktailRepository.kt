package org.massicer.infrastructure.repositories

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.massicer.domain.Item
import org.massicer.domain.Item.Cocktail.Ingredient
import org.massicer.domain.UserName
import org.massicer.domain.repositories.CocktailRepository
import kotlin.reflect.KProperty1

@ApplicationScoped
class HttpCocktailRepository(@RestClient private val httpClient: CocktailClient) : CocktailRepository {
    override fun getRandom(): Item.Cocktail = httpClient.get().drinks.first().toCocktail()
    override fun getFor(name: UserName): Item.Cocktail? =
        httpClient.getByLetter(name.first().lowercase()).drinks.firstOrNull()?.toCocktail()

    @RegisterRestClient(configKey = "cocktails-api")
    interface CocktailClient {

        @Path("/random.php")
        @GET
        fun get(): CocktailResponse

        @Path("/search.php")
        @GET
        fun getByLetter(@QueryParam("f") name: String): CocktailResponse

        data class CocktailResponse(val drinks: MutableList<CocktailModel> = mutableListOf())
        data class CocktailModel(
            @JsonProperty("strDrink")
            val name: String,
            @JsonProperty("strCategory")
            val tagline: String,
            @JsonProperty("strInstructions")
            val instructions: String,
            @JsonProperty("strIngredient1")
            val strIngredient1: String,
            @JsonProperty("strIngredient2")
            val strIngredient2: String?,
            @JsonProperty("strIngredient3")
            val strIngredient3: String?,
            @JsonProperty("strIngredient4")
            val strIngredient4: String?,
            @JsonProperty("strIngredient5")
            val strIngredient5: String?,
            @JsonProperty("strIngredient6")
            val strIngredient6: String?,
            @JsonProperty("strIngredient7")
            val strIngredient7: String?,
            @JsonProperty("strIngredient8")
            val strIngredient8: String?,
            @JsonProperty("strIngredient9")
            val strIngredient9: String?,
            @JsonProperty("strIngredient10")
            val strIngredient10: String?,
            @JsonProperty("strIngredient11")
            val strIngredient11: String?,
            @JsonProperty("strIngredient12")
            val strIngredient12: String?,
            @JsonProperty("strIngredient13")
            val strIngredient13: String?,
            @JsonProperty("strIngredient14")
            val strIngredient14: String?,
            @JsonProperty("strIngredient15")
            val strIngredient15: String?,
            @JsonProperty("strMeasure1")
            val strMeasure1: String?,
            @JsonProperty("strMeasure2")
            val strMeasure2: String?,
            @JsonProperty("strMeasure3")
            val strMeasure3: String?,
            @JsonProperty("strMeasure4")
            val strMeasure4: String?,
            @JsonProperty("strMeasure5")
            val strMeasure5: String?,
            @JsonProperty("strMeasure6")
            val strMeasure6: String?,
            @JsonProperty("strMeasure7")
            val strMeasure7: String?,
            @JsonProperty("strMeasure8")
            val strMeasure8: String?,
            @JsonProperty("strMeasure9")
            val strMeasure9: String?,
            @JsonProperty("strMeasure10")
            val strMeasure10: String?,
            @JsonProperty("strMeasure11")
            val strMeasure11: String?,
            @JsonProperty("strMeasure12")
            val strMeasure12: String?,
            @JsonProperty("strMeasure13")
            val strMeasure13: String?,
            @JsonProperty("strMeasure14")
            val strMeasure14: String?,
            @JsonProperty("strMeasure15")
            val strMeasure15: String?
        )
    }
}

private fun HttpCocktailRepository.CocktailClient.CocktailModel.toCocktail(): Item.Cocktail =
    Item.Cocktail(
        name = this.name,
        tagline = this.tagline,
        ingredients = this.extractIngredients(),
        instructions = this.instructions
            .split("\r\n", ".")
            .filter { it.isNotEmpty() || it.isNotBlank() }
            .map { it.strip() }
            .toSet().toList()

    )

private fun HttpCocktailRepository.CocktailClient.CocktailModel.extractIngredients(): Set<Ingredient> =
    (1..15)
        .map { index ->
            Pair<String?, String?>(
                readInstanceProperty(this, "strIngredient$index"),
                readInstanceProperty(this, "strMeasure$index")
            )
        }
        .filter { it.first != null && it.second != null }
        .filter { it.first!!.isNotBlank() && it.first!!.isNotEmpty() }
        .filter { it.second!!.isNotBlank() && it.second!!.isNotEmpty() }
        .map { Ingredient(it.first!!, it.second!!) }
        .toSet()

@Suppress("UNCHECKED_CAST")
private fun <R> readInstanceProperty(instance: Any, propertyName: String): R {
    val property = instance::class.members
        // don't cast here to <Any, R>, it would succeed silently
        .first { it.name == propertyName } as KProperty1<Any, *>
    // force a invalid cast exception if incorrect type here
    return property.get(instance) as R
}
