package org.massicer.util

import org.massicer.domain.Item
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.Item.Cocktail.Ingredient

object TestDomainObjects {

    val aBeer = Item.Beer("Lager", "Crisp and Clean", 4.5f, 20u, setOf("Grilled Chicken"))

    val aCocktail = Cocktail(
        "Margarita",
        "Classic and Refreshing",
        setOf(
            Ingredient(
                "Tequila",
                "1 glass"
            ),
            Ingredient(
                "Triple Sec",
                "1 glass"
            ),
            Ingredient(
                "Lime Juice",
                "1 glass"
            ),
            Ingredient(
                "Simple Syrup",
                "1 glass"
            ),
            Ingredient(
                "Salt",
                "1 glass"
            )
        ),
        listOf(
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "Take care to moisten only the outer rim and sprinkle the salt on it.",
            "The salt present is only for decoration."
        )
    )
}
