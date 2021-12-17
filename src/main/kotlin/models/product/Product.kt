package models.product

import utils.IdGenerator
import java.util.*

data class Product(
    val id: String = IdGenerator.generate(8),
    val name: String = "",
    val desc: String = "",
    val brand: String = "",
    val supplier: String = "",
    val images: List<String> = emptyList(),
    val price: Double = 0.0,
    val cost: Double = 0.0,
    var quantity: Int = 0,
    val category: String = "",
    val totalSold: Int = 0,
    val addedOn: Long = Date().time
)

val dummyProducts = listOf(
    Product(
        id = "JHFghkDghdFG",
        name = "20/50 Mobil",
        desc = "This is for ...",
        brand = "Lubricant",
        supplier = "ABC Traders",
        images = listOf("", "", ""),
        price = 1000.0,
        cost = 750.0,
        quantity = 10,
        category = "Mobil",
        totalSold = 0
    ),
    Product(
        id = "JKGkjGBjHJjg",
        name = "50/80 Mobil",
        desc = "This is for ...",
        brand = "Lubricant",
        supplier = "ABC Traders",
        images = listOf("", "", ""),
        price = 2000.0,
        cost = 1700.0,
        quantity = 30,
        category = "Mobil",
        totalSold = 0
    ),
    Product(
        id = "OIKHJkfGHvGH",
        name = "30/70 Mobil",
        desc = "This is for ...",
        brand = "Lubricant",
        supplier = "ABC Traders",
        images = listOf("", "", ""),
        price = 1500.0,
        cost = 1200.0,
        quantity = 25,
        category = "Mobil",
        totalSold = 0
    ),
    Product(
        id = "opjnKGuyfhvgh",
        name = "35/25 Mobil",
        desc = "This is for ...",
        brand = "Lubricant",
        supplier = "ABC Traders",
        images = listOf("", "", ""),
        price = 500.0,
        cost = 350.0,
        quantity = 29,
        category = "Mobil",
        totalSold = 0
    )
)