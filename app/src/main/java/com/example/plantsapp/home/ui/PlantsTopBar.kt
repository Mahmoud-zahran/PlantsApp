package com.example.plantsapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.example.plantsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantsTopBar(title:String,modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onBackground),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.onBackground),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(Modifier.size(1.dp))
                //image logo for the app
//                Image(
//                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                    contentDescription = "Plants Logo",
//                    contentScale = ContentScale.Inside
//                )
                //screen title ex: Plants, Plant details
                Text(
                    text = title, // Replace with your text
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}
