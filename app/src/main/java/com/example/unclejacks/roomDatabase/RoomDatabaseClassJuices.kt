package com.example.unclejacks.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unclejacks.models.Fruits
import com.example.unclejacks.models.Juices


@Database(entities = [Juices::class], version = 1, exportSchema = false)
abstract class RoomDatabaseClassJuices: RoomDatabase() {
    abstract fun dataDao(): DaoJuice

    companion object{
        @Volatile
        private var Instance : RoomDatabaseClassJuices?=null
        fun getInstance(context: Context):RoomDatabaseClassJuices{
            val tempInstance = Instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    RoomDatabaseClassJuices::class.java,
                    "juice_DataBase").build()
                Instance = instance
                return instance
            }
        }
    }
}
