package org.massicer.infrastructure.http

import io.mockk.every
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.massicer.domain.Item
import org.massicer.domain.repositories.BeerRepository
import org.massicer.util.shouldMatchJson
import org.massicer.util.toJsonNode

@QuarkusTest
class GetRandomBeerAPITest {

    @InjectMock
    private lateinit var repository: BeerRepository

    @Test
    fun `a random beer is returned`() {
        every { repository.getRandom() } returns aBeer

        given()
            .`when`().get("/beer")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract()
            .toJsonNode()
            .let { it shouldMatchJson expectedResponse }
    }

    private companion object {
        private val aBeer = Item.Beer("Lager", "Crisp and Clean", 4.5f, 20u, "Grilled Chicken")

        @Language("JSON")
        private val expectedResponse = """
        {
            "name": "Lager",
            "tagline": "Crisp and Clean",
            "abv": 4.5,
            "ibu": 20,
            "foodPairing": "Grilled Chicken"
        }
    """.toJsonNode()
    }
}
