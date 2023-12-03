package org.massicer.domain.repositories

import org.massicer.domain.Item

interface CocktailRepository {

    fun getRandom(): Item.Cocktail
}
