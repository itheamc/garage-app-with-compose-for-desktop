package ui.screens.staffscreens.staffdetail.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import models.staff.Staff
import ui.components.containers.ColumnContainer
import ui.components.image.AsyncImage
import ui.components.image.DynamicAsyncImage
import ui.components.image.ImageTransformation
import ui.components.spacers.Spacer16
import ui.components.spacers.Spacer36
import ui.components.spacers.Spacer4
import utils.toDate

/**
 * Personal Info Composable
 * It will contains all the personal information of the staff
 */
@ExperimentalAnimationApi
val personalInfo: @Composable (Modifier, Staff) -> Unit =
    { modifier, staff ->

        /**
         * Upper Container containing the image and name of the staff
         */
        val upperContainer: @Composable (Modifier) -> Unit = {
            ColumnContainer(
                modifier = it
            ) {
                Box(
                    modifier = Modifier.size(200.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    DynamicAsyncImage(
                        modifier = Modifier
                            .size(
                                size = 200.dp
                            ),
                        url = staff.image,
                        contentDescription = "${staff.name}_profile_image",
                        imageTransformation = ImageTransformation.Circle
                    )
                    chip(
                        staff.position
                    )
                }
                Spacer4()
                Text(
                    text = staff.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }

        /**
         * Lower Container containing the other personal details of the staff
         */
        val lowerContainer: @Composable (Modifier) -> Unit = {
            ColumnContainer(modifier = it) {
                customText(staff.address, Icons.Filled.Place)
                customText(staff.phone, Icons.Filled.Phone)
                customText(staff.email, Icons.Filled.Email)
                customText(staff.joinedOn.toDate(), Icons.Filled.CalendarToday)
            }
        }

        /**
         * Final placement of the staff's information
         */
        ColumnContainer(modifier = modifier) {
            Spacer36()
            upperContainer(
                Modifier.fillMaxWidth()
            )
            Spacer36()
            lowerContainer(
                Modifier.fillMaxWidth()
            )
            Spacer36()

        }
    }


/**
 * Composable to show the text for other details rather than Name
 */
private val customText: @Composable (String, ImageVector) -> Unit =
    { value, icon ->
        Row(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = icon,
                contentDescription = icon.name
            )
            Spacer16()
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                ),
            )
        }
    }

/**
 * Composable for chip view
 */
private val chip: @Composable (String) -> Unit =
    { label ->
        Box(
            modifier = Modifier
                .clip(CutCornerShape(50))
                .background(MaterialTheme.colorScheme.surface)
                .zIndex(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 14.dp),
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }