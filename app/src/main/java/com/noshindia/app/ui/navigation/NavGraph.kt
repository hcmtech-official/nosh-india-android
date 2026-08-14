package com.noshindia.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.noshindia.app.ui.home.HomeScreen
import com.noshindia.app.ui.menu.MenuScreen
import com.noshindia.app.ui.order.OrderScreen
import com.noshindia.app.ui.reviews.ReviewsScreen

private sealed class Destination(val route: String, val label: String) {
    object Home : Destination("home", "Home")
    object Menu : Destination("menu", "Menu")
    object Order : Destination("order", "Order")
    object Reviews : Destination("reviews", "Reviews")
}

@Composable
fun NoshIndiaNavGraph(
    onPlaceOrder: (String) -> Unit
) {
    val navController = rememberNavController()
    val destinations = listOf(Destination.Home, Destination.Menu, Destination.Order, Destination.Reviews)

    Scaffold(
        bottomBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntry?.destination

            NavigationBar {
                destinations.forEach { destination ->
                    val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            val icon = when (destination) {
                                Destination.Home -> Icons.Filled.Home
                                Destination.Menu -> Icons.Filled.RestaurantMenu
                                Destination.Order -> Icons.Filled.ShoppingCart
                                Destination.Reviews -> Icons.Filled.RateReview
                            }
                            Icon(icon, contentDescription = destination.label)
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Destination.Home.route) {
                HomeScreen(
                    onOrderNow = {
                        navController.navigate(Destination.Order.route) { launchSingleTop = true }
                    }
                )
            }
            composable(Destination.Menu.route) { MenuScreen() }
            composable(Destination.Order.route) { OrderScreen(onPlaceOrder = onPlaceOrder) }
            composable(Destination.Reviews.route) { ReviewsScreen() }
        }
    }
}
