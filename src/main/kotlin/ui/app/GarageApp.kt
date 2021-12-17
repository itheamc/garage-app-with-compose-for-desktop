package ui.app

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import navcontroller.rememberNavController
import ui.components.Header
import ui.components.fab.CustomFloatingActionButton
import ui.navigation.CustomNavHost
import ui.navigation.ScreenTypes
import ui.navigation.Screens
import scaffold.DesktopScaffold
import scaffold.rememberDesktopScaffoldState
import ui.screens.components.CategoryAddDialog
import ui.screens.components.FloatingPopUpMenu
import ui.screens.components.PopUpMenuItem
import ui.screens.components.ProductAddDialog
import ui.screens.transactionscreen.ui.SalesTransactionTialog
import ui.theme.GarageTheme

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
@Preview
fun GarageApp(windowState: WindowState) {

    val desktopScaffoldState = rememberDesktopScaffoldState()
    val scope = rememberCoroutineScope()
    val screens = Screens.values().asList()

    /**
     * Visibility Variables
     */
    var visible by remember {
        mutableStateOf(false)
    }

    var selectedMenu by remember {
        mutableStateOf(PopUpMenuItem.None)
    }

    val menuHeight by animateDpAsState(
        targetValue = if (visible) 250.dp else 0.dp,
        animationSpec = tween(500)
    )

    var dark by remember {
        mutableStateOf(false)
    }

    val navController by rememberNavController(Screens.HomeScreen)
    val currentScreen by remember {
        navController.currentScreen
    }


    GarageTheme(
        darkTheme = dark
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DesktopScaffold(
                scaffoldState = desktopScaffoldState,
                modifier = Modifier.fillMaxSize(),
                floatingActionButton = {
                    AnimatedVisibility(
                        visible = currentScreen == Screens.HomeScreen,
                        enter = slideIn(animationSpec = tween(250), initialOffset = {
                            IntOffset(it.width - 40, it.height)
                        }),
                        exit = slideOut(animationSpec = tween(250), targetOffset = {
                            IntOffset.Zero
                        }),
                        content = {
                            CustomFloatingActionButton(
                                modifier = Modifier.size(48.dp),
                                onClick = {
                                    visible = !visible
                                },
                                content = {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                    )
                },
                navigationRail = {
                    NavigationRail(
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        header = {
                            Header(
                                modifier = Modifier.size(48.dp)
                            )
                        },
                        content = {
                            screens.forEach {
                                if (it.type == ScreenTypes.Primary) {
                                    NavigationRailItem(
                                        label = {
                                            Text(it.label, color = MaterialTheme.colorScheme.onSurface)
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = it.icon,
                                                contentDescription = it.label
                                            )
                                        },
                                        alwaysShowLabel = false,
                                        onClick = {
                                            navController.navigate(it)
                                        },
                                        selected = currentScreen == it
                                    )
                                }
                            }

                            Spacer(Modifier.weight(1f))
                            IconButton(
                                onClick = {
                                    dark = !dark
                                }
                            ) {
                                Icon(
                                    imageVector = if (dark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            ) {

                /**
                 * Universal Composables
                 */
                FloatingPopUpMenu(
                    modifier = Modifier.padding(bottom = 24.dp).height(menuHeight),
                    onItemClick = {
                        visible = false
                        selectedMenu = it
                    },
                    visible = visible && currentScreen == Screens.HomeScreen
                )

                /**
                 * Add Category Pop Up Dialog
                 */
                CategoryAddDialog(
                    visible = selectedMenu == PopUpMenuItem.AddCategory,
                    onCloseRequest = {
                        selectedMenu = PopUpMenuItem.None
                    },
                    desktopScaffoldState = desktopScaffoldState
                )

                /**
                 * Add Product Pop Up Dialog
                 */
                ProductAddDialog(
                    visible = selectedMenu == PopUpMenuItem.AddProduct,
                    onCloseRequest = {
                        selectedMenu = PopUpMenuItem.None
                    },
                )

                /**
                 * Sales Transaction Dialog
                 */
                SalesTransactionTialog(
                    visible = selectedMenu == PopUpMenuItem.AddSales,
                    onCloseRequest = {
                        selectedMenu = PopUpMenuItem.None
                    }
                )

                CustomNavHost(
                    navController = navController,
                    windowState = windowState
                )

            }
        }
    }

}