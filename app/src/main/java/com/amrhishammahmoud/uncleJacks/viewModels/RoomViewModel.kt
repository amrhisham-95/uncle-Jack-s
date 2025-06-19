package com.amrhishammahmoud.uncleJacks.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amrhishammahmoud.uncleJacks.models.BillFinal
import com.amrhishammahmoud.uncleJacks.models.CartProductsFinal
import com.amrhishammahmoud.uncleJacks.models.FavoriteProducts
import com.amrhishammahmoud.uncleJacks.models.FinalCartData
import com.amrhishammahmoud.uncleJacks.models.Fruits
import com.amrhishammahmoud.uncleJacks.models.JacksMixes
import com.amrhishammahmoud.uncleJacks.models.Juices
import com.amrhishammahmoud.uncleJacks.models.Services
import com.amrhishammahmoud.uncleJacks.repository.RoomRepository
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
    val readAllDataJacksMixes: LiveData<List<JacksMixes>>,
    val readAllDataServices: LiveData<List<Services>>,
    val readAllDataFinal: LiveData<List<CartProductsFinal>>,
    val readAllDataCartFinal: LiveData<List<FinalCartData>>,
    val readAllDataBillFinal : LiveData<List<BillFinal>>,
    val readAllDataFavorite: LiveData<List<FavoriteProducts>>,
    val mutibaleLiveDataFruits: MutableLiveData<List<Fruits>>,
    val mutibaleLiveDataJuices: MutableLiveData<List<Juices>>,
    val mutibaleLiveDataJacksMixes: MutableLiveData<List<JacksMixes>>,
    val mutibaleLiveDataServices: MutableLiveData<List<Services>>,
    val mutibaleLiveDataFinalCart: MutableLiveData<List<CartProductsFinal>>,
    val mutibaleLiveDataBillFinal : MutableLiveData<List<BillFinal>>,
    val mutibaleLiveDataCartFinal : MutableLiveData<List<FinalCartData>>,
    application: Application
) : AndroidViewModel(application) {


    fun getDataRetrofitViewModelFruits() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val x = repository.getFruitsFromApiRepository()
                mutibaleLiveDataFruits.postValue(x)
            }
        }
    }

    fun addFruitsDataViewModel(data: List<Fruits>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDataFruits(data)
        }
    }


    //Fun to delete all fruits:
    fun deleteAllFruitsDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDataFruits()
        }
    }

    fun updateDataViewModelFruits(data: List<Fruits>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDataFruits(data)
        }
    }

    /**********************************************************************************************/

    fun getDataRetrofitViewModelJuices() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val x = repository.getJuicesFromApiRepository()
                mutibaleLiveDataJuices.postValue(x)
            }
        }
    }

    fun addJuicesDataViewModel(data: List<Juices>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDataJuices(data)
        }
    }


    //Fun to delete all fruits:
    fun deleteAllJuicesDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDataJuices()
        }
    }

    fun updateDataViewModelJuices(data: List<Juices>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDataJuices(data)
        }
    }

    /**********************************************************************************************/


    fun getDataRetrofitViewModelJacksMixes() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val x = repository.getJacksMixesFromApiRepository()
                mutibaleLiveDataJacksMixes.postValue(x)
            }
        }
    }

    fun addJacksMixesDataViewModel(data: List<JacksMixes>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDataJacksMixes(data)
        }
    }


    //Fun to delete all fruits:
    fun deleteAllJacksMixesDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDataJacksMixes()
        }
    }

    fun updateDataViewModelJacksMixes(data: List<JacksMixes>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDataJacksMixes(data)
        }
    }

    /**********************************************************************************************/

    fun getDataRetrofitViewModelServices() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val x = repository.getServicesFromApiRepository()
                mutibaleLiveDataServices.postValue(x)
            }
        }
    }

    fun addServicesDataViewModel(data: List<Services>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDataServices(data)
        }
    }


    //Fun to delete all fruits:
    fun deleteAllServicesDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDataServices()
        }
    }

    fun updateDataViewModelServices(data: List<Services>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDataServices(data)
        }
    }

    /**********************************************************************************************/


    fun getAndPutDataFinalIntoDateBase(
        data: List<CartProductsFinal>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.getFinalDataAndPutItIntoRoomDBByDao(
                    data
                )
            }
        }
    }

    fun deleteData() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.deleteAllDataFinal()
            }

        }
    }


    /************************************************************************/

    fun getAndPutDataFavoriteIntoDateBase(
        data: List<FavoriteProducts>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.getFavoriteDataAndPutItIntoRoomDBByDao(
                    data
                )
            }
        }
    }

    fun deleteDataFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.deleteAllDataFavorite()
            }

        }
    }

    /************************************************************************/

    fun postFinalDataCartToServer(data: CartProductsFinal) {
        viewModelScope.launch(Dispatchers.IO) {
             repository.transferDataCartFinalToServer(data)
        }
    }

    /**********************************************************************************************/

    fun addBillFinalDataViewModel(data: List<BillFinal>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDataBillFinal(data)
        }
    }


    //Fun to delete all fruits:
    fun deleteAllBillFinalDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDataBillFinal()
        }
    }

    fun updateDataViewModelBillFinal(data: List<BillFinal>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDataBillFinal(data)
        }
    }


    fun postBillFinalToServer(data: BillFinal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.transferDataBillFinalToServer(data)
        }
    }


    /**********************************************************************************************/

    fun addCartFinalDataViewModel(data: List<FinalCartData>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDataCartFinal(data)
        }
    }


    //Fun to delete all fruits:
    fun deleteAllCartFinalDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDataCartFinal()
        }
    }

    fun updateDataViewModelCartFinal(data: List<FinalCartData>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDataCartFinal(data)
        }
    }


    fun postCartFinalToServer(data: FinalCartData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.transferDataCartFinalToServer(data)
        }
    }


}

