package com.example.footballplayers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FootballerEntity::class], version = 1)
abstract class FootballerRoom : RoomDatabase() {

    abstract fun footballerDao() : FootballerDAO

    companion object {
        private var INSTANCE : FootballerRoom? = null

        fun getDataBase(context : Context) : FootballerRoom {
            synchronized(this) {
                var tempInstance = INSTANCE
                if(tempInstance == null) {
                    tempInstance = Room.databaseBuilder(
                        context.applicationContext,
                        FootballerRoom::class.java,
                        "Footballers_DataBase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = tempInstance
                }
                return tempInstance
            }
        }
    }

}