package com.example.footballplayers.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDAO {
    @Query("SELECT * FROM Player")
    fun getAllPlayers() : LiveData<List<PlayerEntity>>

    @Insert
    suspend fun addPlayer(player : PlayerEntity)

    @Query("DELETE FROM Player WHERE id = :id")
    suspend fun deletePlayer(id : Int)

    @Query("UPDATE Player SET firstName = :newFirstName, lastName = :newLastName WHERE id = :id")
    suspend fun updatePlayer(id : Int, newFirstName : String, newLastName : String)
}