package models.customer

import utils.IdGenerator
import java.util.*

data class Customer(
    val id: String = IdGenerator.generate(10),
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val image: String = "",
    val rel: String = "customer",
    val totalServices: Int = 1,
    val addedOn: Long = Date().time
)


val customers = listOf(
    Customer(
        id = "NtYEkKbdTd",
        name = "Someone Customer",
        phone = "9800110011",
        email = "example@email.com",
        image = "https://unsplash.com/photos/6W4F62sN_yI/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzMwNDI1&force=true&w=640",
        totalServices = 4
    ),
    Customer(
        id = "Bwcq9k8yF3",
        name = "Another Customer",
        phone = "9800002222",
        email = "another@email.com",
        image = "https://unsplash.com/photos/et_78QkMMQs/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzMwNDMz&force=true&w=640",
        totalServices = 2
    ),
    Customer(
        id = "TfD8qrsaJv",
        name = "Last Customer",
        phone = "9855559999",
        email = "last@email.com",
        image = "https://unsplash.com/photos/6wWiZlA2n0Q/download?force=true&w=640",
        totalServices = 8
    )
)