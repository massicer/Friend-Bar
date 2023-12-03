package org.massicer.infrastructure.repositories

import jakarta.enterprise.context.ApplicationScoped
import org.massicer.domain.Item.Cocktail
import org.massicer.domain.Item.Cocktail.Ingredient
import org.massicer.domain.Item.Cocktail.Ingredient.Amount
import org.massicer.domain.Item.Cocktail.Ingredient.Amount.MeasureUnit.CL
import org.massicer.domain.repositories.CocktailRepository

@ApplicationScoped
class InMemoryCocktailRepository : CocktailRepository {

    @Suppress("MagicNumber")
    private val cocktails = setOf(
        Cocktail(
            "Mojito",
            "Refreshing and Minty",
            setOf(
                Ingredient("White Rum", Amount(6.0, CL)),
                Ingredient("Fresh Lime Juice", Amount(2.5, CL)),
                Ingredient("Simple Syrup", Amount(2.0, CL)),
                Ingredient("Fresh Mint Leaves", Amount(8.0, CL)),
                Ingredient("Soda Water", Amount(5.0, CL)),
                Ingredient("Ice Cubes", Amount(1.0, CL))
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
                Ingredient("Bourbon", Amount(6.0, CL)),
                Ingredient("Simple Syrup", Amount(1.0, CL)),
                Ingredient("Angostura Bitters", Amount(2.0, CL)),
                Ingredient("Orange Peel", Amount(1.0, CL)),
                Ingredient("Ice Cubes", Amount(1.0, CL))
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
                Ingredient("White Rum", Amount(6.0, CL)),
                Ingredient("Coconut Cream", Amount(4.0, CL)),
                Ingredient("Pineapple Juice", Amount(6.0, CL)),
                Ingredient("Crushed Ice", Amount(1.0, CL)),
                Ingredient("Pineapple Slice", Amount(1.0, CL))
            ),
            listOf(
                "Blend white rum, coconut cream, pineapple juice, and crushed ice until smooth.",
                "Pour into a chilled glass.",
                "Garnish with a pineapple slice."
            )
        )

    )

    override fun getRandom(): Cocktail = cocktails.random()
}
