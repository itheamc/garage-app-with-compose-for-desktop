package ui.screens.transactionscreen.components


import models.vehicle.Vehicle
import models.vehicle.VehicleType

/**
 * Function to filter the vehicle type
 * as per the user id
 * It will filter the vehicle type that customer have
 */
fun filterVehicleTypes(vehicles: List<Vehicle>, customerId: String): List<VehicleType> {
    val sets = mutableSetOf<VehicleType>()

    vehicles.forEach {
        if (customerId == it.owner) {
            sets.add(it.type)
        }
    }

    return sets.toList()
}


/**
 * Function to filter the vehicles that the customer have
 */
fun filterVehicles(vehicles: List<Vehicle>, customerId: String, vehicleType: VehicleType): List<Vehicle> {
    val list = mutableListOf<Vehicle>()

    vehicles.forEach {
        if (it.owner == customerId && it.type == vehicleType) {
            list.add(it)
        }
    }

    return list
}