package com.example.plantsapp.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.Data
import com.example.plantsapp.home.viewmodel.PlantsViewModel

@Composable
fun PlantDetailsScreen(
    plantId: Int,
    plant: Data,
    navController: NavController,
    viewModel: PlantsViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
    ) {
        // Main content of the screen (image + details)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Display character thumbnail (image)
            item {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            "${
                                plant.image_url.replace(
                                    "http://",
                                    "https://"
                                )
                            }.jpg"
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(
                            2.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = RectangleShape
                        )
                        .aspectRatio(0.9f),
                    contentScale = ContentScale.FillBounds
                )
            }

            // Display character name and description
            item {
                Column(modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp)) {

                    Text(
                        "Name: ${plant.common_name}",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Family: ${plant.family ?: "No description available."}",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Index: ${plant.id}",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Author: ${plant.author ?: "No description available."}",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }


            // Related links section
            item {
                // Load More Button placed at the bottom
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(color = MaterialTheme.colorScheme.onBackground, shape = RectangleShape)
                           , colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onBackground, // Background color
                    contentColor = MaterialTheme.colorScheme.background // Text color
                )
                ) {
                Text(text = "More Details")
                }
                    Spacer(modifier = Modifier.height(40.dp))

            }

        }


    }
}





