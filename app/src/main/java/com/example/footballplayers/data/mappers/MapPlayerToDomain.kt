package com.example.footballplayers.data.mappers

import android.annotation.SuppressLint
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.footballplayers.data.PlayerEntity
import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.domain.repository.PlayerRepository

fun PlayerEntity.toPlayer() = Player(
    id = id,
    firstName = firstName,
    lastName = lastName
)

@SuppressLint("CheckResult")
fun PlayerEntityToPlayer(playerEntityLiveData: LiveData<PlayerEntity>) : LiveData<Player> {
    lateinit var player : Player
    playerEntityLiveData.map { playerEntity ->
        player = Player(
            id = playerEntity.id,
            firstName = playerEntity.firstName,
            lastName = playerEntity.lastName
        )
    }
    return MutableLiveData(player)
}

fun List<PlayerEntity>.toPlayer() = map {it.toPlayer()}