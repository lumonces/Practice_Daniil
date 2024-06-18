package com.example.footballplayers.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.footballplayers.domain.Player

@Dao
interface PlayerDAO {
    @Query("SELECT * FROM Player")
    fun getAllPlayers() : LiveData<List<Player>>

    @Insert
    fun addPlayer(player : Player)

    @Query("DELETE FROM Player WHERE id = :id")
    fun deletePlayer(id : Int)

    @Query("UPDATE Player SET firstName = :newFirstName, lastName = :newLastName WHERE id = :id")
    fun updatePlayer(id : Int, newFirstName : String, newLastName : String)
}