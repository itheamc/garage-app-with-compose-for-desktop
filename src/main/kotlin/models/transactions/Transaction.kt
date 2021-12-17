package models.transactions

import utils.TransactionId
import java.util.*

data class Transaction(
    val id: String = TransactionId.generate(),  // Auto Generated
    val transactionDate: Long = Date().time,  // Auto Picked
    val customerId: String = "",     // If existing picked From there else added new own automatically and picked from there
    val vehicle: String = "",        // From Input
    val vehicleId: String = "",  // From Input
    val typeOfService: List<String> = emptyList(),    // From Input - Dropdown from pre-listed vehicle
    val addOnProducts: List<String> = emptyList(),     // Selected from the available products
    val grossAmount: Int = 0,                 // Auto Calculated
    val netAmount: Int = 0,
    val discount: Float = 0f,                // Selected automatically from the available offer
    val offer: String = "",              // Offer type included with this transaction - selected automatically
    val paymentDue: Int = 0,             // Calculated automatically
    val paymentMethod: String = "Cash",          // Selected from the predefined list - ["Online", "Offline"]
    val servicedBy: List<String> = emptyList(),       // Selected from the list of staffs
    val transactionAddedBy: String = ""     // User added this transaction selected automatically
)
