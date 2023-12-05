package org.massicer.domain.repositories

import org.massicer.domain.User

interface UserRepository {
    fun getRandom(): User
}
