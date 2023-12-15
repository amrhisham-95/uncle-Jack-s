package com.example.unclejacks.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unclejacks.models.FavoriteProducts


@Database(entities = [FavoriteProducts::class], version = 1, exportSchema = false)
abstract class RoomDatabaseFavorite: RoomDatabase() {
    abstract fun dataDao(): DaoFavoriteBuy

    companion object{
        @Volatile
        private var Instance : RoomDatabaseFavorite?=null
        fun getInstance(context: Context):RoomDatabaseFavorite{
            val tempInstance = Instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    RoomDatabaseFavorite::class.java,
                    "favorite_DataBase").build()
                Instance = instance
                return instance
            }
        }
    }
}
