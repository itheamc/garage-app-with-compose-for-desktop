package scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import ui.screens.snackbar.CustomSnackbarHost
import kotlin.math.roundToInt


@Composable
fun DesktopScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: DesktopScaffoldState = rememberDesktopScaffoldState(),
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { CustomSnackbarHost(it) },
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    navigationRail: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    verticalScrollBar: @Composable () -> Unit = {},
    horizontalScrollBar: @Composable () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable (PaddingValues) -> Unit,
) {

    val child = @Composable { childModifier: Modifier ->
        Surface(modifier = childModifier, color = containerColor, contentColor = contentColor) {
            DesktopScaffoldLayout(
                topBar = topBar,
                bottomBar = bottomBar,
                navigationRail = navigationRail,
                content = content,
                snackBar = {
                    snackbarHost(
                        scaffoldState.snackbarHostState
                    )
                },
                fab = floatingActionButton,
                verticalScrollBar = verticalScrollBar,
                horizontalScrollBar = horizontalScrollBar
            )
        }
    }

    child(modifier)

}


/**
 * ------------------------------------------------------------------------------
 * Desktop Scaffold Layout Design
 * -----------------------------------------------------------------------------
 */
@Composable
private fun DesktopScaffoldLayout(
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    navigationRail: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    fab: @Composable () -> Unit,
    snackBar: @Composable () -> Unit,
    verticalScrollBar: @Composable() () -> Unit,
    horizontalScrollBar: @Composable() () -> Unit,
) {
    SubcomposeLayout { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight
        val contentMargin = 0.dp.roundToPx()

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        /*
        -------------------------------------------------------------------------------
        Layout Design and Content Placement Starts from here
         */
        layout(layoutWidth, layoutHeight) {
            /*
            ------------------------------------------------------
             TopBar
             */
            val topBarPlaceables =
                subcompose(slotId = DesktopScaffoldLayoutContent.TopBar) {
                    CompositionLocalProvider(
                        content = topBar
                    )
                }.map {
                    it.measure(looseConstraints)
                }

            val topBarHeight = topBarPlaceables.maxByOrNull { it.height }?.height ?: 0


            /*
            --------------------------------------------------------
             For Floating Action Button
             */
            val fabPlaceables =
                subcompose(DesktopScaffoldLayoutContent.Fab, fab).mapNotNull { measurable ->
                    measurable.measure(looseConstraints)
                        .takeIf { it.height != 0 && it.width != 0 }
                }

            val fabPlacement = if (fabPlaceables.isNotEmpty()) {
                val fabWidth = fabPlaceables.maxByOrNull { it.width }!!.width
                val fabHeight = fabPlaceables.maxByOrNull { it.height }!!.height
                // FAB distance from the left of the layout, taking into account LTR / RTL
                val fabLeftOffset = if (layoutDirection == LayoutDirection.Ltr) {
                    layoutWidth - FabSpacing.roundToPx() - fabWidth
                } else {
                    FabSpacing.roundToPx()
                }

                FabPlacement(
                    left = fabLeftOffset,
                    width = fabWidth,
                    height = fabHeight
                )
            } else {
                null
            }
            /*
            ----------------------------------------------------------
            Bottom Bar
             */
            val bottomBarPlaceables = subcompose(slotId = DesktopScaffoldLayoutContent.BottomBar) {
                CompositionLocalProvider(
                    LocalFabPlacement provides fabPlacement,
                    content = bottomBar
                )
            }.map {
                it.measure(looseConstraints)
            }

            val bottomBarHeight = bottomBarPlaceables.maxByOrNull { it.height }?.height ?: 0


            /*
            ----------------------------------------------------------
            Navigation Rail
             */
            val navigationRailPlaceables =
                subcompose(slotId = DesktopScaffoldLayoutContent.NavigationRail, content = navigationRail).map {
                    it.measure(looseConstraints)
                }

            val navigationRailWidth = navigationRailPlaceables.maxByOrNull { it.width }?.width ?: 0

            /*
            ----------------------------------------------------------------
            Horizontal ScrollBar Placement
             */
            val horizontalScrollBarPlaceable =
                subcompose(
                    slotId = DesktopScaffoldLayoutContent.HorizontalScrollBar,
                    content = horizontalScrollBar
                ).map { measurable ->
                    measurable.measure(
                        looseConstraints.copy(
                            maxWidth = layoutWidth - navigationRailWidth
                        )
                    )
                }

            val horizontalScrollBarHeight = horizontalScrollBarPlaceable.maxByOrNull { it.height }?.height ?: 0
            val horizontalScrollBarYOffset = layoutHeight - bottomBarHeight - horizontalScrollBarHeight


            /*
            ----------------------------------------------------------------
            Vertical ScrollBar Placement
             */
            val verticalScrollBarPlaceable =
                subcompose(
                    slotId = DesktopScaffoldLayoutContent.VerticalScrollBar,
                    content = verticalScrollBar
                ).map { measurable ->
                    measurable.measure(
                        looseConstraints.copy(
                            maxHeight = layoutHeight - topBarHeight - bottomBarHeight - horizontalScrollBarHeight
                        )
                    )
                }

            val verticalScrollBarWidth = verticalScrollBarPlaceable.maxByOrNull { it.width }?.width ?: 0
            val verticalScrollBarXOffset = layoutWidth - verticalScrollBarWidth

            /*
            ----------------------------------------------------------
            Fab Offset Bottom
             */
            val fabOffsetFromBottom = fabPlacement?.let {
                bottomBarHeight + horizontalScrollBarHeight + it.height + FabSpacing.roundToPx()
            }

            /*
            Snackbar Placeable
            ----------------------------------------------------------
             */
            val snackbarPlaceables = subcompose(DesktopScaffoldLayoutContent.Snackbar, snackBar).map {
                it.measure(
                    looseConstraints.copy(
                        maxWidth = (layoutWidth * 0.40).roundToInt()
                    )
                )
            }

            val snackbarHeight = snackbarPlaceables.maxByOrNull { it.height }?.height ?: 0
            val snackbarWidth = snackbarPlaceables.maxByOrNull { it.width }?.width ?: 0

            val snackbarOffsetFromBottom = if (snackbarHeight != 0) {
                snackbarHeight + bottomBarHeight + horizontalScrollBarHeight
            } else {
                0
            }

            val snackbarOffsetFromLeft = (layoutWidth / 2) - (snackbarWidth / 2)

            /*
            -----------------------------------------------------------------
            Content container Width and Height
             */
            val bodyContentHeight =
                layoutHeight - topBarHeight - bottomBarHeight - horizontalScrollBarHeight - (contentMargin * 2)
            val bodyContentWidth = layoutWidth - navigationRailWidth - verticalScrollBarWidth - (contentMargin * 2)

            /*
            ----------------------------------------------------------------------
            Body content placeable
             */
            val bodyContentPlaceable = subcompose(DesktopScaffoldLayoutContent.MainContent) {
                val innerPadding = PaddingValues(all = 12.dp)
                content(innerPadding)
            }.map {
                it.measure(
                    looseConstraints.copy(
                        maxHeight = bodyContentHeight,
                        maxWidth = bodyContentWidth
                    )
                )
            }

            /*
            Body content placeable
             Placing to control drawing order to match default elevation of each placeable
             */
            bodyContentPlaceable.forEach {
                it.place(navigationRailWidth + contentMargin, topBarHeight + contentMargin)
            }
            /*
            Top Bar Placeable
             */
            topBarPlaceables.forEach {
                it.place(
                    x = 0,
                    y = 0
                )
            }

            /*
            Navigation Rail placeable
             */
            navigationRailPlaceables.forEach {
                it.place(
                    x = 0,
                    y = topBarHeight
                )
            }

            /*
            Bottom Bar placeable
             */
            bottomBarPlaceables.forEach {
                it.place(
                    x = navigationRailWidth,
                    y = layoutHeight - bottomBarHeight
                )
            }

            /*
           Vertical Scroll Bar Bar placeable
            */
            verticalScrollBarPlaceable.forEach {
                it.place(
                    x = verticalScrollBarXOffset,
                    y = topBarHeight
                )
            }

            /*
          Horizontal Scroll Bar Bar placeable
           */
            horizontalScrollBarPlaceable.forEach {
                it.place(
                    x = navigationRailWidth,
                    y = horizontalScrollBarYOffset
                )
            }

            /*
            Snackbar Placeable
             */
            snackbarPlaceables.forEach {
                it.place(snackbarOffsetFromLeft, layoutHeight - snackbarOffsetFromBottom)
            }

            /*
            Fab placeable
            Explicitly not using placeRelative here as `leftOffset` already accounts for RTL
             */
            fabPlacement?.let { placement ->
                fabPlaceables.forEach {
                    it.place(placement.left, layoutHeight - fabOffsetFromBottom!!)
                }
            }
        }
    }
}


/**
 * State for [DesktopScaffold] composable component.
 */
class DesktopScaffoldState(
    val snackbarHostState: SnackbarHostState
)

/**
 * Creates a [DesktopScaffoldState] with the default animation clock and memorizes it.
 * @param snackbarHostState the snackBar Host state
 */
@Composable
fun rememberDesktopScaffoldState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): DesktopScaffoldState = remember {
    DesktopScaffoldState(snackbarHostState)
}

/**
 * Placement information for a [FloatingActionButton] inside a [DesktopScaffold].
 */
@Immutable
internal class FabPlacement(
    val left: Int,
    val width: Int,
    val height: Int
)

/**
 * CompositionLocal containing a [FabPlacement] that is used to calculate the FAB bottom offset.
 */
internal val LocalFabPlacement = staticCompositionLocalOf<FabPlacement?> { null }

// FAB spacing above the bottom bar / bottom of the Scaffold
private val FabSpacing = 16.dp

private enum class DesktopScaffoldLayoutContent { TopBar, MainContent, Fab, BottomBar, NavigationRail, Snackbar, VerticalScrollBar, HorizontalScrollBar }