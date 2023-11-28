package org.massicer.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.massicer.domain.Item.Beer
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.Item.Cocktail.Ingredient
import org.massicer.domain.Item.Cocktail.Ingredient.Amount
import org.massicer.domain.Item.Cocktail.Ingredient.Amount.MeasureUnit.CL

class ItemTest {

    @Nested
    inner class BeerTest {

        @Test
        fun `beer is correctly created`() {
            Beer(
                name = "my-name",
                tagline = "Hello",
                abv = 0.12f,
                ibu = 2u,
                foodPairing = "Pizza"
            ).let {
                it.name shouldBe "my-name"
                it.tagline shouldBe "Hello"
                it.abv shouldBe 0.12f
                it.ibu shouldBe 2u
                it.foodPairing shouldBe "Pizza"
            }
        }

        @Test
        fun `name cannot be empty`() {
            shouldThrowWithMessage<IllegalStateException>("Name cannot be empty") {
                Beer(
                    name = "",
                    tagline = "Hello",
                    abv = 0.12f,
                    ibu = 2u,
                    foodPairing = "Pizza"
                )
            }
        }

        @Test
        fun `name cannot be blank`() {
            shouldThrowWithMessage<IllegalStateException>("Name cannot be blank") {
                Beer(
                    name = " ",
                    tagline = "Hello",
                    abv = 0.12f,
                    ibu = 2u,
                    foodPairing = "Pizza"
                )
            }
        }

        @Test
        fun `food pairing cannot be empty`() {
            shouldThrowWithMessage<IllegalStateException>("FoodPairing cannot be blank") {
                Beer(
                    name = "mybeer",
                    tagline = "Hello",
                    abv = 0.12f,
                    ibu = 2u,
                    foodPairing = ""
                )
            }
        }

        @Test
        fun `food pairing cannot be blank`() {
            shouldThrowWithMessage<IllegalStateException>("FoodPairing cannot be blank") {
                Beer(
                    name = "mybeer",
                    tagline = "Hello",
                    abv = 0.12f,
                    ibu = 2u,
                    foodPairing = " "
                )
            }
        }

        @Test
        fun `tagline cannot be empty`() {
            shouldThrowWithMessage<IllegalStateException>("Tagline cannot be empty") {
                Beer(
                    name = "my-name",
                    tagline = "",
                    abv = 0.12f,
                    ibu = 2u,
                    foodPairing = "Pizza"
                )
            }
        }

        @Test
        fun `tagline cannot be blank`() {
            shouldThrowWithMessage<IllegalStateException>("Tagline cannot be blank") {
                Beer(
                    name = "my-name",
                    tagline = " ",
                    abv = 0.12f,
                    ibu = 2u,
                    foodPairing = "Pizza"
                )
            }
        }

        @Test
        fun `abv cannot smallest than 0`() {
            shouldThrowWithMessage<IllegalStateException>("ABV must be bigger or equal to 0") {
                Beer(
                    name = "my-name",
                    tagline = "ciao",
                    abv = -0.1f,
                    ibu = 2u,
                    foodPairing = "Pizza"
                )
            }
        }

        @Test
        fun `abv cannot bigger than 100`() {
            shouldThrowWithMessage<IllegalStateException>("ABV must be smaller or equal to 100.0") {
                Beer(
                    name = "my-name",
                    tagline = "ciao",
                    abv = 100.1f,
                    ibu = 2u,
                    foodPairing = "Pizza"
                )
            }
        }

        @Test
        fun `abv cannot bigger than 120`() {
            shouldThrowWithMessage<IllegalStateException>("IBU must be bigger or equal to 120") {
                Beer(
                    name = "my-name",
                    tagline = "ciao",
                    abv = 100f,
                    ibu = 121u,
                    foodPairing = "Pizza"
                )
            }
        }
    }

    @Nested
    inner class CocktailTest {

        @Test
        fun `cocktail is correctly created`() {
            Cocktail(
                name = "cocktail name",
                tagline = "Hello",
                ingredients = setOf(Ingredient("tabasco", Amount(1.2, CL))),
                instructions = listOf("Put it in", "take it out")
            ).let {
                it.name shouldBe "cocktail name"
                it.tagline shouldBe "Hello"
                it.ingredients shouldBe setOf(Ingredient("tabasco", Amount(1.2, CL)))
                it.instructions shouldBe listOf("Put it in", "take it out")
            }
        }

        @Test
        fun `name cannot be empty`() {
            shouldThrowWithMessage<IllegalStateException>("Name cannot be empty") {
                Cocktail(
                    name = "",
                    tagline = "Hello",
                    ingredients = setOf(Ingredient("tabasco", Amount(1.2, CL))),
                    instructions = listOf("Put it in")
                )
            }
        }

        @Test
        fun `name cannot be blank`() {
            shouldThrowWithMessage<IllegalStateException>("Name cannot be blank") {
                Cocktail(
                    name = " ",
                    tagline = "Hello",
                    ingredients = setOf(Ingredient("tabasco", Amount(1.2, CL))),
                    instructions = listOf("Put it in")
                )
            }
        }

        @Test
        fun `tagline cannot be empty`() {
            shouldThrowWithMessage<IllegalStateException>("Tagline cannot be empty") {
                Cocktail(
                    name = "my-drink",
                    tagline = "",
                    ingredients = setOf(Ingredient("tabasco", Amount(1.2, CL))),
                    instructions = listOf("Put it in")
                )
            }
        }

        @Test
        fun `tagline cannot be blank`() {
            shouldThrowWithMessage<IllegalStateException>("Tagline cannot be blank") {
                Cocktail(
                    name = "my-drink",
                    tagline = " ",
                    ingredients = setOf(Ingredient("tabasco", Amount(1.2, CL))),
                    instructions = listOf("Put it in")
                )
            }
        }

        @Test
        fun `instruction cannot be duplicated`() {
            shouldThrowWithMessage<IllegalStateException>("Instructions cannot contain duplicates") {
                Cocktail(
                    name = "my-drink",
                    tagline = "drink it",
                    ingredients = setOf(Ingredient("tabasco", Amount(1.2, CL))),
                    instructions = List(2) { "Put it in" }
                )
            }
        }
    }
}
