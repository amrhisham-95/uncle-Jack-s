package com.amrhishammahmoud.uncleJacks.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amrhishammahmoud.uncleJacks.models.BillFinal
import com.amrhishammahmoud.uncleJacks.models.CartProductsFinal
import com.amrhishammahmoud.uncleJacks.models.FavoriteProducts
import com.amrhishammahmoud.uncleJacks.models.FinalCartData
import com.amrhishammahmoud.uncleJacks.models.Fruits
import com.amrhishammahmoud.uncleJacks.models.JacksMixes
import com.amrhishammahmoud.uncleJacks.models.Juices
import com.amrhishammahmoud.uncleJacks.models.Services


@androidx.room.Dao
interface DaoFruit {

    //To read all data in data base :
    @Query("SELECT * FROM fruit_table ORDER BY id ASC")
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
    @Query("SELECT * FROM juice_table ORDER BY id ASC")
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

/**************************************************************************************************/
@androidx.room.Dao
interface DaoJacksMixes {

    //To read all data in data base :
    @Query("SELECT * FROM jacksMixes_table ORDER BY id ASC")
    fun readAllDataJacksMixes() : LiveData<List<JacksMixes>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataJacksMixes(data:List<JacksMixes>)

    //To update data in data base (i don't need it now but for learning):
    @Update
    suspend fun updateDataJacksMixes(data: List<JacksMixes>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataJacksMixes(data: List<JacksMixes>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM jacksMixes_table")
    suspend fun deleteAllDataJacksMixes()

}

/**************************************************************************************************/
@androidx.room.Dao
interface DaoServices {

    //To read all data in data base :
    @Query("SELECT * FROM services_table ORDER BY id ASC")
    fun readAllDataServices() : LiveData<List<Services>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataServices(data:List<Services>)

    //To update data in data base (i don't need)
    @Update
    suspend fun updateDataServices(data: List<Services>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataServices(data: List<Services>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM services_table")
    suspend fun deleteAllDataServices()

}


/**************************************************************************************************/
@androidx.room.Dao
interface DaoFavoriteBuy {

    //To read all data in data base :
    @Query("SELECT * FROM favorite_table ORDER BY itemPicFavorite ASC")
    fun readAllDataFavorite(): LiveData<List<FavoriteProducts>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataFavorite(data: List<FavoriteProducts>)

    //To update data in data base (i don't need)
    @Update
    suspend fun updateDataFavorite(data: List<FavoriteProducts>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataFavorite(data: List<FavoriteProducts>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllDataFavorite()

}


/**************************************************************************************************/
@androidx.room.Dao
interface DaoFinalBuy {

    //To read all data in data base :
    @Query("SELECT * FROM final_table ORDER BY timeDate ASC")
    fun readAllDataFinal(): LiveData<List<CartProductsFinal>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataFinal(data: List<CartProductsFinal>)

    //To update data in data base (i don't need)
    @Update
    suspend fun updateDataFinal(data: List<CartProductsFinal>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataFinal(data: List<CartProductsFinal>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM final_table")
    suspend fun deleteAllDataFinal()

}

/*******************************************************************************************************************************/

@androidx.room.Dao
interface DaoBillFinal {

    //To read all data in data base :
    @Query("SELECT * FROM bill_final_table ORDER BY timeDate ASC")
    fun readAllDataBillFinal() : LiveData<List<BillFinal>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataBillFinal(data:List<BillFinal>)

    //To update data in data base (i don't need it now but for learning):
    @Update
    suspend fun updateADataBillFinal(data: List<BillFinal>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataBillFinal(data: List<BillFinal>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM bill_final_table")
    suspend fun deleteAllDataBillFinal()

}


/*******************************************************************************************************************************/

@androidx.room.Dao
interface DaoCartFinal {

    //To read all data in data base :
    @Query("SELECT * FROM finalCart_table ORDER BY timeDate ASC")
    fun readAllDataCartFinal() : LiveData<List<FinalCartData>>

    //To insert data in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataCartFinal(data:List<FinalCartData>)

    //To update data in data base (i don't need it now but for learning):
    @Update
    suspend fun updateADataCartFinal(data: List<FinalCartData>)

    //To delete data in data base (i don't need it now but for learning):
    @Delete
    suspend fun deleteDataCartFinal(data: List<FinalCartData>)

    //To delete all data in data base ( i don't need it now but for learning):
    @Query("DELETE FROM finalCart_table")
    suspend fun deleteAllDataCartFinal()

}