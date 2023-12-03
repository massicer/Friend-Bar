package org.massicer.domain.useCases

import jakarta.enterprise.context.ApplicationScoped
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.repositories.CocktailRepository

@ApplicationScoped
class GetRandomCocktailUseCase(private val itemsService: CocktailRepository) {

    fun get(): Cocktail = itemsService.getRandom()
}
