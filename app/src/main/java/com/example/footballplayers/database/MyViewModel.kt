package com.example.footballplayers.database

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MyViewModel(application: Application) : AndroidViewModel(application) {

    val footballerList : LiveData<List<FootballerEntity>>
    private val footballerRep : FootballerRep

    var stateId by mutableIntStateOf(0)
    var stateFirstName by mutableStateOf("")
    var stateLastName by mutableStateOf("")

    init {
        val footballerDao = FootballerRoom.getDataBase(application).footballerDao()
        footballerRep = FootballerRep(footballerDao)
        footballerList = footballerRep.allFootballers
    }

    fun addFootballer() {
        footballerRep.addFootballer(FootballerEntity(stateFirstName, stateLastName))
    }

    fun deleteFootballer(id : Int) {
        footballerRep.deleteFootballer(id)
    }

    fun updateFootballer(id : Int, newFirstName : String, newLastName : String) {
        footballerRep.updateFootballer(id, newFirstName, newLastName)
    }

    fun changeFirstName(newFirstName: String) {
        stateFirstName = newFirstName
    }

    fun changeLastName(newLastName: String) {
        stateLastName = newLastName
    }
}