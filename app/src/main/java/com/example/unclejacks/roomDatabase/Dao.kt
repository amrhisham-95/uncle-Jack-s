package com.example.unclejacks.roomDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.unclejacks.models.CartProductsFinal
import com.example.unclejacks.models.FavoriteProducts
import com.example.unclejacks.models.Fruits
import com.example.unclejacks.models.Juices


@androidx.room.Dao
interface DaoFruit {

    //To read all data in data base :
    @Query("SELECT * FROM fruit_table ORDER BY itemPic ASC")
    fun readAllDataFruits() : LiveData<List<Fruits>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataFruits(data:List<Fruits>)

    //To update data in data base (i don't need it now but for learning):
    @Update
    suspend fun updateADataFruits(data: List<Fruits>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataFruits(data: List<Fruits>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM fruit_table")
    suspend fun deleteAllDataFruits()

}



@androidx.room.Dao
interface DaoJuice {

    //To read all data in data base :
    @Query("SELECT * FROM juice_table ORDER BY itemPic ASC")
    fun readAllDataJuices() : LiveData<List<Juices>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataJuices(data:List<Juices>)

    //To update data in data base (i don't need it now but for learning):
    @Update
    suspend fun updateDataJuices(data: List<Juices>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataJuices(data: List<Juices>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM juice_table")
    suspend fun deleteAllDataJuices()

}


@androidx.room.Dao
interface DaoFinalBuy{

    //To read all data in data base :
    @Query("SELECT * FROM final_table ORDER BY itemPicFinal ASC")
    fun readAllDataFinal() : LiveData<List<CartProductsFinal>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataFinal(data:List<CartProductsFinal>)

    //To update data in data base (i don't need it now but for learning):
    @Update
    suspend fun updateDataFinal(data: List<CartProductsFinal>)

    //To delete data in data base
    @Delete
    suspend fun deleteDataFinal(data: List<CartProductsFinal>)

    //To delete all data in data base
    @Query("DELETE FROM final_table")
    suspend fun deleteAllDataFinal()

}


@androidx.room.Dao
interface DaoFavoriteBuy{

    //To read all data in data base :
    @Query("SELECT * FROM favorite_table ORDER BY itemPicFavorite ASC")
    fun readAllDataFavorite() : LiveData<List<FavoriteProducts>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataFavorite(data:List<FavoriteProducts>)

    //To update data in data base (i don't need it now but for learning):
    @Update
    suspend fun updateDataFavorite(data: List<FavoriteProducts>)

    //To delete data in data base
    @Delete
    suspend fun deleteDataFavorite(data: List<FavoriteProducts>)

    //To delete all data in data base
    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllDataFavorite()

}
