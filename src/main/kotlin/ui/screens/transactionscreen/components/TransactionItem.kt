package ui.screens.transactionscreen.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import models.transactions.Transaction
import ui.components.image.AsyncImage
import ui.components.image.ImageTransformation
import ui.components.spacers.Spacer8
import utils.toDate

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun LazyItemScope.TransactionItem(transaction: Transaction = Transaction(), onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        /**
         * Image
         */
        AsyncImage(
            modifier = Modifier.size(70.dp),
            url = "https://unsplash.com/photos/6W4F62sN_yI/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4Njc3ODc4&force=true&w=640",
            contentDescription = transaction.customerId,
            imageTransformation = ImageTransformation.RoundedCorner.apply { radius = 2.dp }
        )

        /**
         * Spacer between Image and Column
         */
        Spacer8()
        /**
         * Column to contain the contents
         */
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            /**
             * Customer Name
             */
            Text(
                text = "Amit Chaudhary",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            /**
             * Transaction Id
             */
            Text(
                text = buildAnnotatedString {
                    append("Trans Id: ")
                    withStyle(
                        SpanStyle(
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append(transaction.id)
                    }
                },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
            )

            /**
             * Transaction Amount
             */
            Text(
                text = buildAnnotatedString {
                    append("Amount: ")
                    withStyle(
                        SpanStyle(
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append("Rs. ${transaction.netAmount}")
                    }
                },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
            )
            /**
             * Date
             */
            Text(
                text = buildAnnotatedString {
                    append("Date: ")
                    withStyle(
                        SpanStyle(
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append(transaction.transactionDate.toDate())
                    }
                },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                fontStyle = FontStyle.Italic
            )
        }
    }
}