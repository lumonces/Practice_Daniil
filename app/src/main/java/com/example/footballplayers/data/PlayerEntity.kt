package com.example.footballplayers.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Player")
class PlayerEntity {
    @PrimaryKey(autoGenerate = true)
    private var id = 0
    private var firstName = ""
    private var lastName = ""

    constructor() {}

    constructor(id : Int, firstName : String, lastName : String) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
    }

    fun getId() : Int {
        return id
    }

    fun getFirstName() : String {
        return firstName
    }

    fun getLastName() : String {
        return lastName
    }

    fun setId(newId : Int) {
        this.id = newId
    }

    fun setFirstName(newFirstName : String) {
        this.firstName = newFirstName
    }

    fun setLastName(newLastName : String) {
        this.lastName = newLastName
    }
}