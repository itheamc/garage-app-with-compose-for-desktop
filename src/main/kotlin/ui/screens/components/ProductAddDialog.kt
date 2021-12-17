package ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ui.components.containers.ColumnContainer
import ui.components.textfield.CustomTextField
import androidx.compose.runtime.*
import ui.components.button.CustomButton
import ui.grid.Grid
import ui.grid.GridType

@ExperimentalComposeUiApi
@Composable
fun ProductAddDialog(
    onCloseRequest: () -> Unit,
    state: DialogState = rememberDialogState(size = DpSize(700.dp, 525.dp)),
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
            ColumnContainer(
                modifier = Modifier.fillMaxSize()
            ) {
                Grid(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    gridType = GridType.Normal
//            verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val name = textField("Product Name", "e.g. Mobil, grease etc.")
                    val desc = textField("Product Description", "e.g. Mobil, grease etc.")
                    val cost = textField("Product Cost", "e.g. Mobil, grease etc.")
                    val price = textField("Product Price", "e.g. Mobil, grease etc.")
                    val qty = textField("Product Quantity", "e.g. Mobil, grease etc.")

                    Text("name -> $name, desc -> $desc, cost -> $cost, price -> $price, qty -> $qty")
                    val addedBy = textField("Added By", "e.g. Mobil, grease etc.")
                    val addedOn = textField("Added On", "e.g. Mobil, grease etc.")

                }

                CustomButton(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    label = "Add",
                    onClick = {

                    }
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
private val textField: @Composable (String, String) -> String =
    { label, placeholder ->
        var value by remember {
            mutableStateOf("")
        }
        CustomTextField(
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            label = label,
            placeholder = placeholder,
            value = value,
            onValueChange = {
                value = it
            }
        )
        value
    }