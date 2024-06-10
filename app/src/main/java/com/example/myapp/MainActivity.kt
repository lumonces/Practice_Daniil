package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 40.dp, end = 40.dp, top = 50.dp, bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                AddFirstTerm()
                AddFirstButtons()
                AddPlus()
                AddSecondTerm()
                AddSecondButtons()
                AddEqual()
                AddResult()
            }
        }
    }
}

@Composable
fun AddFirstTerm() {
    Row (
        modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
    ) {
        Text(text = "0", fontSize = 32.sp, fontFamily = FontFamily.Monospace)
    }
}

@Composable
fun AddFirstButtons() {
    Row (
        modifier = Modifier
            .padding(bottom = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button (
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.weight(1f).padding(end = 15.dp)
        ) {
            Text("-", fontSize = 28.sp)
        }

        Button (
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.weight(1f).padding(start = 15.dp)
        ) {
            Text("+", fontSize = 28.sp)
        }
    }
}

@Composable
fun AddPlus() {
    Row (
        modifier = Modifier.padding(top = 25.dp, bottom = 25.dp)
    ) {
        Text(text = "+", fontSize = 32.sp, fontFamily = FontFamily.Monospace)
    }
}

@Composable
fun AddSecondTerm() {
    Row (
        modifier = Modifier.padding(top = 25.dp, bottom = 20.dp)
    ) {
        Text(text = "0", fontSize = 32.sp, fontFamily = FontFamily.Monospace)
    }
}

@Composable
fun AddSecondButtons() {
    Row (
        modifier = Modifier
            .padding(bottom = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button (
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.weight(1f).padding(end = 15.dp)
        ) {
            Text("-", fontSize = 28.sp)
        }

        Button (
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.weight(1f).padding(start = 15.dp)
        ) {
            Text("+", fontSize = 28.sp)
        }
    }
}

@Composable
fun AddEqual() {
    Row (
        modifier = Modifier.padding(top = 25.dp, bottom = 25.dp)
    ) {
        Button (
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("=", fontSize = 28.sp)
        }
    }
}

@Composable
fun AddResult() {
    Row (
        modifier = Modifier.padding(top = 25.dp)
    ) {
        Text(text = "0", fontSize = 32.sp, fontFamily = FontFamily.Monospace)
    }
}