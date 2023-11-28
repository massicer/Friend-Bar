package org.massicer.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ItemTest {

    @Nested
    inner class BeerTest {

        @Test
        fun `name cannot be empty`() {
            shouldThrowWithMessage<IllegalStateException>("Name cannot be empty") {
                Item.Beer(
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
                Item.Beer(
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
                Item.Beer(
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
                Item.Beer(
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
                Item.Beer(
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
                Item.Beer(
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
                Item.Beer(
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
                Item.Beer(
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
                Item.Beer(
                    name = "my-name",
                    tagline = "ciao",
                    abv = 100f,
                    ibu = 121u,
                    foodPairing = "Pizza"
                )
            }
        }
    }
}
