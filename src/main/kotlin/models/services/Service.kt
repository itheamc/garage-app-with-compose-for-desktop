package models.services

import utils.IdGenerator
import java.util.*

data class Service(
    val id: String = IdGenerator.generate(10),
    val name: String = "",
    val charge: Double = 0.0,
    val addedOn: Long = Date().time
) {
    companion object {
        fun charge(sId: String, services: List<Service>): Double {
            return services.filter { it.id == sId }[0].charge
        }
    }
}


/**
 * Dummy Service List
 */
val dummyServices = listOf<Service>(
    Service(
        id = "Y4WzHAXKfr",
        name = "Engine Repairment",
        charge = 1500.0
    ),
    Service(
        id = "Y5anWQbPXe",
        name = "Oil Filter Replacement",
        charge = 500.0
    ),
    Service(
        id = "S0uAHXyZdR",
        name = "Wiper Blades Replacement",
        charge = 250.0
    ),
    Service(
        id = "lGlyzH2SPf",
        name = "Air Filter Replacement",
        charge = 300.0
    ),
    Service(
        id = "n2DL2rRYgf",
        name = "Servicing",
        charge = 1000.0
    ),
    Service(
        id = "XwdFQScLDw",
        name = "Tire Repairment",
        charge = 150.0
    ),
    Service(
        id = "rijgkbJLim",
        name = "Battery Replacement",
        charge = 175.0
    ),
    Service(
        id = "XtMIm6Db2y",
        name = "Break Maintenance",
        charge = 450.0
    ),
    Service(
        id = "BhK6A0PG0n",
        name = "Engine Tune-Up",
        charge = 1250.0
    ),
    Service(
        id = "jTXoYN9d8u",
        name = "Wheel Balancing",
        charge = 400.0
    ),
    Service(
        id = "nuCs449HeY",
        name = "Glass Replacement",
        charge = 1200.0
    ),
    Service(
        id = "aPeOnZdicI",
        name = "Hydraulic Repairment",
        charge = 1550.0
    ),
    Service(
        id = "l0E7gqEyat",
        name = "Bearing Replacement",
        charge = 350.0
    ),
    Service(
        id = "XACtrM9f0e",
        name = "Other Small Parts Replacement",
        charge = 200.0
    ),
    Service(
        id = "Wy1Eg2ogzg",
        name = "Other Minor Repairment",
        charge = 100.0
    )
)
