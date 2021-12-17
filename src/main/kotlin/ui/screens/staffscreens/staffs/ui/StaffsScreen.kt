package ui.screens.staffscreens.staffs.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import models.staff.Staff
import models.staff.dummyStaffs
import navcontroller.NavController
import ui.components.containers.RowContainer
import ui.screens.staffscreens.staffdetail.ui.StaffDetailsScreen
import ui.screens.staffscreens.staffs.components.StaffView


@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun StaffsScreen(
    modifier: Modifier,
    navController: NavController
) {

    val lazyListState = rememberLazyListState()
    var selected: Staff? by rememberSaveable {
        mutableStateOf(null)
    }

    RowContainer(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {

        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .width(300.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dummyStaffs) { staff ->
                StaffView(
                    staff = staff,
                    onClick = {
                        selected = staff
                    },
                    onLongClick = {

                    },
                    selected = staff.id == (selected?.id ?: "")
                )
            }
        }

        /**
         * Scrollbar
         */
        VerticalScrollbar(
            modifier = Modifier.fillMaxHeight(),
            adapter = rememberScrollbarAdapter(lazyListState)
        )

        /**
         * For Staf Details
         */
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = if (selected == null) Alignment.Center else Alignment.TopCenter
        ) {

            val textAlpha by animateFloatAsState(
                targetValue = if (selected == null) 1f else 0f,
                animationSpec = tween(1000)
            )
            val screenAlpha by animateFloatAsState(
                targetValue = if (selected == null) 0f else 1f,
                animationSpec = tween(1000)
            )

            when (selected) {
                null -> Text(
                    modifier = Modifier.alpha(textAlpha),
                    text = "Select staff to see details",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontStyle = FontStyle.Italic
                    )
                )
                else -> StaffDetailsScreen(modifier = Modifier.alpha(screenAlpha), staff = selected!!)
            }
        }
    }

}