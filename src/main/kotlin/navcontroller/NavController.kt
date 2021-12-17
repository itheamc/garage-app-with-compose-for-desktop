package navcontroller

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import kotlinx.coroutines.delay
import ui.navigation.Screens


/**
 * NavController
 */
class NavController(
    private val startDestination: Screens,
    private var backStackScreens: MutableSet<Screens>,
) {

    var currentScreen: MutableState<Screens> = mutableStateOf(startDestination)

    fun navigate(scr: Screens) {
        if (scr != currentScreen.value) {
            if (backStackScreens.contains(currentScreen.value) && currentScreen.value != startDestination) {
                backStackScreens.remove(currentScreen.value)
            }

            if (scr == startDestination) {
                backStackScreens = mutableSetOf()
            } else {
                backStackScreens.add(currentScreen.value)
            }

            currentScreen.value = scr
        }
        println(backStackScreens)
    }

    fun navigateBack() {
        if (backStackScreens.isNotEmpty()) {
            currentScreen.value = backStackScreens.last()
            backStackScreens.remove(currentScreen.value)
        }
    }

}


@Composable
fun rememberNavController(
    startDestination: Screens,
    backStackScreens: MutableSet<Screens> = mutableSetOf()
): MutableState<NavController> = rememberSaveable {
    mutableStateOf(NavController(startDestination, backStackScreens))
}


/**
 * Navigation Host
 */
class NavigationHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {

    @Composable
    fun build() {
        NavigationGraphBuilder().render()
    }

    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController,
    ) {
        @Composable
        fun render() {
            this@NavigationHost.contents(this)
        }
    }
}


@Composable
fun NavigationHost.NavigationGraphBuilder.composable(
    route: Screens,
    content: @Composable (Modifier) -> Unit
) {
    if (navController.currentScreen.value == route) {
        var timeout by remember {
            mutableStateOf(false)
        }
        val alpha by animateFloatAsState(
            targetValue = if (timeout) 1f else 0f,
            animationSpec = tween(750)
        )

        LaunchedEffect(Unit) {
            delay(100)
            timeout = true
        }

        content(
            Modifier
                .fillMaxSize()
                .alpha(alpha)
        )
    }
}
