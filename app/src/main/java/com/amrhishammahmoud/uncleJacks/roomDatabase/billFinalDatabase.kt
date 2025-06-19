package com.amrhishammahmoud.uncleJacks.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amrhishammahmoud.uncleJacks.models.BillFinal

@Database(entities = [BillFinal::class], version = 1, exportSchema = false)
abstract class billFinalDatabase: RoomDatabase() {
    abstract fun dataDao(): DaoBillFinal

    companion object{
        @Volatile
        private var Instance : billFinalDatabase?=null
        fun getInstance(context: Context):billFinalDatabase{
            val tempInstance = Instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    billFinalDatabase::class.java,
                    "billFinal_DataBase").build()
                Instance = instance
                return instance
            }
        }
    }
}