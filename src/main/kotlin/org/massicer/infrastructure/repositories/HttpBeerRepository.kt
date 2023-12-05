package org.massicer.infrastructure.repositories

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.massicer.domain.Item.Beer
import org.massicer.domain.repositories.BeerRepository

@ApplicationScoped
class HttpBeerRepository(@RestClient private val httpClient: BeerClient) : BeerRepository {
    override fun getRandom(): Beer = httpClient.get().first().toBeer()

    @RegisterRestClient(configKey = "beers-api")
    interface BeerClient {

        @Path("/random")
        @GET
        fun get(): MutableList<BeerResponse>

        data class BeerResponse(
            val name: String,
            val tagline: String,
            val abv: Int,
            val ibu: Int,
            @JsonProperty("food_pairing")
            val foodPairing: List<String>
        )
    }
}

private fun HttpBeerRepository.BeerClient.BeerResponse.toBeer(): Beer {
    return Beer(
        name,
        tagline,
        abv.toFloat(),
        ibu.toUInt().coerceAtMost(120u),
        foodPairing.toSet()
    )
}
