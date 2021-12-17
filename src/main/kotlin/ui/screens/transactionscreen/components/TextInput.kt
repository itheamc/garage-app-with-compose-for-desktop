package ui.screens.transactionscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ui.components.textfield.CustomTextField

@ExperimentalComposeUiApi
@Composable
internal fun TransactionTextInput(
    value: String,
    onValueChange: (String) -> Unit = { },
    label: String,
    placeholder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onSearch: () -> Unit = { },
    enabled: Boolean = true,
    onClick: () -> Unit = { }
) {


    CustomTextField(
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                role = Role.Button
            ),
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