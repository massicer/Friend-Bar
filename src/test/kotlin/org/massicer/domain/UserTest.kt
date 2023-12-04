package org.massicer.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.Instant
import java.time.ZoneOffset

class UserTest {

    @ParameterizedTest
    @CsvSource(
        "2023-01-01T07:00:00Z, +02:00, true", // Daytime
        "2023-01-01T07:00:00Z, +00:00, true", // Daytime
        "2023-01-01T05:00:00Z, -05:00, false", // Nighttime
        "2023-01-01T19:00:00Z, -01:00, false" // Nighttime
    )
    fun `test isDaytime`(instant: String, offset: String, expected: Boolean) {
        val user = User("John", ZoneOffset.of(offset))
        val currentInstant = Instant.parse(instant)

        user.isDaytime(currentInstant) shouldBe expected
    }
}
