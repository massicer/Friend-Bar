package org.massicer.domain.useCases

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.massicer.domain.Item.Beer
import org.massicer.domain.repositories.BeerRepository

class GetRandomBeerUseCaseTest {
    private val service = mockk<BeerRepository>()
    private val useCase = GetRandomBeerUseCase(service)
    private val aBeer = Beer("Lager", "Crisp and Clean", 4.5f, 20u, setOf("Grilled Chicken"))

    @Test
    fun `returns the beer got from the item service`() {
        every { service.getRandom() } returns aBeer

        useCase.get() shouldBe aBeer
    }
}
