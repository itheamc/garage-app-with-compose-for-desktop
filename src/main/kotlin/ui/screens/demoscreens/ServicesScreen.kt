@file:OptIn(ExperimentalAnimationApi::class)

package ui.screens.demoscreens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navcontroller.NavController
import ui.components.containers.ColumnContainer
import ui.navigation.Screens
import ui.screens.transactionscreen.ui.TransactionDetailScreen
import ui.screens.transactionscreen.ui.TransactionDetils

@Composable
fun ServicesScreen(
    modifier: Modifier,
    navController: NavController
) {
//    ColumnContainer(
//        modifier = modifier,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(navController.currentScreen.value.label)
//        Button(
//            onClick = { navController.navigate(Screens.CustomersScreen) }
//        ) {
//            Text("Navigate to customer")
//        }
//    }
    TransactionDetils()
}