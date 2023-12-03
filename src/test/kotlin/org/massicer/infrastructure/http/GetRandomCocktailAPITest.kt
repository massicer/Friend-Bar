package org.massicer.infrastructure.http

import io.mockk.every
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.Item.Cocktail.Ingredient
import org.massicer.domain.Item.Cocktail.Ingredient.Amount
import org.massicer.domain.Item.Cocktail.Ingredient.Amount.MeasureUnit.CL
import org.massicer.domain.repositories.CocktailRepository
import org.massicer.util.shouldMatchJson
import org.massicer.util.toJsonNode

@QuarkusTest
class GetRandomCocktailAPITest {

    @InjectMock
    private lateinit var repository: CocktailRepository

    @Test
    fun `a random cocktail is returned`() {
        every { repository.getRandom() } returns aCocktail

        given()
            .`when`().get("/cocktail")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract()
            .toJsonNode()
            .let { it shouldMatchJson expectedResponse }
    }

    private companion object {
        private val aCocktail = Cocktail(
            "Margarita",
            "Classic and Refreshing",
            setOf(
                Ingredient("Tequila", Amount(5.0, CL)),
                Ingredient("Triple Sec", Amount(2.0, CL)),
                Ingredient("Lime Juice", Amount(3.0, CL)),
                Ingredient("Simple Syrup", Amount(1.0, CL)),
                Ingredient("Salt", Amount(0.5, CL))
            ),
            listOf(
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                "Take care to moisten only the outer rim and sprinkle the salt on it.",
                "The salt present is only for decoration."
            )
        )

        @Language("JSON")
        private val expectedResponse = """
            {
              "name": "Margarita",
              "tagline": "Classic and Refreshing",
              "ingredients": [
                {
                  "name": "Tequila",
                  "amount": {
                    "value": 5.0,
                    "measureUnit": "CL"
                  }
                },
                {
                  "name": "Triple Sec",
                  "amount": {
                    "value": 2.0,
                    "measureUnit": "CL"
                  }
                },
                {
                  "name": "Lime Juice",
                  "amount": {
                    "value": 3.0,
                    "measureUnit": "CL"
                  }
                },
                {
                  "name": "Simple Syrup",
                  "amount": {
                    "value": 1.0,
                    "measureUnit": "CL"
                  }
                },
                {
                  "name": "Salt",
                  "amount": {
                    "value": 0.5,
                    "measureUnit": "CL"
                  }
                }
              ],
              "instructions": [
                "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                "Take care to moisten only the outer rim and sprinkle the salt on it.",
                "The salt present is only for decoration."
              ]
            }

        
    """.toJsonNode()
    }
}