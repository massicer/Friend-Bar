package org.massicer.infrastructure.repositories

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.massicer.domain.User
import org.massicer.domain.UserRepository
import java.time.ZoneOffset

@ApplicationScoped
class HttpUserRepository(@RestClient private val httpClient: UserClient) : UserRepository {

    override fun getRandom(): User {
        val userResponse = httpClient.get().results.first()
        return User(
            firstName = userResponse.name.first,
            offset = userResponse.location.timezone.offset.toOffset()
        )
    }

    @RegisterRestClient(configKey = "user-api")
    interface UserClient {

        @Path("/")
        @GET
        fun get(): UserResponse
    }

    data class UserResponse(val results: List<UserModel>) {
        // Add a default constructor
        constructor() : this(emptyList())

        data class UserModel(val name: Name, val location: Location) {
            data class Name(val first: String) {
                constructor() : this("")
            }

            data class Location(val timezone: Timezone) {

                constructor() : this(Timezone())

                data class Timezone(val offset: String) {
                    constructor() : this("")
                }
            }
        }
    }
}

private fun String.toOffset(): ZoneOffset {
    val sign: Char = this[0]
    var hours = 0
    var minutes = 0
    if (sign == '-' || sign == '+') {
        hours = this.substring(1, this.indexOf(':')).toInt()
        minutes = this.substring(this.indexOf(':') + 1).toInt()
    }

    val offsetSign = if (sign == '-') -1 else 1

    return ZoneOffset.ofHoursMinutes(offsetSign * hours, minutes * offsetSign)
}
