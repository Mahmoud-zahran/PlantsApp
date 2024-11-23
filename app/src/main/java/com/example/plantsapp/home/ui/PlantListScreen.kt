package com.example.plantsapp.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.domain.model.InternalDataLinks
import com.example.plantsapp.R

@Composable
fun PlantListScreen(
    plants: List<Data>, navController: NavController, modifier: Modifier = Modifier
) {
    Column (Modifier.padding(top = 90.dp)){
    // Tabs
    val tabTitles = listOf("All", "Palestine", "Sudan","Myanmar","Transcaucasus","Uzbekistan")
    var selectedTabIndex by remember { mutableStateOf(0) }


        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 1.dp, // Add padding at the edges
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background),
            contentColor = MaterialTheme.colorScheme.surface,
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                    ,modifier = Modifier.background(if (selectedTabIndex == index) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background),

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
            modifier = Modifier
                .fillMaxWidth()
                .padding( bottom = 40.dp)
        ) {
            items(plants) { plant ->
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
    }
}


@Composable
fun PlantItem(image_url: String,name: String, year: String, status: String) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp, start = 2.dp, end = 2.dp).border(1.dp,color = MaterialTheme.colorScheme.onBackground, shape = RectangleShape).padding(8.dp).background(MaterialTheme.colorScheme.background), verticalAlignment = Alignment.CenterVertically
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
    PlantItem("url","zahran","6","status")
}