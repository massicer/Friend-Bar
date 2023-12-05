package org.massicer.domain.useCases

import jakarta.enterprise.context.ApplicationScoped
import org.massicer.domain.Item
import org.massicer.domain.User
import org.massicer.domain.UserRepository
import org.massicer.domain.repositories.BeerRepository
import org.massicer.domain.repositories.CocktailRepository

@ApplicationScoped
class GetSuggestionUseCase(
    private val cocktailRepository: CocktailRepository,
    private val beerRepository: BeerRepository,
    private val userRepository: UserRepository
) {

    fun get(): Pair<Item?, User> {
        val user = userRepository.getRandom()
        val item =
            if (user.isDaytime()) {
                beerRepository.getRandom()
            } else {
                cocktailRepository.getFor(user.firstName)
            }
        return Pair(item, user)
    }
}
