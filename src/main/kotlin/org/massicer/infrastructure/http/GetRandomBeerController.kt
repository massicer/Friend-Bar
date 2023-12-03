package org.massicer.infrastructure.http

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import org.massicer.domain.Item.Beer
import org.massicer.domain.useCases.GetRandomBeerUseCase
import org.massicer.infrastructure.http.GetRandomBeerController.BeerModel

typealias EthanolMilliliters = Float // for 100 ml of solution
typealias InternationaBitternesUnit = Int
typealias Food = String

@Path("/beer")
class GetRandomBeerController(private val getRandomBeerUseCase: GetRandomBeerUseCase) {

    @GET
    @Produces(APPLICATION_JSON)
    fun getRandom(): BeerModel = getRandomBeerUseCase.getRandomBeer().toModel()

    data class BeerModel(
        val name: String,
        val tagline: String,
        val abv: EthanolMilliliters,
        val ibu: InternationaBitternesUnit,
        val foodPairing: Food
    )
}

private fun Beer.toModel(): BeerModel = BeerModel(name, tagline, abv, ibu.toInt(), foodPairing)
