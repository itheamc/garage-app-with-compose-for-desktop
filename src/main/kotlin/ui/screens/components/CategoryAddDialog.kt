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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.components.button.CustomButton
import ui.grid.Grid
import ui.grid.GridType
import scaffold.DesktopScaffoldState
import ui.theme.GarageTheme

@ExperimentalComposeUiApi
@Composable
fun CategoryAddDialog(
    onCloseRequest: () -> Unit,
    state: DialogState = rememberDialogState(),
    visible: Boolean = false,
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    desktopScaffoldState: DesktopScaffoldState
) {
    val scope = rememberCoroutineScope()

    Dialog(
        onCloseRequest = onCloseRequest,
        visible = visible,
        state = state,
        title = "New Product Category",
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
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                val name = textField("Category Name", "e.g. Mobil, grease etc.")
                val desc = textField("Category Description", "Some info about category...")
                CustomButton(
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    label = "Add",
                    onClick = {
                        scope.launch {
                            desktopScaffoldState.snackbarHostState.showSnackbar("name -> $name, desc -> $desc")
                        }
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