package com.example.plantsapp.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.plantsapp.home.ui.PlantsAppScreen
import com.example.plantsapp.home.viewmodel.PlantsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val plantsViewModel: PlantsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PlantsAppScreen(
                viewModel = plantsViewModel,
                windowSize = calculateWindowSizeClass(activity = this),
                navController = navController
            )
        }

        lifecycleScope.launch {
            plantsViewModel.getPlants("",1)
        }
    }
    override fun onBackPressed() {
            super.onBackPressed() // Default system back navigation

    }
}

