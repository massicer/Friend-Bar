package org.massicer.domain.repositories

import org.massicer.domain.Item

interface BeerRepository {

    fun getRandom(): Item.Beer
}
