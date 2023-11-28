package org.massicer.domain

typealias Name = String
typealias TagLine = String
typealias EthanolMilliliters = Float // for 100 ml of solution
typealias InternationaBitternesUnit = Int
typealias Food = String
typealias Instruction = String
abstract class Item(
    val name: Name,
    val tagline: TagLine
) {

    init {
        check(name.isNotEmpty()) { "Name cannot be empty" }
        check(name.isNotBlank()) { "Name cannot be blank" }
        check(tagline.isNotEmpty()) { "Tagline cannot be empty" }
        check(tagline.isNotBlank()) { "Tagline cannot be blank" }
    }

    @Suppress("MagicNumber")
    class Beer(
        name: Name,
        tagline: TagLine,
        val abv: EthanolMilliliters,
        val ibu: InternationaBitternesUnit,
        val foodPairing: Food
    ) : Item(name, tagline) {
        init {
            check(foodPairing.isNotBlank()) { "FoodPairing cannot be blank" }
            check(foodPairing.isNotEmpty()) { "FoodPairing cannot be blank" }
            check(abv >= 0.0f) { "ABV must be bigger or equal to 0" }
            check(abv <= 100.0f) { "ABV must be smaller or equal to 100.0" }
        }
    }

    class Cocktail(
        name: Name,
        tagline: TagLine,
        val ingredients: Set<Ingredient>,
        val instructions: List<Instruction>
    ) : Item(name, tagline) {
        data class Ingredient(val name: String, val amount: Amount) {
            data class Amount(val value: Double, val measureUnit: MeasureUnit) {
                enum class MeasureUnit { CL }
            }
        }
    }
}
