package com.akbar.a0142_roomdatabase.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akbar.a0142_roomdatabase.uicontroller.route.DestinasiDetailSiswa
import com.akbar.a0142_roomdatabase.uicontroller.route.DestinasiDetailSiswa.itemIdArg
import com.akbar.a0142_roomdatabase.uicontroller.route.DestinasiEditSiswa
import com.akbar.a0142_roomdatabase.uicontroller.route.DestinasiEntry
import com.akbar.a0142_roomdatabase.uicontroller.route.DestinasiHome
import com.akbar.a0142_roomdatabase.view.DetailSiswaScreen
import com.akbar.a0142_roomdatabase.view.EditSiswaScreen
import com.akbar.a0142_roomdatabase.view.EntrySiswaScreen
import com.akbar.a0142_roomdatabase.view.HomeScreen

@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                // edit 1 : tambahkan parameter navigateToItemUpdate
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetailSiswa.route}/${it}")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }
        // edit 2 : tambahkan 2 composable route
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                navigateToEditItem = { navController.navigate("${DestinasiEditSiswa.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditSiswa.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}