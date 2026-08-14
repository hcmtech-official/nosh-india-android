package com.noshindia.app.data

enum class PricingUnit { PIECE, KG }

data class Product(
    val name: String,
    val description: String,
    val unitPrice: Double,
    val unit: PricingUnit,
    val imageDescription: String
)

val products = listOf(
    Product(
        name = "Royal Gulab Jamun",
        description = "Melt-in-your-mouth golden spheres made from pure khoya, soaked in " +
            "aromatic rose and cardamom syrup. The ultimate comfort sweet.",
        unitPrice = 1.50,
        unit = PricingUnit.PIECE,
        imageDescription = "Gulab Jamun"
    ),
    Product(
        name = "Traditional Milk Cake",
        description = "A rich, grainy, and decadent sweet made by slow-reducing full-cream " +
            "milk with a hint of ghee. Topped with crushed pistachios.",
        unitPrice = 32.00,
        unit = PricingUnit.KG,
        imageDescription = "Milk Cake"
    )
)

// Matches the live site's fixed quantity/weight tiers exactly.
val pieceTiers = listOf(10, 20, 30, 50, 100)
val kgTiers = listOf(0.5, 1.0, 2.0, 5.0)
