package com.example.footballplayers.data.mappers

import com.example.footballplayers.data.PlayerEntity
import com.example.footballplayers.domain.models.Player

fun Player.toPlayerEntity() = PlayerEntity(
    id = getId(),
    firstName = getFirstName(),
    lastName = getLastName()
)