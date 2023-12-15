package com.example.unclejacks.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.unclejacks.models.CartProductsFinal
import com.example.unclejacks.models.FavoriteProducts
import com.example.unclejacks.models.Fruits
import com.example.unclejacks.models.Juices
import com.example.unclejacks.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: RoomRepository,
    val readAllDataFruits: LiveData<List<Fruits>>,
    val readAllDataJuices: LiveData<List<Juices>>,
    val readAllDataFinal: LiveData<List<CartProductsFinal>>,
    val readAllDataFavorite: LiveData<List<FavoriteProducts>>,
    application: Application
) : AndroidViewModel(application) {


    fun getDataFruitFromDateBase() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.getFruitDataAndPutItIntoRoomDBByDao()
            }
        }
    }


    fun getDataJuiceFromDateBase() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.getJuiceDataAndPutItIntoRoomDBByDao()
            }
        }
    }

    fun getAndPutDataFinalIntoDateBase(resultFinalName :String, resultFinalPrice:String, resultFinalPic:Int, resultFinalQuantity :String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.getFinalDataAndPutItIntoRoomDBByDao(resultFinalName,resultFinalPrice,resultFinalPic,resultFinalQuantity)
            }
        }
    }
    fun deleteData(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.deleteAllDataFinal()
            }

        }
            }


    /************************************************************************/

    fun getAndPutDataFavoriteIntoDateBase(resultFinalName :String, resultFinalPrice:String, resultFinalPic:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.getFavoriteDataAndPutItIntoRoomDBByDao(resultFinalName,resultFinalPrice,resultFinalPic)
            }
        }
    }
    fun deleteDataFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.deleteAllDataFavorite()
            }

        }
    }
    }

