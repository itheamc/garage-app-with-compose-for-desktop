package ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.Header(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(8.dp))
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.CarRepair,
        contentDescription = "Shop"
    )
    Spacer(modifier = Modifier.height(8.dp))
    Divider(modifier = Modifier.width(60.dp))
}