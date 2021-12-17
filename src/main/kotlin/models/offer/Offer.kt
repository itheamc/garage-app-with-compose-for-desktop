package models.offer

import utils.IdGenerator
import java.util.*

data class Offer(
    val id: String = IdGenerator.generate(6),
    val title: String = "",
    val multiplier: Float = 0f,
    val listOfValidTimes: List<Int> = emptyList(),
    val desc: String = "",
    val startedOn: Long = Date().time,
    val endedOn: Long = 0
) {
    fun discount(
        noOfDiscountableServices: Int
    ): Float {
        return when (noOfDiscountableServices + 1) {
            in 2..10 -> {
                noOfDiscountableServices * multiplier
            }
            else -> {
                multiplier
            }
        }
    }

    companion object {
        fun eligible(noOfDiscountableServices: Int, offers: List<Offer>): Offer {
            val filtered =
                offers.filter { offer -> offer.listOfValidTimes.contains(noOfDiscountableServices + 1) }
            return if (filtered.isNotEmpty()) filtered[0] else offers.filter { it.listOfValidTimes.isEmpty() }[0]
        }
    }
}

/**
 * Dummy Offers
 */
val dummyOffers = listOf<Offer>(
    Offer(
        id = "xcfxzE",
        title = "Welcome Offer",
        multiplier = 1.0f,
        listOfValidTimes = listOf(1),
        desc = "This offer is eligible for all the customers who are visiting our repairing shop first time."
    ),
    Offer(
        id = "GYzjH3",
        title = "Welcome Back Offer",
        multiplier = 0.1f,
        listOfValidTimes = listOf(2, 3, 4, 5, 6, 7, 8, 9, 10),
        desc = "This offer is eligible for all the customers who are visiting our repairing shop other than first time"
    ),
    Offer(
        id = "H807We",
        title = "Friendship Started Offer",
        multiplier = 1.0f,
        listOfValidTimes = listOf(11),
        desc = ""
    ),
    Offer(
        id = "H807We",
        title = "Friendship Offer",
        multiplier = 0.1f,
        listOfValidTimes = (12..500).toList(),
        desc = "This offer is eligible for all the customers who has already visited our repairing shop not less than 12 times"
    ),
    Offer(
        id = "tXXElT",
        title = "Not Available",
        multiplier = 0f,
        listOfValidTimes = emptyList(),
        desc = "No Offer"
    )
)