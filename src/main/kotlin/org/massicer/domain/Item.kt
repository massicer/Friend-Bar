package org.massicer.domain

typealias Name = String
typealias TagLine = String
typealias EthanolMilliliters = Float // for 100 ml of solution
typealias InternationaBitternesUnit = UInt
typealias Food = String
typealias Instruction = String

sealed class Item(
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
        val foodPairing: Set<Food>
    ) : Item(name, tagline) {
        init {
            check(abv >= 0.0f) { "ABV must be bigger or equal to 0" }
            check(abv <= 100.0f) { "ABV must be smaller or equal to 100.0" }
            check(ibu <= 120u) { "IBU must be bigger or equal to 120" }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Beer) return false
            if (!super.equals(other)) return false

            if (abv != other.abv) return false
            if (ibu != other.ibu) return false
            return foodPairing == other.foodPairing
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + abv.hashCode()
            result = 31 * result + ibu.hashCode()
            result = 31 * result + foodPairing.hashCode()
            return result
        }
    }

    class Cocktail(
        name: Name,
        tagline: TagLine,
        val ingredients: Set<Ingredient>,
        val instructions: List<Instruction>
    ) : Item(name, tagline) {

        init {
            check(instructions.distinct().size == instructions.size) { "Instructions cannot contain duplicates" }
        }

        data class Ingredient(val name: String, val amount: Amount) {
            data class Amount(val value: Double, val measureUnit: MeasureUnit) {

                init {
                    check(value > 0.0) { "Value mus be bigger than 0" }
                }

                enum class MeasureUnit { CL }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Cocktail) return false
            if (!super.equals(other)) return false

            if (ingredients != other.ingredients) return false
            return instructions == other.instructions
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + ingredients.hashCode()
            result = 31 * result + instructions.hashCode()
            return result
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Item) return false

        if (name != other.name) return false
        return tagline == other.tagline
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + tagline.hashCode()
        return result
    }
}
