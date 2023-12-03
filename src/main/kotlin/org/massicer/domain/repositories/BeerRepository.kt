package org.massicer.domain.repositories

import org.massicer.domain.Item

interface BeerRepository {

    fun getRandomBeer(): Item.Beer
}
