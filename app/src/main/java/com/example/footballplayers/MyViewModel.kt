package com.example.footballplayers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var stateFirstName by mutableStateOf("")
    var stateLastName by mutableStateOf("")


}