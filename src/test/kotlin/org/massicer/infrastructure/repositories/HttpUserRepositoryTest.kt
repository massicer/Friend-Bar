package org.massicer.infrastructure.repositories

import io.kotest.matchers.shouldNotBe
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@QuarkusTest
class HttpUserRepositoryTest {

    @Inject
    private lateinit var service: HttpUserRepository

    @Test
    fun `the user is correctly returned`() {
        service.getRandom() shouldNotBe null
    }
}
