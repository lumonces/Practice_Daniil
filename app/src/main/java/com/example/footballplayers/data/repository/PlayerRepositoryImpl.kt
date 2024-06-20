package com.example.footballplayers.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.footballplayers.data.PlayerDAO
import com.example.footballplayers.data.mappers.toPlayer
import com.example.footballplayers.data.mappers.toPlayerEntity
import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.domain.repository.PlayerRepository
import kotlin.math.log

private const val SHARED_PREFS = "shared_prefs"
private const val KEY_LOGIN = "login"
private const val KEY_PASSWORD = "password"
private const val DEFAULT_LOGIN = ""
private const val DEFAULT_PASSWORD = ""

class PlayerRepositoryImpl(
    private val playerDAO: PlayerDAO,
    context : Context
) : PlayerRepository {

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    override fun saveLoginPassword(login : String, password : String) {
        sharedPrefs.edit().putString(KEY_LOGIN, login).apply()
        sharedPrefs.edit().putString(KEY_PASSWORD, password).apply()
    }

    override fun getLogin() : String {
        return sharedPrefs.getString(KEY_LOGIN, DEFAULT_LOGIN) ?: DEFAULT_LOGIN
    }

    override fun getPassword() : String {
        return sharedPrefs.getString(KEY_PASSWORD, DEFAULT_PASSWORD) ?: DEFAULT_PASSWORD
    }

    override fun getAllPlayers(): LiveData<List<Player>> {
    return playerDAO.getAllPlayers().map {
        it.toPlayer()
    }
    }

    override suspend fun getPlayerById(id: Long): Player {
        return playerDAO.getPlayerById(id).toPlayer()
    }

    override suspend fun addPlayer(player: Player) {
        playerDAO.addPlayer(player.toPlayerEntity())
    }

    override suspend fun deletePlayer(id : Long) {
        playerDAO.deletePlayer(id)
    }

    override suspend fun updatePlayer(id: Long, newFirstName : String, newLastName : String) {
        playerDAO.updatePlayer(id, newFirstName, newLastName)
    }
}