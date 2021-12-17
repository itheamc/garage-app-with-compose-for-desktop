package ui.screens.transactionscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.customer.Customer
import models.offer.Offer
import models.product.Product
import models.services.Service
import models.staff.Staff
import models.vehicle.Vehicle
import models.vehicle.VehicleType
import ui.components.image.AsyncImage
import ui.components.image.ImageTransformation
import ui.components.textfield.CustomTextField


/**
 * Staffs dropdown
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun StaffsDropdown(
    onSelected: (List<Staff>) -> Unit = { },
    label: String,
    lists: List<Staff> = emptyList()
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var staffs by rememberSaveable {
        mutableStateOf(emptyList<Staff>())
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(key1 = value, block = {
        launch {
            staffs = if (textFieldValue.text.isNotBlank()) {
                if (textFieldValue.text.split(", ").size > 1) {
                    if (textFieldValue.text.substringAfterLast(", ").isBlank()) {
                        emptyList()
                    } else {
                        lists.filter { staff ->
                            staff.name.lowercase()
                                .contains(textFieldValue.text.substringAfterLast(", ").lowercase())
                        }
                    }
                } else {
                    lists.filter { staff ->
                        staff.name.lowercase()
                            .contains(textFieldValue.text.substringAfterLast(", ").lowercase())
                    }
                }
            } else {
                emptyList()
            }
            expanded = staffs.isNotEmpty()
        }
    })


    LaunchedEffect(key1 = expanded, block = {
        if (!expanded) {
            onSelected(
                selectedStaffs(
                    names = textFieldValue.text
                        .substringBeforeLast(", ")
                        .split(", "),
                    staffs = lists
                )
            )
        }
    })

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                value = it.text
            },
            label = label,
        )

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                staffs.forEach { staff ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true
                            ) {
                                val v = if (!textFieldValue.text.contains(",")) {
                                    "${staff.name}, "
                                } else if (textFieldValue.text.contains(staff.name)) {
                                    textFieldValue.text
                                } else {
                                    "${textFieldValue.text.substringBeforeLast(", ")}, ${staff.name}, "
                                }
                                textFieldValue = TextFieldValue(
                                    text = v,
                                    selection = TextRange(v.length),
                                )
                                value = v
                            },
                        text = {
                            Text(
                                text = staff.name,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        },
                        icon = {
                            AsyncImage(
                                modifier = Modifier.size(36.dp),
                                url = staff.image,
                                contentDescription = staff.name,
                                imageTransformation = ImageTransformation.Circle
                            )
                        }
                    )
                }
            }
        }
    }

}


/**
 * Customers Dropdown
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun CustomersDropdown(
    onSelected: (Customer) -> Unit = { },
    label: String,
    lists: List<Customer> = emptyList()
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var customers by rememberSaveable {
        mutableStateOf(emptyList<Customer>())
    }

    var selected by rememberSaveable {
        mutableStateOf(false)
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(key1 = value, block = {
        launch {
            customers = if (textFieldValue.text.isNotBlank() && textFieldValue.text.length <= 8) {
                lists.filter { customer ->
                    customer.name.lowercase().contains(textFieldValue.text.lowercase())
                }
            } else {
                emptyList()
            }

            expanded = customers.isNotEmpty()

            if (!expanded && textFieldValue.text.isNotBlank() && textFieldValue.text.length > 5) {
                delay(500)
                if (!selected) {
                    val names = textFieldValue.text.trim().split(", ")
                    onSelected(
                        Customer(
                            name = names[0],
                            phone = "",
                            rel = if (names.size > 1) names[1] else "customer"
                        )
                    )
                }
            } else {
                selected = false
            }
        }

    })


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                value = it.text
                textFieldValue = it
            },
            label = label
        )

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                customers.forEach { customer ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true,
                                onClick = {
                                    onSelected(customer)
                                    selected = true
                                    textFieldValue = TextFieldValue(
                                        text = customer.name,
                                        selection = TextRange((customer.name.length))
                                    )
                                    value = customer.name
                                }
                            ),
                        text = {
                            Text(
                                text = customer.name,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        },
                        icon = {
                            AsyncImage(
                                modifier = Modifier.size(36.dp),
                                url = customer.image,
                                contentDescription = customer.name,
                                imageTransformation = ImageTransformation.Circle
                            )
                        }
                    )
                }
            }
        }
    }

}

/**
 * Vehicle Types Dropdown Composable
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun VehicleTypeDropdown(
    onSelected: (VehicleType) -> Unit = { },
    label: String,
    lists: List<VehicleType> = emptyList()
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var types by rememberSaveable {
        mutableStateOf(emptyList<VehicleType>())
    }

    var selected by rememberSaveable {
        mutableStateOf(false)
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(key1 = value, block = {
        launch {
            types = if (textFieldValue.text.isNotBlank()) {
                lists.filter { type ->
                    type.name.lowercase().contains(textFieldValue.text.lowercase())
                }
            } else {
                selected = false
                emptyList()
            }

            expanded = types.isNotEmpty() && !selected
        }
    })


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                value = it.text
            },
            label = label,
        )

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                types.forEach { item ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true,
                                onClick = {
                                    onSelected(item)
                                    selected = true
                                    textFieldValue = TextFieldValue(
                                        text = item.label,
                                        selection = TextRange((item.label.length))
                                    )
                                    value = item.label
                                }
                            ),
                        text = {
                            Text(
                                text = item.label,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        },
                        icon = {
                            AsyncImage(
                                modifier = Modifier.size(36.dp),
                                url = item.image,
                                contentDescription = item.label,
                                imageTransformation = ImageTransformation.Circle
                            )
                        }
                    )
                }
            }
        }
    }

}


/**
 * Vehicles Dropdown Composable
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun VehiclesDropdown(
    onSelected: (Vehicle) -> Unit = { },
    label: String,
    lists: List<Vehicle> = emptyList(),
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var vehicles by rememberSaveable {
        mutableStateOf(emptyList<Vehicle>())
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }

    var selected by rememberSaveable {
        mutableStateOf(false)
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(key1 = value, block = {
        launch {
            vehicles = if (textFieldValue.text.isNotBlank() && textFieldValue.text.length <= 4) {
                lists.filter { vehicle ->
                    vehicle.no.lowercase().contains(textFieldValue.text.lowercase())
                }
            } else {
                emptyList()
            }

            expanded = vehicles.isNotEmpty()

            if (!expanded && textFieldValue.text.isNotBlank() && textFieldValue.text.length > 10) {
                delay(500)
                if (!selected) {
                    onSelected(
                        Vehicle(
                            no = textFieldValue.text
                        )
                    )
                }
            } else {
                selected = false
            }
        }

    })


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                value = it.text
            },
            label = label,
        )

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                vehicles.forEach { vehicle ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true,
                                onClick = {
                                    onSelected(vehicle)
                                    selected = true
                                    textFieldValue = TextFieldValue(
                                        text = vehicle.no,
                                        selection = TextRange((vehicle.no.length))
                                    )
                                    value = vehicle.no
                                }
                            ),
                        text = {
                            Text(
                                text = vehicle.no,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        }
                    )
                }
            }
        }
    }

}

/**
 * Offers Dropdown Composable
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun OffersDropdown(
    onSelected: (Offer) -> Unit = { },
    label: String,
    lists: List<Offer> = emptyList(),
    v: Offer,
    enabled: Boolean = true
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var offers by rememberSaveable {
        mutableStateOf(emptyList<Offer>())
    }

    var selected by rememberSaveable {
        mutableStateOf(true)
    }

    var value by rememberSaveable {
        mutableStateOf(v.title)
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(key1 = v, block = {
        textFieldValue = TextFieldValue(
            text = v.title,
            selection = TextRange(v.title.length)
        )
        value = v.title
    })

    LaunchedEffect(key1 = value, block = {
        launch {
            offers = if (textFieldValue.text.isNotBlank()) {
                lists.filter { offer ->
                    offer.title.lowercase().contains(textFieldValue.text.lowercase())
                }
            } else {
                selected = false
                emptyList()
            }

            expanded = offers.isNotEmpty() && !selected
        }
    })

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                value = it.text
            },
            label = label,
            enabled = enabled
        )

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                offers.forEach { offer ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true,
                                onClick = {
                                    onSelected(offer)
                                    selected = true
                                    textFieldValue = TextFieldValue(
                                        text = offer.title,
                                        selection = TextRange((offer.title.length))
                                    )
                                    value = offer.title
                                }
                            ),
                        text = {
                            Text(
                                text = offer.title,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        }
                    )
                }
            }
        }
    }

}


/**
 * Services Offered dropdown
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ServicesDropdown(
    onSelected: (List<Service>) -> Unit = { },
    label: String,
    lists: List<Service> = emptyList()
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var services by rememberSaveable {
        mutableStateOf(emptyList<Service>())
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(key1 = value, block = {
        launch {
            services = if (textFieldValue.text.isNotBlank()) {
                if (textFieldValue.text.split(", ").size > 1) {
                    if (textFieldValue.text.substringAfterLast(", ").isBlank()) {
                        emptyList()
                    } else {
                        lists.filter { service ->
                            service.name.lowercase()
                                .contains(textFieldValue.text.substringAfterLast(", ").lowercase())
                        }
                    }
                } else {
                    lists.filter { service ->
                        service.name.lowercase()
                            .contains(textFieldValue.text.substringAfterLast(", ").lowercase())
                    }
                }
            } else {
                emptyList()
            }
            expanded = services.isNotEmpty()
        }
    })


    LaunchedEffect(key1 = expanded, block = {
        if (!expanded) {
            onSelected(
                selectedServices(
                    names = textFieldValue.text
                        .substringBeforeLast(", ")
                        .split(", "),
                    services = lists
                )
            )
        }
    })

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                value = it.text
            },
            label = label,
        )

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                services.forEach { service ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true
                            ) {
                                val v = if (!textFieldValue.text.contains(",")) {
                                    "${service.name}, "
                                } else if (textFieldValue.text.contains(service.name)) {
                                    textFieldValue.text
                                } else {
                                    "${textFieldValue.text.substringBeforeLast(", ")}, ${service.name}, "
                                }
                                textFieldValue = TextFieldValue(
                                    text = v,
                                    selection = TextRange(v.length),
                                )
                                value = v
                            },
                        text = {
                            Text(
                                text = service.name,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        }
                    )
                }
            }
        }
    }

}


/**
 * Add On Products Dropdown
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun AddOnProductsDropdown(
    onSelected: (List<Product>) -> Unit = { },
    label: String,
    lists: List<Product> = emptyList()
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var products by rememberSaveable {
        mutableStateOf(emptyList<Product>())
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(key1 = value, block = {
        launch {
            products = if (textFieldValue.text.isNotBlank()) {
                if (textFieldValue.text.split(", ").size > 1) {
                    if (textFieldValue.text.substringAfterLast(", ").isBlank()) {
                        emptyList()
                    } else {
                        lists.filter { product ->
                            product.name.lowercase()
                                .contains(textFieldValue.text.substringAfterLast(", ").lowercase())
                        }
                    }
                } else {
                    lists.filter { product ->
                        product.name.lowercase()
                            .contains(textFieldValue.text.substringAfterLast(", ").lowercase())
                    }
                }
            } else {
                emptyList()
            }
            expanded = products.isNotEmpty()
        }
    })

    LaunchedEffect(key1 = expanded, block = {
        if (!expanded) {
            onSelected(
                selectedProducts(
                    names = textFieldValue.text
                        .substringBeforeLast(", ")
                        .split(", "),
                    products = lists
                )
            )
        }
    })

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                value = it.text
            },
            label = label,
        )

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                products.forEach { item ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true,
                                onClick = {
                                    val v = if (!textFieldValue.text.contains(",")) {
                                        "${item.name}, "
                                    } else if (textFieldValue.text.contains(item.name)) {
                                        textFieldValue.text
                                    } else {
                                        "${textFieldValue.text.substringBeforeLast(", ")}, ${item.name}, "
                                    }
                                    textFieldValue = TextFieldValue(
                                        text = v,
                                        selection = TextRange((v.length))
                                    )
                                    value = v
                                }
                            ),
                        text = {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        }
                    )
                }
            }
        }
    }
}

/**
 * Functions to get the list of products ids from name list
 */
private fun selectedProducts(names: List<String>, products: List<Product>): List<Product> {
    val list = mutableSetOf<Product>()
    names.forEach { name ->
        products.forEachIndexed { _, product ->
            if (name.trim().lowercase() == product.name.trim().lowercase()) {
                list.add(product)
                return@forEachIndexed
            }
        }
    }

    return list.toList()
}


/**
 * Functions to get the list of products ids from name list
 */
private fun selectedStaffs(names: List<String>, staffs: List<Staff>): List<Staff> {
    val list = mutableSetOf<Staff>()
    names.forEach { name ->
        staffs.forEachIndexed { _, staff ->
            if (name.trim().lowercase() == staff.name.trim().lowercase()) {
                list.add(staff)
                return@forEachIndexed
            }
        }
    }

    return list.toList()
}

/**
 * Functions to get the list of products ids from name list
 */
private fun selectedServices(names: List<String>, services: List<Service>): List<Service> {
    val list = mutableSetOf<Service>()
    names.forEach { name ->
        services.forEachIndexed { _, service ->
            if (name.trim().lowercase() == service.name.trim().lowercase()) {
                list.add(service)
                return@forEachIndexed
            }
        }
    }

    return list.toList()
}
