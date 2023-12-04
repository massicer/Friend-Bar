package org.massicer.infrastructure.repositories

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.massicer.domain.User
import org.massicer.domain.UserName
import org.massicer.domain.UserRepository
import java.time.ZoneId
import java.time.ZoneOffset


@ApplicationScoped
class HttpUserRepository(@RestClient private val httpClient: UserClient) : UserRepository {


    override fun getRandom(): User {
        val userResponse = httpClient.get().results.first()

        val offsetString = userResponse.location.timezone.offset

        val sign: Char = offsetString[0]
        var hours = 0
        var minutes = 0
        if (sign == '-' || sign == '+') {
            hours = offsetString.substring(1, offsetString.indexOf(':')).toInt()
            minutes = offsetString.substring(offsetString.indexOf(':') + 1).toInt()
        }


        val offsetSign = if (sign == '-') -1 else 1

        val zoneOffset = ZoneOffset.ofHoursMinutes(offsetSign * hours, minutes * offsetSign)

        return User(
            firstName = userResponse.name.first,
            offset = zoneOffset
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

