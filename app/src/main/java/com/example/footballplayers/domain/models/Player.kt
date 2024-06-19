package com.example.footballplayers.domain.models

data class Player(
    var id : Long = 0,
    var firstName : String = "",
    var lastName : String = ""
) {
    constructor(firstName: String, lastName: String) : this() {
        this.firstName = firstName
        this.lastName = lastName
    }
}