package ui.screens.transactionscreen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogState
import androidx.compose.ui.window.rememberDialogState
import models.customer.Customer
import models.customer.customers
import models.offer.Offer
import models.offer.dummyOffers
import models.product.Product
import models.product.dummyProducts
import models.services.Service
import models.services.dummyServices
import models.staff.Staff
import models.staff.dummyStaffs
import models.transactions.Transaction
import models.vehicle.Vehicle
import models.vehicle.VehicleType
import models.vehicle.dummyVehicles
import ui.components.button.CustomButton
import ui.components.containers.ColumnContainer
import ui.components.containers.RowContainer
import ui.components.spacers.*
import ui.screens.transactionscreen.components.*
import utils.TransactionId
import utils.round
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun SalesTransactionTialog(
    onCloseRequest: () -> Unit,
    state: DialogState = rememberDialogState(size = DpSize(1000.dp, 680.dp)),
    visible: Boolean = false,
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
) {
    Dialog(
        onCloseRequest = onCloseRequest,
        visible = visible,
        state = state,
        title = "New Product",
        icon = rememberVectorPainter(Icons.Filled.AddCircle),
        resizable = false,
        onPreviewKeyEvent = onPreviewKeyEvent,
        onKeyEvent = onKeyEvent
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RowContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.Top
            ) {
                form(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(0.6f),
                )
            }
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
private val form: @Composable (Modifier) -> Unit = { modifier ->
    /**
     * Variables for other than text fields
     */
    val transId by rememberSaveable {
        mutableStateOf(TransactionId.generate())
    }
    val transDate by rememberSaveable {
        mutableStateOf(Date())
    }

    var customerId by rememberSaveable {
        mutableStateOf("")
    }

    var customerName by rememberSaveable {
        mutableStateOf("")
    }

    var rel by rememberSaveable {
        mutableStateOf("customer")
    }

    var vehicleId by rememberSaveable {
        mutableStateOf("")
    }

    var vehicleNo by rememberSaveable {
        mutableStateOf("")
    }

    /**
     * State Variables for normal text fields
     */
    var phone by rememberSaveable {
        mutableStateOf("")
    }

    var discount by rememberSaveable {
        mutableStateOf(0.0f)
    }

    var receivedAmount by rememberSaveable {
        mutableStateOf("")
    }

    var pmtMethod by rememberSaveable {
        mutableStateOf("")
    }

    var transactionAddedBy by rememberSaveable {
        mutableStateOf("itheamc")
    }


    /**
     * Variables for dropdown text fields
     */

    var customer by remember {
        mutableStateOf(
            Customer(
                id = customerId,
                name = customerName,
                phone = phone,
                rel = rel
            )
        )
    }

    var vehicleType by rememberSaveable {
        mutableStateOf(VehicleType.Unknown)
    }

    var vehicle by remember {
        mutableStateOf(
            Vehicle(
                id = vehicleId,
                no = vehicleNo,
                owner = customerId,
                type = vehicleType
            )
        )
    }

    var services by rememberSaveable {
        mutableStateOf(listOf<Service>())
    }
    var products by rememberSaveable {
        mutableStateOf(listOf<Product>())
    }

    var servicedBy by rememberSaveable {
        mutableStateOf(listOf<Staff>())
    }

    val offer by rememberSaveable {
        mutableStateOf(
            mutableListOf<Offer>()
        )
    }

    /**
     * Boolean Variables
     */
    var isNewCustomer by rememberSaveable {
        mutableStateOf(true)
    }

    var isNewVehicle by rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(keys = emptyArray(), block = {
        println("Services: $services")
        println("Products: $products")
        println("Vehicle ($isNewVehicle): $vehicle")
        println("Customer ($isNewCustomer): $customer")

    })


    // First Container
    ColumnContainer(
        modifier = modifier.padding(
            end = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val space by animateIntAsState(
            targetValue = if (services.isNotEmpty()) 88 else 54,
            animationSpec = tween(300)
        )
        SpacerX(space)
        CustomersDropdown(
            label = "Customer Name",
            onSelected = {
                customer = it
                customerId = it.id
                customerName = it.name
                rel = it.rel
                if (it.phone.trim().isNotBlank()) {
                    phone = it.phone
                    isNewCustomer = false
                } else {
                    isNewCustomer = true
                }
            },
            lists = customers
        )

        TransactionTextInput(
            value = phone,
            onValueChange = {
                phone = it
                if (isNewCustomer && it.length == 10) {
                    customer = customer.copy(
                        phone = it
                    )
                }
            },
            label = "Mobile Number",
            keyboardType = KeyboardType.Phone,
            enabled = isNewCustomer
        )

        VehicleTypeDropdown(
            label = "Vehicle Category",
            onSelected = {
                vehicleType = it
                println("Vehicle: $it")
            },
            lists = VehicleType.values().asList(),
        )

        VehiclesDropdown(
            label = "Vehicle",
            onSelected = {
                if (it.owner.trim().isNotBlank()) {
                    vehicle = it
                    isNewVehicle = false
                } else {
                    vehicle = it.copy(
                        owner = customerId,
                        type = vehicleType,
                    )
                    isNewVehicle = true
                }
                vehicleNo = it.no
                vehicleId = it.id
                offer.add(
                    0, Offer.eligible(
                        noOfDiscountableServices = it.noOfDiscountableServices,
                        dummyOffers
                    )
                )
                discount =
                    if (offer.isNotEmpty()) offer[0].discount(noOfDiscountableServices = it.noOfDiscountableServices) else 0f
            },
            lists = filterVehicles(dummyVehicles, customerId, vehicleType = vehicleType),
        )

        ServicesDropdown(
            label = "Services Offered",
            onSelected = {
                services = it
                println("Services Offered: $it")
            },
            lists = dummyServices,
        )

        AddOnProductsDropdown(
            label = "Add On Products",
            onSelected = {
                products = it
                println("Ads On Product: $it")
            },
            lists = dummyProducts,
        )

        OffersDropdown(
            label = "Offer Type",
            v = if (offer.isNotEmpty()) offer[0] else Offer(
                title = "N/A"
            ),
            onSelected = {
                offer.add(0, it)
            },
            lists = dummyOffers,
            enabled = false
        )

        TransactionTextInput(
            value = "${(discount * 100).roundToInt()}%",
            label = "Discount Percentage",
            enabled = false,
        )
    }

    // Second Container
    ColumnContainer(
        modifier = modifier.padding(
            start = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val unconditionalDiscount = 0.1f
        val amountOfProducts = if (products.isNotEmpty()) products.sumOf { it.price } else 0.0
        val amountOfServices = if (services.isNotEmpty()) services.maxOf { it.charge } else 0.0
        val amountOfGrossTotal = amountOfProducts + amountOfServices
        val amountOfDiscount =
            (amountOfProducts * 0.015) + if (amountOfProducts >= amountOfServices) (amountOfServices * discount) else (amountOfServices * unconditionalDiscount)
        val amountOfNetTotal = amountOfGrossTotal - amountOfDiscount

        SpacerX(10)
        ColumnContainer(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "Transaction Id#  $transId",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
            )
            Spacer4()
            Text(
                text = "Date:  ${SimpleDateFormat.getDateInstance().format(Date())}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Light),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
            )
            SpacerX(30)

            text("Products Total#", amountOfProducts)
            text("Services Total#", amountOfServices)
            Divider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            text("Total Amount#", amountOfGrossTotal)
            text("Discount#", amountOfDiscount)

            AnimatedVisibility(
                visible = amountOfServices > 0
            ) {
                ColumnContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    SpacerX(6)
                    Text(
                        text = buildAnnotatedString {
                            append("Note: Discount of 1.5% on Products & ")
                            withStyle(
                                SpanStyle(
                                    textDecoration = if (amountOfProducts < amountOfServices) TextDecoration.LineThrough else null,
                                    fontStyle = if (amountOfProducts < amountOfServices) FontStyle.Normal else null,
                                    fontWeight = if (amountOfProducts < amountOfServices) FontWeight.ExtraBold else null
                                )
                            ) {
                                append("${(discount * 100.0).round(2)}")
                            }
                            append(if (amountOfProducts < amountOfServices) " " else "")
                            append(
                                "${
                                    if (amountOfProducts < amountOfServices) (unconditionalDiscount * 100.0).round(
                                        2
                                    ) else ""
                                }% on Services are applicable"
                            )

                        },
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp
                        ),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            Divider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            text("Net Total#", amountOfNetTotal)
            Spacer12()
        }

        TransactionTextInput(
            value = receivedAmount,
            onValueChange = {
                receivedAmount = it
            },
            label = "Received Amount",
            keyboardType = KeyboardType.Number
        )

        TransactionTextInput(
            value = pmtMethod,
            onValueChange = {
                pmtMethod = it
            },
            label = "Payment Method"
        )

        StaffsDropdown(
            label = "Served By",
            onSelected = {
                servicedBy = it
            },
            lists = dummyStaffs,
        )

        TransactionTextInput(
            value = transactionAddedBy,
            onValueChange = {
                transactionAddedBy = it
            },
            label = "Verified By",
        )

        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            label = "Save",
            onClick = {
                val transaction = Transaction(
                    id = transId,
                    transactionDate = transDate.time,
                    customerId = customerId,
                    vehicle = vehicleType.name,
                    vehicleId = vehicleId,
                    typeOfService = if (services.isNotEmpty()) services.map { it.id } else emptyList(),
                    addOnProducts = if (products.isNotEmpty()) products.map { it.id } else emptyList(),
                    grossAmount = amountOfGrossTotal.roundToInt(),
                    netAmount = amountOfNetTotal.roundToInt(),
                    discount = discount,
                    offer = if (offer.isNotEmpty()) offer[0].id else "",
                    paymentDue = amountOfNetTotal.roundToInt() - (if (receivedAmount.trim()
                            .isNotBlank()
                    ) receivedAmount.toInt() else 0),
                    paymentMethod = pmtMethod,
                    servicedBy = if (servicedBy.isNotEmpty()) servicedBy.map { it.id } else emptyList(),
                    transactionAddedBy = transactionAddedBy
                )

                println("Transaction: $transaction")
            }
        )
    }
}


/**
 * Composable to draw a text
 */
private val text: @Composable (String, Double) -> Unit = { label, value ->
    RowContainer(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
            text = "Rs. ${value.roundToInt()}",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
            fontStyle = FontStyle.Italic,
        )
    }
}





