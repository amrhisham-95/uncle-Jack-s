package com.amrhishammahmoud.uncleJacks.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amrhishammahmoud.uncleJacks.models.BillFinal
import com.amrhishammahmoud.uncleJacks.models.FinalCartData

@Database(entities = [FinalCartData::class], version = 1, exportSchema = false)
abstract class CartFinalDatabase: RoomDatabase() {
    abstract fun dataDao(): DaoCartFinal

    companion object{
        @Volatile
        private var Instance : CartFinalDatabase?=null
        fun getInstance(context: Context):CartFinalDatabase{
            val tempInstance = Instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    CartFinalDatabase::class.java,
                    "cartFinal_DataBase").build()
                Instance = instance
                return instance
            }
        }
    }
}