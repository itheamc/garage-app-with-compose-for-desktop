package ui.screens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.rememberCursorPositionProvider
import ui.components.button.ButtonType
import ui.components.button.CustomButton
import ui.components.containers.ColumnContainer

@ExperimentalComposeUiApi
@Composable
fun FloatingPopUpMenu(
    modifier: Modifier = Modifier,
    onItemClick: (PopUpMenuItem) -> Unit = {},
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    visible: Boolean = false
) {

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(500))
    ) {
        Popup(
            popupPositionProvider = rememberCursorPositionProvider(),
            onKeyEvent = onKeyEvent,
            onPreviewKeyEvent = onPreviewKeyEvent
        ) {
            ColumnContainer(
                modifier = modifier,
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                CustomButton(
                    onClick = {
                        onItemClick(PopUpMenuItem.AddProduct)
                    },
                    label = "Add New Product",
                    icon = Icons.Filled.Business,
                    buttonType = ButtonType.Normal
                )

                CustomButton(
                    onClick = {
                        onItemClick(PopUpMenuItem.AddCategory)
                    },
                    label = "Add New Category",
                    icon = Icons.Filled.Business,
                    buttonType = ButtonType.Normal
                )

                CustomButton(
                    onClick = {
                        onItemClick(PopUpMenuItem.AddPurchase)
                    },
                    label = "Add Purchase Transaction",
                    icon = Icons.Filled.Business,
                    buttonType = ButtonType.Normal
                )
                CustomButton(
                    onClick = {
                        onItemClick(PopUpMenuItem.AddSales)
                    },
                    label = "Add Sales Transaction",
                    icon = Icons.Filled.Business,
                    buttonType = ButtonType.Normal
                )

                CustomButton(
                    onClick = {
                        onItemClick(PopUpMenuItem.AddCollections)
                    },
                    label = "Add Collection Transaction",
                    icon = Icons.Filled.Business,
                    buttonType = ButtonType.Normal
                )

            }
        }
    }
}


/**
 * Popup Menu Items
 */
enum class PopUpMenuItem {
    AddProduct,
    AddService,
    AddCategory,
    AddPurchase,
    AddSales,
    AddCollections,
    AddSalaries,
    AddStaff,
    AddCustomer,
    AddVehicle,
    None
}