package org.massicer.domain

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset

typealias UserName = String

data class User(
    val firstName: UserName,
    val offset: ZoneOffset
) {

    fun isDaytime(currentInstant: Instant = Instant.now()): Boolean {
        val zoneId = ZoneId.ofOffset("UTC", offset)
        val localTime = LocalTime.ofInstant(currentInstant, zoneId)

        val startOfDay = LocalTime.of(6, 0, 0)
        val endOfDay = LocalTime.of(18, 0, 0)

        return localTime.isAfter(startOfDay) && localTime.isBefore(endOfDay)
    }
}
