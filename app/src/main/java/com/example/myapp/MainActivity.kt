package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content(modifier = Modifier.padding(40.dp))
        }
    }
}

class ViewModeForContent : ViewModel() {
    var stateFirstTerm by mutableIntStateOf(0)
    var stateSecondTerm by mutableIntStateOf(0)
    var stateResult by mutableIntStateOf(0)

    fun increaseFirstTerm() {
        stateFirstTerm++
    }

    fun reduceFirstTerm() {
        stateFirstTerm--
    }

    fun increaseSecondTerm() {
        stateSecondTerm++
    }

    fun reduceSecondTerm() {
        stateSecondTerm--
    }

    fun calculateResult() {
        stateResult = stateFirstTerm + stateSecondTerm
    }

}

@Composable
fun Content(vm: ViewModeForContent = viewModel() ,modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyText(text = vm.stateFirstTerm.toString())

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MyButton(text = "-", modifier = Modifier.weight(1.0f)) { vm.reduceFirstTerm() }
            MyButton(text = "+", modifier = Modifier.weight(1.0f)) { vm.increaseFirstTerm() }
        }

        MyText(text = "+")

        MyText(text = vm.stateSecondTerm.toString())

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MyButton(text = "-", modifier = Modifier.weight(1.0f)) { vm.reduceSecondTerm() }
            MyButton(text = "+", modifier = Modifier.weight(1.0f)) { vm.increaseSecondTerm() }
        }

        MyButton(text = "=", modifier = Modifier.fillMaxWidth()) { vm.calculateResult() }

        MyText(text = vm.stateResult.toString())
    }
}
@Composable
fun MyButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White),
        modifier = modifier
    ) {
        Text(text, fontSize = 28.sp)
    }
}
@Composable
fun MyText(text: String) {
    Text(text = text, fontSize = 32.sp)
}