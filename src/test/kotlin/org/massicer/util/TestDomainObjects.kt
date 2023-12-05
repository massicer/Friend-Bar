package org.massicer.util

import org.massicer.domain.Item
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.Item.Cocktail.Ingredient
import org.massicer.domain.Item.Cocktail.Ingredient.Amount
import org.massicer.domain.Item.Cocktail.Ingredient.Amount.MeasureUnit.CL

object TestDomainObjects {

    val aBeer = Item.Beer("Lager", "Crisp and Clean", 4.5f, 20u, setOf("Grilled Chicken"))

    val aCocktail = Cocktail(
        "Margarita",
        "Classic and Refreshing",
        setOf(
            Ingredient(
                "Tequila",
                Amount(5.0, CL)
            ),
            Ingredient(
                "Triple Sec",
                Amount(2.0, CL)
            ),
            Ingredient(
                "Lime Juice",
                Amount(3.0, CL)
            ),
            Ingredient(
                "Simple Syrup",
                Amount(1.0, CL)
            ),
            Ingredient(
                "Salt",
                Amount(0.5, CL)
            )
        ),
        listOf(
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "Take care to moisten only the outer rim and sprinkle the salt on it.",
            "The salt present is only for decoration."
        )
    )
}
