package com.example.footballplayers.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDAO {
    @Query("SELECT * FROM Player")
    fun getAllPlayers() : LiveData<List<PlayerEntity>>
    @Query("SELECT * FROM Player WHERE id = :id")
    suspend fun getPlayerById(id : Long) : PlayerEntity

    @Insert
    suspend fun addPlayer(player : PlayerEntity)

    @Query("DELETE FROM Player WHERE id = :id")
    suspend fun deletePlayer(id : Long)

    @Query("UPDATE Player SET firstName = :newFirstName, lastName = :newLastName WHERE id = :id")
    suspend fun updatePlayer(id : Long, newFirstName : String, newLastName : String)
}