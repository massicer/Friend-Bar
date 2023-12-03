package org.massicer.infrastructure.repositories

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class InMemoryBeerRepositoryTest {

    val service = InMemoryBeerRepository()

    @Test
    fun `returns a random beer correctly`() {
        val firstBeer = service.getRandomBeer()
        val secondBeer = service.getRandomBeer()

        firstBeer shouldNotBe secondBeer
    }
}
