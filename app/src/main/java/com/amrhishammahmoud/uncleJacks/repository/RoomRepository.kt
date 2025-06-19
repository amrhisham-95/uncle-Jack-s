package com.amrhishammahmoud.uncleJacks.repository

import androidx.lifecycle.LiveData
import com.amrhishammahmoud.uncleJacks.models.*
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoBillFinal
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoCartFinal
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoFavoriteBuy
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoFinalBuy
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoFruit
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoJacksMixes
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoJuice
import com.amrhishammahmoud.uncleJacks.roomDatabase.DaoServices
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RoomRepository @Inject constructor(
    private val dataDaoFruits: DaoFruit,
    private val dataDaoJuices: DaoJuice,
    private val dataDaoJacksMixes: DaoJacksMixes,
    private val dataDaoServices: DaoServices,
    private val dataDaoFinal: DaoFinalBuy,
    private val dataDaoFavorite: DaoFavoriteBuy,
    private val dataDaoBillFinal : DaoBillFinal,
    private val dataDaoCartFinal : DaoCartFinal
) {


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

    //for get the data directly from the service without put it into room database
    suspend fun getFruitsFromApiRepository(): List<Fruits> =
        KtorClient.httpClient.get("http://192.168.1.192:8100/fruits").body()


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

    //for get the data directly from the service without put it into room database
    suspend fun getJuicesFromApiRepository(): List<Juices> =
        KtorClient.httpClient.get("http://192.168.1.192:8100/juices").body()

    /*****************************************************************************/

    val readAllDataJacksMixes: LiveData<List<JacksMixes>> =
        dataDaoJacksMixes.readAllDataJacksMixes()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataJacksMixes(data: List<JacksMixes>) {
        dataDaoJacksMixes.addDataJacksMixes(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataJacksMixes(data: List<JacksMixes>) {
        dataDaoJacksMixes.updateDataJacksMixes(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataJacksMixes(data: List<JacksMixes>) {
        dataDaoJacksMixes.deleteDataJacksMixes(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataJacksMixes() {
        dataDaoJacksMixes.deleteAllDataJacksMixes()
    }

    //for get the data directly from the service without put it into room database
    suspend fun getJacksMixesFromApiRepository(): List<JacksMixes> =
        KtorClient.httpClient.get("http://192.168.1.192:8100/jacksMixes").body()

    /*****************************************************************************/

    val readAllDataServices: LiveData<List<Services>> = dataDaoServices.readAllDataServices()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataServices(data: List<Services>) {
        dataDaoServices.addDataServices(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataServices(data: List<Services>) {
        dataDaoServices.updateDataServices(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataServices(data: List<Services>) {
        dataDaoServices.deleteDataServices(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataServices() {
        dataDaoServices.deleteAllDataServices()
    }

    //for get the data directly from the service without put it into room database
    suspend fun getServicesFromApiRepository(): List<Services> =
        KtorClient.httpClient.get("http://192.168.1.192:8100/services").body()

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
    suspend fun getFinalDataAndPutItIntoRoomDBByDao(data: List<CartProductsFinal>) {
        dataDaoFinal.addDataFinal(data)
    }

    /*****************************************************************************/

    val readAllDataFavorite: LiveData<List<FavoriteProducts>> =
        dataDaoFavorite.readAllDataFavorite()

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
    suspend fun getFavoriteDataAndPutItIntoRoomDBByDao(data: List<FavoriteProducts>) {
        dataDaoFavorite.addDataFavorite(data)
    }
    /*****************************************************************************/


    suspend fun transferDataCartFinalToServer(cartFinal : CartProductsFinal) {
        KtorClient.httpClient.post("http://192.168.1.192:8100/cartFinal") {
            contentType(ContentType.Application.Json)
            setBody(cartFinal)
        }
    }



    /*******************************************************************************/


    val readAllDataBillFinal: LiveData<List<BillFinal>> = dataDaoBillFinal.readAllDataBillFinal()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataBillFinal(data: List<BillFinal>) {
        dataDaoBillFinal.addDataBillFinal(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataBillFinal(data: List<BillFinal>) {
        dataDaoBillFinal.updateADataBillFinal(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataBillFinal(data: List<BillFinal>) {
        dataDaoBillFinal.deleteDataBillFinal(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataBillFinal() {
        dataDaoBillFinal.deleteAllDataBillFinal()
    }


    suspend fun transferDataBillFinalToServer(billFinal : BillFinal) {
        KtorClient.httpClient.post("http://192.168.1.192:8100/billFinal") {
            contentType(ContentType.Application.Json)
            setBody(billFinal)
        }
    }

    /*******************************************************************************/


    val readAllDataCartFinal: LiveData<List<FinalCartData>> = dataDaoCartFinal.readAllDataCartFinal()

    //suspend fun To add data and use it in view model scope in (Main view model):
    suspend fun addDataCartFinal(data: List<FinalCartData>) {
        dataDaoCartFinal.addDataCartFinal(data)
    }

    //suspend fun To update data and use it in view model scope in (Main view model):
    suspend fun updateDataCartFinal(data: List<FinalCartData>) {
        dataDaoCartFinal.updateADataCartFinal(data)
    }

    //suspend fun To delete data and use it in view model scope in (Main view model):
    suspend fun deleteDataCartFinal(data: List<FinalCartData>) {
        dataDaoCartFinal.deleteDataCartFinal(data)
    }

    //suspend fun To delete all data and use it in view model scope in (Main view model):
    suspend fun deleteAllDataCartFinal() {
        dataDaoCartFinal.deleteAllDataCartFinal()
    }


    suspend fun transferDataCartFinalToServer(cartFinal : FinalCartData) {
        KtorClient.httpClient.post("http://192.168.1.192:8100/cartFinal") {
            contentType(ContentType.Application.Json)
            setBody(cartFinal)
        }
    }

    suspend fun transferData(cartFinal : CartProductsFinal) {
        KtorClient.httpClient.post("http://192.168.1.192:8100/cartFinal") {
            contentType(ContentType.Application.Json)
            setBody(cartFinal)
        }
    }

}