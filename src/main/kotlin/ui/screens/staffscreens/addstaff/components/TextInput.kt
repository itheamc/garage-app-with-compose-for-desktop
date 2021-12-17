package ui.screens.staffscreens.addstaff.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ui.components.textfield.CustomTextField

@ExperimentalComposeUiApi
@Composable
fun ColumnScope.TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onSearch: () -> Unit = { },
    enabled: Boolean = true,
) {


    CustomTextField(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .height(50.dp)
            .fillMaxWidth(0.75f),
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        keyboardType = keyboardType,
        imeAction = imeAction,
        visualTransformation = visualTransformation,
        onSearch = onSearch,
        enabled = enabled
    )
}


/**
 * Text Input for rowScope
 */
@ExperimentalComposeUiApi
@Composable
fun RowScope.TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onSearch: () -> Unit,
    enabled: Boolean = true,
) {

    CustomTextField(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .height(50.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        keyboardType = keyboardType,
        imeAction = imeAction,
        visualTransformation = visualTransformation,
        onSearch = onSearch,
        enabled = enabled
    )
}