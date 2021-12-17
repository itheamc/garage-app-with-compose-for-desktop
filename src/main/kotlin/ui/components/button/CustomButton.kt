package ui.components.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ui.components.spacers.Spacer4


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Normal,
    shape: Shape = RoundedCornerShape(4.dp),
    label: String,
    icon: ImageVector? = null,
    onClick: () -> Unit,
    enabled: Boolean = true
) {

    val content: @Composable RowScope.() -> Unit = {
        Text(
            text = label, style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                    alpha = 0.9f
                ),
                fontWeight = FontWeight.Bold
            )
        )
        Spacer4()
        icon?.let {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = it,
                contentDescription = null
            )
        }
    }


    when (buttonType) {
        ButtonType.Normal -> {
            Button(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                content = content,
                shape = shape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }

        ButtonType.Outline -> {
            OutlinedButton(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                content = content,
                shape = shape,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        }

        ButtonType.Text -> {
            TextButton(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                content = content,
                shape = shape
            )
        }

        ButtonType.Icon -> {
            IconButton(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                content = {
                    icon?.let {
                        Icon(imageVector = it, contentDescription = it.name)
                    }
                }
            )
        }
    }
}


/**
 * Enum Class For Button Types
 */
enum class ButtonType { Normal, Outline, Text, Icon }