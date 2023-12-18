package com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.Keluhan
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class KeluhanViewModel(private val repository: Repository) : ViewModel() {
    private val _itemsState = mutableStateListOf<Keluhan>()
    val itemsState: List<Keluhan> = _itemsState
    private val _itemsStateFlow = MutableStateFlow<List<Keluhan>>(mutableListOf())
    val itemsStateFlow: StateFlow<List<Keluhan>> get() = _itemsStateFlow
    private val _counter = mutableStateOf(0)
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading
    private val _session = MutableStateFlow<UserModel?>(null)

    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getDataKeluhan()
                }
            }

        }
    }

    private suspend fun getDataKeluhan(){
        try {
            var count = 0
            val response = ApiConfig.getApiService(_session.value!!.token).getKeluhan()
            response.data!!.forEach{
                count++
                _itemsState.add(
                    Keluhan(id = count.toString(), namaKeluhan = it!!.textKeluhan.toString(),false)
                )
            }
        } catch (e: HttpException) {
            e.message?.let { Log.e("Exception", it) }
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
    fun changeData(index: Int) {
            if(_itemsState[index].isChecklist){
                _counter.value--
                _itemsState[index] = _itemsState[index].copy(isChecklist = false)
            }else{
                if(_counter.value > 2){
                }else{
                    _counter.value++
                    _itemsState[index] = _itemsState[index].copy(isChecklist = true)
                    val currentList = _itemsStateFlow.value.toMutableList()
                    currentList.add(_itemsState[index])
                    _itemsStateFlow.value = currentList
                }
            }
    }
}