package org.massicer.infrastructure.http

import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.massicer.domain.User
import org.massicer.domain.repositories.BeerRepository
import org.massicer.domain.repositories.CocktailRepository
import org.massicer.domain.repositories.UserRepository
import org.massicer.util.TestDomainObjects.aBeer
import org.massicer.util.TestDomainObjects.aCocktail
import org.massicer.util.shouldMatchJson
import org.massicer.util.toJsonNode

@QuarkusTest
class GetRandomSuggestionAPITest {

    @InjectMock
    private lateinit var cocktailRepository: CocktailRepository

    @InjectMock
    private lateinit var beerRepository: BeerRepository

    @InjectMock
    private lateinit var userRepository: UserRepository

    private val user = mockk<User>()

    @Test
    fun `a random beer is returned when the user is in its day time`() {
        every { user.isDaytime(any()) } returns true
        every { userRepository.getRandom() } returns user
        every { beerRepository.getRandom() } returns aBeer

        given()
            .`when`().get("/suggestion")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract()
            .toJsonNode()
            .let { it shouldMatchJson expectedBeerResponse }

        verify { cocktailRepository wasNot called }
    }

    @Test
    fun `a random cocktail is returned when the user is in its night time`() {
        every { user.isDaytime(any()) } returns false
        val userName = "Mauro"
        every { user.firstName } returns userName
        every { userRepository.getRandom() } returns user
        every { cocktailRepository.getFor(userName) } returns aCocktail

        given()
            .`when`().get("/suggestion")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract()
            .toJsonNode()
            .let { it shouldMatchJson expectedCocktailResponse }

        verify { beerRepository wasNot called }
    }

    @Test
    fun `when the user is in night time but no cocktail is found, the error response is returned`() {
        every { user.isDaytime(any()) } returns false
        val userName = "Mauro"
        every { user.firstName } returns userName
        every { userRepository.getRandom() } returns user
        every { cocktailRepository.getFor(userName) } returns null

        given()
            .`when`().get("/suggestion")
            .then()
            .statusCode(404)
            .contentType("application/json")
            .extract()
            .toJsonNode()
            .let { it shouldMatchJson errorResponse }

        verify { beerRepository wasNot called }
    }

    private companion object {

        @Language("JSON")
        private val errorResponse = """
            {"error":"CocktailNotFound", "message":"Not found cocktail for user with name: Mauro"}
        """.trimIndent()

        @Language("JSON")
        private val expectedBeerResponse = """
        {
            "beer":  
                {
                    "name": "Lager",
                    "tagline": "Crisp and Clean",
                    "abv": 4.5,
                    "ibu": 20,
                    "foodPairing": ["Grilled Chicken"]
                }
        }
    """.toJsonNode()

        @Language("JSON")
        private val expectedCocktailResponse = """
            {"cocktail": 
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
            }
    """.toJsonNode()
    }
}
