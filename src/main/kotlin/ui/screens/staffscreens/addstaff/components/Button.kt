package ui.screens.staffscreens.addstaff.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.components.button.CustomButton

@Composable
fun StaffButton(
    label: String,
    onClick: () -> Unit,
) {


    CustomButton(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(50.dp)
            .fillMaxWidth(0.75f),
        label = label,
        onClick = onClick
    )
}