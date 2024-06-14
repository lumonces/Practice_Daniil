package com.example.footballplayers.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Footballers")
class FootballerEntity {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var firstName : String = ""
    var lastName : String = ""

    constructor() {}
    constructor(firstName : String, lastName : String) {
        this.firstName = firstName
        this.lastName = lastName
    }
}