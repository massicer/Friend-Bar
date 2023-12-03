package org.massicer.domain.useCases

import jakarta.enterprise.context.ApplicationScoped
import org.massicer.domain.Item.Beer
import org.massicer.domain.repositories.BeerRepository

@ApplicationScoped
class GetRandomBeerUseCase(private val itemsService: BeerRepository) {

    fun getRandomBeer(): Beer = itemsService.getRandomBeer()
}
