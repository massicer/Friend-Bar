package org.massicer.infrastructure.repositories

import org.massicer.domain.Item.Cocktail
import org.massicer.domain.Item.Cocktail.Ingredient
import org.massicer.domain.UserName
import org.massicer.domain.repositories.CocktailRepository

class InMemoryCocktailRepository : CocktailRepository {

    @Suppress("MagicNumber")
    private val cocktails = setOf(
        Cocktail(
            "Mojito",
            "Refreshing and Minty",
            setOf(
                Ingredient("White Rum", "6.0 cl"),
                Ingredient("Fresh Lime Juice", "2.5 cl"),
                Ingredient("Simple Syrup", "2.0 cl"),
                Ingredient("Fresh Mint Leaves", "8.0 cl"),
                Ingredient("Soda Water", "5.0 cl"),
                Ingredient("Ice Cubes", "1.0 cl")
            ),
            listOf(
                "Muddle fresh mint leaves and simple syrup in a glass.",
                "Add white rum and fresh lime juice.",
                "Fill the glass with ice cubes.",
                "Top up with soda water and stir gently.",
                "Garnish with a mint sprig."
            )
        ),
        Cocktail(
            "Old Fashioned",
            "Classic and Strong",
            setOf(
                Ingredient("Bourbon", "6.0 cl"),
                Ingredient("Simple Syrup", "1.0 cl"),
                Ingredient("Angostura Bitters", "2.0 cl"),
                Ingredient("Orange Peel", "1.0 cl"),
                Ingredient("Ice Cubes", "1.0 cl")
            ),
            listOf(
                "In a mixing glass, combine bourbon, simple syrup, and bitters.",
                "Add ice cubes and stir until well-chilled.",
                "Strain the mixture into a glass with a large ice cube.",
                "Express the oil from the orange peel over the drink and drop it in."
            )
        ),
        Cocktail(
            "Piña Colada",
            "Tropical and Creamy",
            setOf(
                Ingredient("White Rum", "6.0 cl"),
                Ingredient("Coconut Cream", "4.0 cl"),
                Ingredient("Pineapple Juice", "6.0 cl"),
                Ingredient("Crushed Ice", "1.0 cl"),
                Ingredient("Pineapple Slice", "1.0 cl")
            ),
            listOf(
                "Blend white rum, coconut cream, pineapple juice, and crushed ice until smooth.",
                "Pour into a chilled glass.",
                "Garnish with a pineapple slice."
            )
        )
    )

    override fun getRandom(): Cocktail = cocktails.random()
    override fun getFor(name: UserName): Cocktail? {
        val letter = name.first().lowercase()
        return cocktails.find { it.name.first().lowercase() == letter }
    }
}
