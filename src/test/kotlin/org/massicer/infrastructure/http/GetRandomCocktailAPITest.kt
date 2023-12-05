package org.massicer.infrastructure.http

import io.mockk.every
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.massicer.domain.repositories.CocktailRepository
import org.massicer.util.TestDomainObjects.aCocktail
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

        @Language("JSON")
        private val expectedResponse = """
        {
              "name": "Margarita",
              "tagline": "Classic and Refreshing",
              "ingredients": [
                {
                  "name": "Tequila",
                  "amount": "1 glass"
                },
                {
                  "name": "Triple Sec",
                  "amount": "1 glass"
                },
                {
                  "name": "Lime Juice",
                  "amount": "1 glass"
                },
                {
                  "name": "Simple Syrup",
                  "amount": "1 glass"
                },
                {
                  "name": "Salt",
                  "amount": "1 glass"
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
