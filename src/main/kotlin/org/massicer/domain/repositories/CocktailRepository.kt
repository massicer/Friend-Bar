package org.massicer.domain.repositories

import org.massicer.domain.Item
import org.massicer.domain.UserName

interface CocktailRepository {

    fun getRandom(): Item.Cocktail

    fun getFor(name: UserName): Item.Cocktail?
}
