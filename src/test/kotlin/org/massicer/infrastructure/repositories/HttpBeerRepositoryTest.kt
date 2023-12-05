package org.massicer.infrastructure.repositories

import io.kotest.matchers.shouldNotBe
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@QuarkusTest
class HttpBeerRepositoryTest {

    @Inject
    private lateinit var service: HttpBeerRepository

    @Test
    fun `the beer is correctly returned`() {
        service.getRandom() shouldNotBe null
    }
}
