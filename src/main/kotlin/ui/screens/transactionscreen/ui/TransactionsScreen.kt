package ui.screens.transactionscreen.ui


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import models.transactions.Transaction
import ui.screens.transactionscreen.components.TransactionItem


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun TransactionsScreen() {
    val transactions = listOf<Transaction>(
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction(),
        Transaction()
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(transactions) { transaction ->
            TransactionItem(
                transaction = transaction,
                onClick = {
                    println("TransactionsScreen: trnsId -> ${transaction.id}")
                }
            )
        }
    }
}