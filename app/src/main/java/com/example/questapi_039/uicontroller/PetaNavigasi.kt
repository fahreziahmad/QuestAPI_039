package com.example.questapi_039.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.questapi_039.uicontroller.route.DestinasiDetail
import com.example.questapi_039.uicontroller.route.DestinasiEdit
import com.example.questapi_039.uicontroller.route.DestinasiEntry
import com.example.questapi_039.uicontroller.route.DestinasiHome
import com.example.questapi_039.view.DetailSiswaScreen
import com.example.questapi_039.view.EntrySiswaScreen
import com.example.questapi_039.view.HomeScreen
import com.example.questapi_039.view.EditSiswaScreen

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Home Screen
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                navigateToItemUpdate = { id ->
                    // Correctly navigating to the detail screen first
                    navController.navigate("${DestinasiDetail.route}/$id")
                }
            )
        }

        // Entry Screen
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Detail Screen - FIXED PARAMETERS AND itemIdArg
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetail.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                navigateBack = { navController.popBackStack() },
                // Changed from onEditClick to navigateToEdit to match the expected parameter
                navigateToEdit = { id ->
                    navController.navigate("${DestinasiEdit.route}/$id")
                }
            )
        }
