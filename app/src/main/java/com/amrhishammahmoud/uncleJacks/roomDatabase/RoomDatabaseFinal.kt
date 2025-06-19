package com.amrhishammahmoud.uncleJacks.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amrhishammahmoud.uncleJacks.models.CartProductsFinal


@Database(entities = [CartProductsFinal::class], version = 1, exportSchema = false)
abstract class RoomDatabaseFinal: RoomDatabase() {
    abstract fun dataDao(): DaoFinalBuy

    companion object{
        @Volatile
        private var Instance : RoomDatabaseFinal?=null
        fun getInstance(context: Context):RoomDatabaseFinal{
            val tempInstance = Instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    RoomDatabaseFinal::class.java,
                    "final_DataBase").build()
                Instance = instance
                return instance
            }
        }
    }
}