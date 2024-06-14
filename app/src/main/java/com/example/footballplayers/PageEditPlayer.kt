package com.example.footballplayers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.footballplayers.database.FootballerEntity
import com.example.footballplayers.database.MyViewModel

@Composable
fun EditPlayer(navController: NavController, vm : MyViewModel = viewModel()) {
    val footballers by vm.footballerList.observeAsState(listOf())
    val footballer = footballers.first{it.id == vm.stateId }
    println(footballer.id)
    Scaffold(
        topBar = {
            AddTopBarToPage("${footballer.firstName.uppercase()} ${footballer.lastName.uppercase()}")
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFD9D9D9))
        ) {
            ContentForSpecificPlayer(vm, navController, footballer)
        }
    }
}

@Composable
fun ContentForSpecificPlayer(vm : MyViewModel, navController: NavController, footballer: FootballerEntity) {
    val initialFirstName by remember { mutableStateOf(footballer.firstName) }
    val initialLastName by remember { mutableStateOf(footballer.lastName) }
    LaunchedEffect(Unit) {
        vm.changeFirstName(initialFirstName)
        vm.changeLastName(initialLastName)
    }
    println(vm.stateFirstName + " " + vm.stateLastName)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = vm.stateFirstName,
            onValueChange = { vm.changeFirstName(it) },
            placeholder = { Text(text = "Firstname", fontSize = 23.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 24.sp)
        )

        TextField(
            value = vm.stateLastName,
            onValueChange = { vm.stateLastName = it },
            placeholder = { Text(text = "Lastname", fontSize = 23.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 24.sp)
        )

        Button(
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .height(60.dp),
            onClick = { handleEditButton(navController, vm, footballer) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4B55B6),
                contentColor = Color.White
            ),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Save", fontSize = 24.sp)
            }
        }
    }
}

fun handleEditButton(navController: NavController, vm: MyViewModel, footballer: FootballerEntity) {
    vm.updateFootballer(footballer.id, vm.stateFirstName, vm.stateLastName)
    navController.navigate(Routes.Players.route) {
        popUpTo(navController.graph.findStartDestination().id) {saveState = true}
        restoreState = true
    }
}