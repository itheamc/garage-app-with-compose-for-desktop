package ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screens(
    val label: String,
    val icon: ImageVector,
    val type: ScreenTypes = ScreenTypes.Secondary
) {
    HomeScreen(
        label = "Dashboard",
        icon = Icons.Filled.DashboardCustomize,
        type = ScreenTypes.Primary
    ),
    ServicesScreen(
        label = "Services",
        icon = Icons.Filled.Construction,
        type = ScreenTypes.Primary
    ),
    StaffsScreen(
        label = "Staffs",
        icon = Icons.Filled.Engineering,
        type = ScreenTypes.Primary
    ),
    CustomersScreen(
        label = "Customers",
        icon = Icons.Filled.Groups,
        type = ScreenTypes.Primary
    ),
    SettingsScreen(
        label = "Settings",
        icon = Icons.Filled.Settings,
        type = ScreenTypes.Primary
    ),
    NotificationScreen(
        label = "Notifications",
        icon = Icons.Filled.Notifications,
    ),
    ProfileScreen(
        label = "Profile",
        icon = Icons.Filled.VerifiedUser,
    );

}


/**
 * -------------------------------------
 * Enum Class for Screen type
 */
enum class ScreenTypes {
    Primary,
    Secondary
}