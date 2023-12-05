package org.massicer.infrastructure.repositories

import org.massicer.domain.Item.Beer
import org.massicer.domain.repositories.BeerRepository

class InMemoryBeerRepository : BeerRepository {

    private val beers = setOf(
        Beer("Lager", "Crisp and Clean", 4.5f, 20u, setOf("Grilled Chicken")),
        Beer("IPA", "Bold and Hoppy", 6.0f, 60u, setOf("Spicy Tacos")),
        Beer("Stout", "Rich and Roasty", 7.2f, 40u, setOf("Chocolate Brownie")),
        Beer("Pale Ale", "Citrus Burst", 5.5f, 35u, setOf("Fish and Chips")),
        Beer("Wheat Beer", "Light and Fruity", 5.0f, 25u, setOf("Caprese Salad")),
        Beer("Pilsner", "Classic and Refreshing", 4.2f, 30u, setOf("Caesar Salad")),
        Beer("Saison", "Spicy and Complex", 6.8f, 25u, setOf("Grilled Shrimp")),
        Beer("Porter", "Velvety Dark", 6.5f, 35u, setOf("BBQ Ribs")),
        Beer("Red Ale", "Malts and Caramel", 5.8f, 28u, setOf("Beef Stew")),
        Beer("Amber Ale", "Balanced and Toasty", 5.4f, 32u, setOf("Grilled Cheese")),
        Beer("Session IPA", "Easygoing Hop Flavor", 4.0f, 40u, setOf("Sushi")),
        Beer("Belgian Tripel", "Fruity and Spicy", 8.5f, 30u, setOf("Curry Chicken")),
        Beer("Double IPA", "Intensely Hoppy", 8.0f, 80u, setOf("Spicy Wings")),
        Beer("Milk Stout", "Creamy and Sweet", 6.3f, 25u, setOf("Chocolate Cake")),
        Beer("Blonde Ale", "Light and Crisp", 4.8f, 20u, setOf("Grilled Vegetables")),
        Beer("Scotch Ale", "Rich and Malty", 7.5f, 35u, setOf("Steak")),
        Beer("Hefeweizen", "Banana and Clove", 5.2f, 15u, setOf("Bratwurst")),
        Beer("American Brown Ale", "Toasty and Nutty", 6.0f, 30u, setOf("Pulled Pork")),
        Beer("Barleywine", "Bold and Complex", 10.0f, 50u, setOf("Blue Cheese")),
        Beer("Sour Ale", "Tart and Funky", 4.5f, 10u, setOf("Cheese Plate"))
    )

    override fun getRandom(): Beer = beers.random()
}
