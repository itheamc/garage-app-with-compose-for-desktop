package ui.screens.staffscreens.staffdetail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import models.staff.Staff

// Professional Info Composable
val professionalInfo: @Composable (Modifier, Staff) -> Unit =
    { modifier, staff ->
        servicesOffered(modifier, staff)
    }


/**
 * Services Offered
 */
private val servicesOffered: @Composable (Modifier, Staff) -> Unit =
    { modifier, staff ->


    }