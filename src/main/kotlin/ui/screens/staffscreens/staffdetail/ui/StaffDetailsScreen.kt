package ui.screens.staffscreens.staffdetail.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import models.staff.Staff
import ui.components.containers.ColumnContainer
import ui.components.containers.ScrollableColumnContainer

import ui.screens.staffscreens.staffdetail.components.personalInfo
import ui.screens.staffscreens.staffdetail.components.professionalInfo


@ExperimentalAnimationApi
@Composable
fun StaffDetailsScreen(modifier: Modifier = Modifier, staff: Staff) {

    val scrollState = rememberScrollState()

    ScrollableColumnContainer(
        modifier = Modifier
            .fillMaxSize(),
        scrollState = scrollState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 8.dp, top = 20.dp)
                .clip(
                    shape = RoundedCornerShape(12.dp)
                )
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.075f))
                .zIndex(1f)
        ) {
            personalInfo(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                staff
            )
        }

        professionalInfo(
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(start = 12.dp, end = 8.dp, bottom = 20.dp),
            staff
        )

    }

}


/**
 * Staff Details
 * -------------------------------------------
 * Personal Info
 * -------------------------------------------
 * Name
 * Address
 * Phone
 * Email
 * Photo
 * JoinedOn
 * ----------------------------------------------
 * Professional Info
 * ----------------------------------------------
 * Position
 * Total Services Offered - (with category like - Repairing, Servicing, and so on)
 * Efficiency Rate (Total Services - Re-Services)/100
 * Total Working days (Hours)
 * Total leave offered
 * Total Amount Paid and Pending Payments
 *
 */