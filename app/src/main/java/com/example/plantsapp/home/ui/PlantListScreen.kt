package com.example.plantsapp.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.Data
import com.example.plantsapp.R
import com.example.plantsapp.home.viewmodel.PlantsViewModel

@Composable
fun PlantListScreen(plantsViewModel: PlantsViewModel,
    plants: List<Data>, navController: NavController, modifier: Modifier = Modifier
) {
    // Observe the ViewModel state directly
    val selectedTabIndex = plantsViewModel.selectedTabIndex
    val isLoading = plantsViewModel.isLoading

    // LazyListState for tracking scroll position
    val listState = rememberLazyListState()

    Column(Modifier.padding(top = 90.dp)) {
        // Tabs
        val tabTitles =
            listOf("All", "Palestine", "Sudan", "Myanmar", "Transcaucasus", "Uzbekistan")


        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 1.dp, // Add padding at the edges
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            contentColor = MaterialTheme.colorScheme.surface,
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        plantsViewModel.setSelectedTab(index) // Update the selected tab index in the ViewModel
                        val distributionPath= plantsViewModel.getDistributionPathForTab(index)
                        plantsViewModel.getPlants(distributionPath,1)
                    /*    when (index) {
                            0 -> plantsViewModel.getPlants("", 1) // "All"
                            1 -> plantsViewModel.getPlants("distributions/pal/", 1) // "Palestine"
                            2 -> plantsViewModel.getPlants("distributions/sud/", 1) // "Sudan"
                            3 -> plantsViewModel.getPlants("distributions/mya/", 1) // "Myanmar"
                            4 -> plantsViewModel.getPlants("distributions/tcs/", 1) // "Transcaucasus"
                            5 -> plantsViewModel.getPlants("distributions/uzb/", 1) // "Uzbekistan"
                        }*/
                    },
                    modifier = Modifier.background(if (selectedTabIndex == index) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background),

                    ) {
                    Text(
                        text = title,
                        fontSize = 16.sp, // Adjust text size
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(4.dp),//.background(if (selectedTabIndex == index) Color.Gray else Color.Black),
                        color = if (selectedTabIndex == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
        LazyColumn(
            state = listState, // Set the state for the LazyColumn
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
        ) {
            items(plants) { plant ->
                if (plant != null) {
                    Box(modifier = Modifier
                    .fillMaxWidth()
//                    .padding(8.dp)
                    .clickable {
                        navController.navigate("plant_details/${plant.id}")
                    }) {

                        PlantItem(plant.image_url, plant.common_name, "${plant.year}", plant.status)
                    }
                }
            }
// Load more indicator
            item {
                if (isLoading) {
                    // Show loading indicator at the bottom when fetching more data
                    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Text(text = "Loading...", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
        // Detect when the user has scrolled to the bottom
        LaunchedEffect(listState.layoutInfo.visibleItemsInfo.lastOrNull()) {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            val totalItems = listState.layoutInfo.totalItemsCount

            if (lastVisibleItem != null && lastVisibleItem.index == totalItems - 1 && isLoading) {
                // Trigger loading more data when the last item is visible
                plantsViewModel.loadNextPage()
            }
        }
    }
}


@Composable
fun PlantItem(image_url: String, name: String, year: String, status: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 2.dp, end = 2.dp)
            .border(1.dp, color = MaterialTheme.colorScheme.onBackground, shape = RectangleShape)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Placeholder for image

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
//                .data("https://bs.plantnet.org/image/o/0d92cadb0d66dce1b0a8b26913125d6501e31d68.jpg")
                .data(
                    "${
                        image_url.replace(
                            "http://", "https://"
                        )
                    }.jpg"
                ).crossfade(true).build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(90.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Plant details
        Column {
            Text(text = "Name: $name", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Year: $year", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Status: $status", style = MaterialTheme.typography.bodyMedium)
        }
    }

}

@Preview
@Composable
fun PlantItemPreview() {
    PlantItem("url", "zahran", "6", "status")
}
