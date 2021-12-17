package ui.screens.transactionscreen.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import models.customer.Customer
import models.customer.customers
import models.product.Product
import models.product.dummyProducts
import models.services.Service
import models.services.dummyServices
import models.transactions.Transaction
import models.vehicle.Vehicle
import models.vehicle.dummyVehicles
import ui.components.containers.ColumnContainer
import ui.components.containers.RowContainer
import ui.components.image.AsyncImage
import ui.components.image.ImageTransformation
import ui.components.spacers.Spacer24
import ui.components.spacers.Spacer4
import ui.components.spacers.Spacer8
import utils.round
import utils.toDate


@ExperimentalAnimationApi
@Composable
fun TransactionDetailScreen(transaction: Transaction = Transaction(id="DEC15202113258PM", transactionDate=1639554478106, customerId="NtYEkKbdTd", vehicle="Tipper", vehicleId="gYQnzbN4Bs", typeOfService= listOf("Y4WzHAXKfr", "Y5anWQbPXe", "lGlyzH2SPf"), addOnProducts= listOf("JKGkjGBjHJjg", "JHFghkDghdFG"), grossAmount=4500, netAmount=2955, discount=1.0f, offer="H807We", paymentDue=0, paymentMethod="Cash", servicedBy= listOf("U0caYB", "pgJ3J9"), transactionAddedBy="itheamc")) {

    val products = dummyProducts.filterIndexed { index, _ -> index < 3 }
    val services = dummyServices.filterIndexed { index, _ -> index < 4 }
    val customer = customers[0]
    val vehicle = dummyVehicles[0]

    ColumnContainer(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.Start
    ) {

        /**
         * Date and Transaction Id Container
         */
        Text(
            text = buildAnnotatedString {
                append("Transaction Id: ")
                withStyle(
                    SpanStyle(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(transaction.id)
                }
            },
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Text(
            text = buildAnnotatedString {
                append("Transaction Date: ")
                withStyle(
                    SpanStyle(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(transaction.transactionDate.toDate())
                }
            },
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        /**
         * Spacer8
         */
        Spacer8()

        /**
         * Divider
         */

        /**
         * Divider
         */
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )

        /**
         * Spacer8
         */
        Spacer8()

        /**
         * Other transaction details container
         */
        ColumnContainer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                ),
            horizontalAlignment = Alignment.Start
        ) {
            // Customer
            heading("Customer", null)
            text1(customer, null)

            // Spacer between Customer and Vehicle
            Spacer24()

            // Vehicle
            heading("Vehicle", null)
            text1(null, vehicle)

            // Spacer between Vehicle and Services
            Spacer24()

            // Services
            heading("Services", services.maxOf { it.charge })
            services.forEach { service ->
                text2(null, service)
            }

            // Spacer between Services and Products
            Spacer24()

            // Services
            heading("Products", products.sumOf { it.price })
            products.forEach { product ->
                text2(product, null)
            }
        }
    }
}

/**
 * Heading
 */
private val heading: @Composable (String, Double?) -> Unit = { label, value ->
    RowContainer(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.ExtraBold
            )
        )

        Text(
            text = if (value == null) "" else "Rs. ${value.round(2)}",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
    Spacer4()
}

/**
 * Text for customer or vehicle
 */
@ExperimentalAnimationApi
private val text1: @Composable (Customer?, Vehicle?) -> Unit = { customer, vehical ->
    RowContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(36.dp),
            url = customer?.image ?: (vehical?.type?.image ?: ""),
            contentDescription = customer?.name ?: vehical?.no,
            imageTransformation = ImageTransformation.Circle
        )
        Spacer4()
        ColumnContainer(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = customer?.name ?: (vehical?.type?.label ?: ""),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.9f
                    ),
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = customer?.phone ?: (vehical?.no ?: ""),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.8f
                    ),
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

/**
 * Text for Services or Products
 */
@ExperimentalAnimationApi
private val text2: @Composable (Product?, Service?) -> Unit = { product, service ->
    RowContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, start = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(28.dp),
            url = "https://unsplash.com/photos/V37iTrYZz2E/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8M3x8YXV0byUyMHJlcGFpcnx8MHx8fHwxNjM4NjcxMjE1&force=true&w=640",
            contentDescription = product?.name ?: service?.name,
            contentScale = ContentScale.Crop
        )
        Spacer4()
        ColumnContainer(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product?.name ?: (service?.name ?: ""),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.9f
                    ),
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = (product?.price ?: (service?.charge ?: "")).toString(),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.8f
                    ),
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}
