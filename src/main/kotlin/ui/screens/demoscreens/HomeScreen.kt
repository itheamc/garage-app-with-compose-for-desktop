package ui.screens.demoscreens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navcontroller.NavController

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(navController.currentScreen.value.label)
    }
}