package org.massicer.domain.useCases

import jakarta.enterprise.context.ApplicationScoped
import org.massicer.domain.Item
import org.massicer.domain.Item.Beer
import org.massicer.domain.UserRepository
import org.massicer.domain.repositories.BeerRepository
import org.massicer.domain.repositories.CocktailRepository

@ApplicationScoped
class GetSuggestionUseCase(
    private val cocktailRepository: CocktailRepository,
    private val beerRepository: BeerRepository,
    private val userRepository: UserRepository
) {

    fun get(): Item? {
        val user = userRepository.getRandom()
        return if (user.isDaytime()) {
            beerRepository.getRandom()
        } else {
            cocktailRepository.getRandom()
        }
    }
}
