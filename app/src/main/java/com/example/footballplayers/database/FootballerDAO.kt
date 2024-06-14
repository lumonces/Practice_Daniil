package com.example.footballplayers.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FootballerDAO {
    @Query("SELECT * FROM Footballers")
    fun getFootballers() : LiveData<List<FootballerEntity>>

    @Query("SELECT * FROM Footballers WHERE id = :id")
    fun getFootballer(id : Int) : FootballerEntity

    @Insert
    fun addFootballer(footballer : FootballerEntity)

    @Query("DELETE FROM Footballers WHERE id = :id")
    fun deleteFootballer(id : Int)

    @Query("UPDATE Footballers SET firstName = :newFirstName, lastName = :newLastName WHERE id = :id")
    fun updateFootballer(id : Int, newFirstName : String, newLastName : String)
}