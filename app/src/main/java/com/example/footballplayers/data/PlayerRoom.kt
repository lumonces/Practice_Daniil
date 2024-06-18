package com.example.footballplayers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.footballplayers.domain.Player

@Database(entities = [Player::class], version = 1)
abstract class PlayerRoom : RoomDatabase() {

    abstract fun playerDao() : PlayerDAO

    companion object {
        private var INSTANCE : PlayerRoom? = null

        fun getDataBase(context : Context) : PlayerRoom {
            synchronized(this) {
                var tempInstance = INSTANCE
                if(tempInstance == null) {
                    tempInstance = Room.databaseBuilder(
                        context.applicationContext,
                        PlayerRoom::class.java,
                        "Footballers_DataBase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = tempInstance
                }
                return tempInstance
            }
        }
    }
}