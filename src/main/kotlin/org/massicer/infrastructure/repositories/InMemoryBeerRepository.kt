package org.massicer.infrastructure.repositories

import jakarta.enterprise.context.ApplicationScoped
import org.massicer.domain.Item.Beer
import org.massicer.domain.repositories.BeerRepository

@ApplicationScoped
class InMemoryBeerRepository : BeerRepository {

    private val beers = setOf(
        Beer("Lager", "Crisp and Clean", 4.5f, 20u, "Grilled Chicken"),
        Beer("IPA", "Bold and Hoppy", 6.0f, 60u, "Spicy Tacos"),
        Beer("Stout", "Rich and Roasty", 7.2f, 40u, "Chocolate Brownie"),
        Beer("Pale Ale", "Citrus Burst", 5.5f, 35u, "Fish and Chips"),
        Beer("Wheat Beer", "Light and Fruity", 5.0f, 25u, "Caprese Salad"),
        Beer("Pilsner", "Classic and Refreshing", 4.2f, 30u, "Caesar Salad"),
        Beer("Saison", "Spicy and Complex", 6.8f, 25u, "Grilled Shrimp"),
        Beer("Porter", "Velvety Dark", 6.5f, 35u, "BBQ Ribs"),
        Beer("Red Ale", "Malts and Caramel", 5.8f, 28u, "Beef Stew"),
        Beer("Amber Ale", "Balanced and Toasty", 5.4f, 32u, "Grilled Cheese"),
        Beer("Session IPA", "Easygoing Hop Flavor", 4.0f, 40u, "Sushi"),
        Beer("Belgian Tripel", "Fruity and Spicy", 8.5f, 30u, "Curry Chicken"),
        Beer("Double IPA", "Intensely Hoppy", 8.0f, 80u, "Spicy Wings"),
        Beer("Milk Stout", "Creamy and Sweet", 6.3f, 25u, "Chocolate Cake"),
        Beer("Blonde Ale", "Light and Crisp", 4.8f, 20u, "Grilled Vegetables"),
        Beer("Scotch Ale", "Rich and Malty", 7.5f, 35u, "Steak"),
        Beer("Hefeweizen", "Banana and Clove", 5.2f, 15u, "Bratwurst"),
        Beer("American Brown Ale", "Toasty and Nutty", 6.0f, 30u, "Pulled Pork"),
        Beer("Barleywine", "Bold and Complex", 10.0f, 50u, "Blue Cheese"),
        Beer("Sour Ale", "Tart and Funky", 4.5f, 10u, "Cheese Plate")
    )

    override fun getRandom(): Beer = beers.random()
}
