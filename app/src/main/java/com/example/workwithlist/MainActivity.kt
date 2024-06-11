package com.example.workwithlist

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content(this)
        }
    }
}

@Composable
fun Content(context: Context) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 30.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1_000_000) { i ->
            AddClickableItem(item = "Item ${i+1}", context)
        }
    }
}

@Composable
fun AddClickableItem(item: String, context: Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .border(width = 2.dp, color = Color.Black)
            .height(40.dp)
            .clickable { handleClick(item, context) },
        contentAlignment = Alignment.Center
    ) {
        Text(item, fontSize = 28.sp)
    }
}

fun handleClick(item: String, context: Context) {
    val toast = Toast.makeText(context, "Clicked: $item", Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}