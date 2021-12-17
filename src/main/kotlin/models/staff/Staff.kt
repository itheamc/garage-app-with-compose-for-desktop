package models.staff


import utils.IdGenerator
import java.util.*

data class Staff(
    val id: String = IdGenerator.generate(6),
    val name: String = "",
    val image: String = "",
    val position: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = "",
    val joinedOn: Long = Date().time
)


/**
 * ----------------------------------------------
 * Demo Staffs
 */

val dummyStaffs = listOf<Staff>(
    Staff(
        id = "U0caYB",
        name = "Binti Ram Chaudhary",
        position = StaffPost.HeavyVehicleMechanic.name,
        address = "Gadhawa - 7, Kanchhi Gaun",
        phone = "+9779818732591",
        email = "brc2727@gmail.com",
        image = "https://unsplash.com/photos/mVGW8j9rrC4/download?force=true&w=640"
    ),
    Staff(
        id = "pgJ3J9",
        name = "Rolodex Chaudhary",
        position = StaffPost.Accountant.name,
        address = "Gadhawa - 7, Kanchhi Gaun",
        phone = "+9779818732591",
        email = "brc2727@gmail.com",
        image = "https://unsplash.com/photos/6wWiZlA2n0Q/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8MjN8fG1vZGVsfGVufDB8fHx8MTYzODAxMzcxNw&force=true&w=640"
    ),
    Staff(
        id = "YPPoPK",
        name = "ABC Chaudhary",
        position = StaffPost.Operator.name,
        address = "Gadhawa - 7, Kanchhi Gaun",
        phone = "+9779818732591",
        email = "brc2727@gmail.com",
        image = "https://unsplash.com/photos/WyDr1KFS23Y/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MDc3Mzk5&force=true&w=640"
    ), Staff(
        id = "fbdrgZ",
        name = "XYZ Chaudhary",
        position = StaffPost.LightVehicleMechanic.name,
        address = "Gadhawa - 7, Kanchhi Gaun",
        phone = "+9779818732591",
        email = "brc2727@gmail.com",
        image = "https://unsplash.com/photos/R21SyyJDFgc/download?force=true&w=640"
    ),
    Staff(
        id = "Noy5nf",
        name = "Dipendar Chaudhary",
        position = StaffPost.Assistant.name,
        address = "Gadhawa - 7, Kanchhi Gaun",
        phone = "+9779822922854",
        email = "dipechy@gmail.com",
        image = "https://unsplash.com/photos/iFgRcqHznqg/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8MTV8fG1vZGVsfHwwfHx8fDE2MzgwMjc4NDg&force=true&w=640"
    ),
    Staff(
        id = "Au49YR",
        name = "Sudip Chaudhary",
        position = StaffPost.Assistant.name,
        address = "Gadhawa - 7, Kanchhi Gaun",
        phone = "+9779818034512",
        email = "sudipchy@gmail.com",
        image = "https://unsplash.com/photos/et_78QkMMQs/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MDc3OTU4&force=true&w=640"
    ),
    Staff(
        id = "8iNQgd",
        name = "Amit Chaudhary",
        position = StaffPost.Manager.name,
        address = "Gadhawa - 7, Kanchhi Gaun",
        phone = "+97799822984969",
        email = "itheamc@gmail.com",
        image = "https://unsplash.com/photos/ENMb0LZNzxw/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8MzJ8fG1vZGVsfGVufDB8fHx8MTYzODAxMzcxNw&force=true&w=640"
    )
)

/**
 * StaffsInfo
 */
enum class StaffInfo {
    Name,
    Post,
    Address,
    Phone,
    Email;
}


/**
 * Staff posts
 */
enum class StaffPost { Manager, Operator, LightVehicleMechanic, HeavyVehicleMechanic, Assistant, Accountant }
