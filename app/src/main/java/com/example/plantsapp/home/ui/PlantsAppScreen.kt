package com.example.plantsapp.home.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.domain.model.Result
import com.example.plantsapp.home.viewmodel.PlantsViewModel
import com.example.plantsapp.ui.theme.PlantsAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PlantsAppScreen(
    viewModel: PlantsViewModel = viewModel(),
    windowSize: WindowSizeClass,
    navController: NavController
) {
    val PlantsState = viewModel.plants.collectAsState()

    // Get the current navigation destination
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    PlantsAppTheme{
        Scaffold(
            topBar = {
                if (currentDestination == "home") {
                    PlantsTopBar("Plants")
                }else if(currentDestination == "plant_details/{plantId}"){
                    PlantsTopBar("Plants Details")

                }
            }

        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                // Handle the Plants list state
                when (val PlantsResult = PlantsState.value) {
                    is Result.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    is Result.Success -> {
                        if (PlantsResult.data.data.isNotEmpty()) {
                            NavHost(
                                navController = navController as NavHostController,
                                startDestination = "home"
                            ) {
                                composable("home") {
                                    PlantListScreen(
                                        plants = PlantsResult.data.data,
                                        navController = navController,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                              /*  composable(
                                    "plant_details/{plantId}",
                                    arguments = listOf(navArgument("plantId") { type = NavType.IntType })
                                ) { backStackEntry ->
                                    val plantId = backStackEntry.arguments?.getInt("plantId") ?: 0
                                    val plant = PlantsResult.data.data.firstOrNull { it.id == plantId }
                                    plant?.let {
                                        PlantDetailsScreen(
                                            it.id,
                                            plant = it,
                                            navController = navController,
                                            viewModel = viewModel
                                        )
                                    }
                                }*/

                            }
                        } else {
                            Text("No Plants found.", modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    is Result.Error -> {
                        Text(
                            text = "Error: ${PlantsResult.message}",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    null -> TODO()
                }
            }
        }
    }
}

