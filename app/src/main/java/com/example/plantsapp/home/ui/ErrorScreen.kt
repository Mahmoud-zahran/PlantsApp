package com.example.plantsapp.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable
fun ErrorScreen(message: String,  onBack: () -> Unit) {
    BackHandler {
        onBack() // Handle the back press (e.g., navigate to the previous screen or close the app)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $message",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        IconButton(
            onClick = { onBack}, // Navigate back
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Go Back")
        }
    }
}