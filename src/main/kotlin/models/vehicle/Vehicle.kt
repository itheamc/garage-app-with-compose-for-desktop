package models.vehicle

import utils.IdGenerator
import java.util.*

data class Vehicle(
    val id: String = IdGenerator.generate(10),
    var type: VehicleType = VehicleType.Unknown,
    val no: String = "",
    var owner: String = "",
    var driver: String = "Unknown",
    var servicesTaken: Int = 0,
    var noOfDiscountableServices: Int = 0,
    val addedOn: Long = Date().time
)


val dummyVehicles = listOf<Vehicle>(
    Vehicle(
        id = "gYQnzbN4Bs",
        type = VehicleType.Tipper,
        owner = "NtYEkKbdTd",
        no = "Ra 5 Pa 1144",
        noOfDiscountableServices = 10,
        servicesTaken = 15
    ),
    Vehicle(
        id = "1ACmrQ4RiZ",
        type = VehicleType.Bus,
        owner = "TfD8qrsaJv",
        no = "Ra 4 Kha 1644",
        noOfDiscountableServices = 8,
        servicesTaken = 10
    ),
    Vehicle(
        id = "E5wmBIbfTX",
        type = VehicleType.JCB,
        owner = "NtYEkKbdTd",
        no = "Ra 5 Pa 3542",
        noOfDiscountableServices = 6,
        servicesTaken = 6
    ),
    Vehicle(
        id = "WC7KdYckbE",
        type = VehicleType.Truck,
        owner = "TfD8qrsaJv",
        no = "Ra 4 Kha 1632",
        noOfDiscountableServices = 5,
        servicesTaken = 8
    ),
    Vehicle(
        id = "gtWmcDkZNV",
        type = VehicleType.Tipper,
        owner = "NtYEkKbdTd",
        no = "Ra 5 Pa 2440",
        noOfDiscountableServices = 3,
        servicesTaken = 4
    ),
    Vehicle(
        id = "cFgo65UIAb",
        type = VehicleType.JCB,
        owner = "TfD8qrsaJv",
        no = "Ra 4 Kha 9080",
        noOfDiscountableServices = 5,
        servicesTaken = 5
    ),
    Vehicle(
        id = "Uy92Fy1JBM",
        type = VehicleType.Tipper,
        owner = "NtYEkKbdTd",
        no = "Ra 5 Pa 6050",
        noOfDiscountableServices = 3,
        servicesTaken = 6
    ),
)