package org.massicer.infrastructure.repositories

import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@QuarkusTest
class HttpCocktailRepositoryTest {

    @Inject
    private lateinit var service: HttpCocktailRepository

    @Test
    fun `the cocktail is correctly returned`() {
        service.getRandom() shouldNotBe null
    }

    @Test
    fun `the cocktail by User name is correctly returned`() {
        service.getFor("Mauro")!!.let {
            it.instructions.shouldNotBeEmpty()
            it.ingredients.shouldNotBeEmpty()
            it.tagline shouldBe "Coffee / Tea"
            it.name shouldStartWith "M"
        }
    }
}
