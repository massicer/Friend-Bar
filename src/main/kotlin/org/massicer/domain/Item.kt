package org.massicer.domain

typealias Name = String
typealias TagLine = String
typealias EthanolMilliliters = ULong // for 100 ml of solution
typealias InternationaBitternesUnit = UByte
typealias Food = String
typealias Instruction = String
sealed class Item {
    abstract val name: Name
    abstract val tagline: TagLine

    data class Beer(
        override val name: Name,
        override val tagline: TagLine,
        val abv: EthanolMilliliters,
        val ibu: InternationaBitternesUnit,
        val foodPairing: Food
    ) : Item()

    data class Cocktail(
        override val name: Name,
        override val tagline: TagLine,
        val ingredients: Set<Ingredient>,
        val instructions: List<Instruction>
    ) : Item() {
        data class Ingredient(val name: String, val amount: Amount) {
            data class Amount(val value: Double, val measureUnit: MeasureUnit) {
                enum class MeasureUnit { CL }
            }
        }
    }
}
