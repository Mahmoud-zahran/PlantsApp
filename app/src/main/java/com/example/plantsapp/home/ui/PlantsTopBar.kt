package com.example.plantsapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plantsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantsTopBar(title: String, navController: NavController, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onBackground),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.onBackground),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Align back button to the start
            ) {
                //image logo for the app
          /*      Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Plants Logo",
                    contentScale = ContentScale.Inside
                )*/
                //screen title ex: Plants, Plant details
                // Back arrow placed on top-left of the image (stacked over the image)
                if (!title.equals("Plants")) {
                    IconButton(
                        onClick = { navController.popBackStack() },
//                        modifier = Modifier
//                            .padding(start = 1.dp, top = 1.dp)
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft, contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.background // Change this to any color you want
                        )
                    }
                }
                // Spacer for centering the title
                Spacer(modifier = Modifier.weight(1f))


                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = title,
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))

                //search icon
              /*  Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.size(24.dp)
                )*/
            }
        }
    )
}
