package com.example.footballplayers.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopBarToPage(title: String) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .background(Color(0xFF4B55B6))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = title, fontSize = 28.sp)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF4B55B6),
            titleContentColor = Color.White
        )
    )
}