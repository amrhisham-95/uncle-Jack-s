package com.example.unclejacks.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.unclejacks.models.*
import com.example.unclejacks.roomDatabase.DaoFavoriteBuy
import com.example.unclejacks.roomDatabase.DaoFinalBuy
import com.example.unclejacks.roomDatabase.DaoFruit
import com.example.unclejacks.roomDatabase.DaoJuice
import javax.inject.Inject


class RoomRepository @Inject constructor(private val dataDaoFruits: DaoFruit
,private val dataDaoJuices: DaoJuice,private val dataDaoFinal: DaoFinalBuy,private val dataDaoFavorite: DaoFavoriteBuy) {

    val readAllDataFruits: LiveData<List<Fruits>> = dataDaoFruits.readAllDataFruits()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataFruits(data: List<Fruits>) {
        dataDaoFruits.addDataFruits(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataFruits(data: List<Fruits>) {
        dataDaoFruits.updateADataFruits(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataFruits(data: List<Fruits>) {
        dataDaoFruits.deleteDataFruits(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataFruits() {
        dataDaoFruits.deleteAllDataFruits()
    }

    //to parse and insert fruit data to database by dao (with room data base)
    suspend fun getFruitDataAndPutItIntoRoomDBByDao() {
        val resultFruitName = DataFruitsApiObject.getFruitName()
        val resultFruitPrice = DataFruitsApiObject.getFruitPrice()
        val resultFruitPic = DataFruitsApiObject.getFruitPic()

        val array = ArrayList<Fruits>()
        var i = 0
        for (i in 0 until 14) {
            array.add(Fruits(resultFruitPic[i], resultFruitName[i], resultFruitPrice[i]))
        }

        dataDaoFruits.addDataFruits(array)
    }

    /*********************************************************************************************/


    val readAllDataJuices: LiveData<List<Juices>> = dataDaoJuices.readAllDataJuices()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataJuices(data: List<Juices>) {
        dataDaoJuices.addDataJuices(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataJuices(data: List<Juices>) {
        dataDaoJuices.updateDataJuices(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataJuices(data: List<Juices>) {
        dataDaoJuices.deleteDataJuices(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataJuices() {
        dataDaoJuices.deleteAllDataJuices()
    }

    //to parse and insert juice data to database by dao (with room data base)
    suspend fun getJuiceDataAndPutItIntoRoomDBByDao() {
        val resultJuiceName = DataFruitsApiObject.getJuiceName()
        val resultJuicePrice = DataFruitsApiObject.getJuicePrice()
        val resultJuicePic = DataFruitsApiObject.getJuicePic()

        val array = ArrayList<Juices>()
        var i = 0
        for (i in 0 until 13) {
            array.add(Juices(resultJuicePic[i], resultJuiceName[i], resultJuicePrice[i]))
        }

        dataDaoJuices.addDataJuices(array)
    }

/*****************************************************************************/

val readAllDataFinal: LiveData<List<CartProductsFinal>> = dataDaoFinal.readAllDataFinal()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataFinal(data: List<CartProductsFinal>) {
        dataDaoFinal.addDataFinal(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataFinal(data: List<CartProductsFinal>) {
        dataDaoFinal.updateDataFinal(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataFinal(data: List<CartProductsFinal>) {
        dataDaoFinal.deleteDataFinal(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataFinal() {
        dataDaoFinal.deleteAllDataFinal()
    }

    //to parse and insert fruit data to database by dao (with room data base)
    suspend fun getFinalDataAndPutItIntoRoomDBByDao(resultFinalName :String, resultFinalPrice:String, resultFinalPic:Int, resultFinalQuantity :String) {

        val array = ArrayList<CartProductsFinal>()
        var i = 0
        for (i in 0 until 100) {
            array.add(CartProductsFinal(resultFinalPic, resultFinalName, resultFinalPrice,resultFinalQuantity))
        }

        dataDaoFinal.addDataFinal(array)
    }

    /*****************************************************************************/

    val readAllDataFavorite: LiveData<List<FavoriteProducts>> = dataDaoFavorite.readAllDataFavorite()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataFavorite(data: List<FavoriteProducts>) {
        dataDaoFavorite.addDataFavorite(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataFavorite(data: List<FavoriteProducts>) {
        dataDaoFavorite.updateDataFavorite(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataFavorite(data: List<FavoriteProducts>) {
        dataDaoFavorite.deleteDataFavorite(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataFavorite() {
        dataDaoFavorite.deleteAllDataFavorite()
    }

    //to parse and insert fruit data to database by dao (with room data base)
    suspend fun getFavoriteDataAndPutItIntoRoomDBByDao(resultFinalName :String, resultFinalPrice:String, resultFinalPic:Int) {

        val array = ArrayList<FavoriteProducts>()
        var i = 0
        for (i in 0 until 100) {
            array.add(FavoriteProducts(resultFinalPic, resultFinalName, resultFinalPrice))
        }

        dataDaoFavorite.addDataFavorite(array)
    }

}