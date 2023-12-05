package org.massicer.util

import org.massicer.domain.Item

object TestDomainObjects {

    val aBeer = Item.Beer("Lager", "Crisp and Clean", 4.5f, 20u, "Grilled Chicken")

    val aCocktail = Item.Cocktail(
        "Margarita",
        "Classic and Refreshing",
        setOf(
            Item.Cocktail.Ingredient(
                "Tequila",
                Item.Cocktail.Ingredient.Amount(5.0, Item.Cocktail.Ingredient.Amount.MeasureUnit.CL)
            ),
            Item.Cocktail.Ingredient(
                "Triple Sec",
                Item.Cocktail.Ingredient.Amount(2.0, Item.Cocktail.Ingredient.Amount.MeasureUnit.CL)
            ),
            Item.Cocktail.Ingredient(
                "Lime Juice",
                Item.Cocktail.Ingredient.Amount(3.0, Item.Cocktail.Ingredient.Amount.MeasureUnit.CL)
            ),
            Item.Cocktail.Ingredient(
                "Simple Syrup",
                Item.Cocktail.Ingredient.Amount(1.0, Item.Cocktail.Ingredient.Amount.MeasureUnit.CL)
            ),
            Item.Cocktail.Ingredient(
                "Salt",
                Item.Cocktail.Ingredient.Amount(0.5, Item.Cocktail.Ingredient.Amount.MeasureUnit.CL)
            )
        ),
        listOf(
            "Rub the rim of the glass with the lime slice to make the salt stick to it.",
            "Take care to moisten only the outer rim and sprinkle the salt on it.",
            "The salt present is only for decoration."
        )
    )
}
