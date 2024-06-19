package com.example.footballplayers.domain.models

class Player {
    private var id = 0
    private var firstName = ""
    private var lastName = ""

    constructor() {}

    constructor(firstName : String, lastName : String) {
        this.firstName = firstName
        this.lastName = lastName
    }

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
}