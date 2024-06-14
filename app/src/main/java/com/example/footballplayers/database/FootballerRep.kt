package com.example.footballplayers.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FootballerRep(private val footballerDAO: FootballerDAO) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allFootballers : LiveData<List<FootballerEntity>> = footballerDAO.getFootballers()

    fun addFootballer(footballer : FootballerEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            footballerDAO.addFootballer(footballer)
        }
    }

    fun deleteFootballer(id : Int) {
        coroutineScope.launch(Dispatchers.IO) {
            footballerDAO.deleteFootballer(id)
        }
    }

    fun updateFootballer(id : Int, newFirstName : String, newLastName : String) {
        coroutineScope.launch(Dispatchers.IO) {
            footballerDAO.updateFootballer(id, newFirstName, newLastName)
        }
    }
}