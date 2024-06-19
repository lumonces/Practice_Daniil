package com.example.footballplayers.data.mappers

import com.example.footballplayers.data.PlayerEntity
import com.example.footballplayers.domain.models.Player

fun PlayerEntity.toPlayer() = Player(
    id = getId(),
    firstName = getFirstName(),
    lastName = getLastName()
)

fun List<PlayerEntity>.toPlayer() = map {it.toPlayer()}