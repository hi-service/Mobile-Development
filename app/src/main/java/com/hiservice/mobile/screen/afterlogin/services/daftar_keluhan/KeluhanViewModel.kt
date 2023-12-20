package com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.fake_data.KeluhanFakeData.listKeluhan
import com.hiservice.mobile.data.model.Keluhan
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.data.retrofit.gson.DataKeluhanItem
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import com.hiservice.mobile.data.retrofit.gson.KeluhanResponse
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
    private val _dataGejala = mutableStateOf("")
    val dataGejala: State<String> get() = _dataGejala

    private val _gejalaDitemukan = mutableStateOf(false)
    val gejalaDitemukan: State<Boolean> get() = _gejalaDitemukan

    private val _btnEnabled = mutableStateOf(false)
    val btnEnabled: State<Boolean> get() = _btnEnabled

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

    private suspend fun getDataKeluhan() {
        try {
            var count = 0
            val response = ApiConfig.getApiService(_session.value!!.token).getKeluhan()
            response.data!!.forEach {
                count++
                _itemsState.add(
                    Keluhan(id = count.toString(), namaKeluhan = it!!.textKeluhan.toString(), false)
                )
            }
        } catch (e: HttpException) {
            e.message?.let { Log.e("Exception", it) }
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        } finally {

        }
    }

    fun setDataCancel() {
        _gejalaDitemukan.value = false
    }

    fun getDataGejala() {
        val joinedKeluhan = _itemsState
            .filter { it.isChecklist }
            .joinToString(";") { it.namaKeluhan.toLowerCase() }
        Log.d("Rick", joinedKeluhan)
        viewModelScope.launch {
            setDataKeluhan(joinedKeluhan)
        }

    }

    private suspend fun setDataKeluhan(gejala: String) {
        _loading.value = true
        try {
            val response = ApiConfig.getMlService().getAIGejala(gejala)
            _dataGejala.value = response.data!!
            _gejalaDitemukan.value = true
        } catch (e: HttpException) {
            e.message?.let { Log.e("Exception", it) }
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        } finally {
            _loading.value = false
        }
    }

    fun changeData(index: Int) {
        if (_itemsState[index].isChecklist) {
            _counter.value--
            _itemsState[index] = _itemsState[index].copy(isChecklist = false)
        } else {
            if (_counter.value > 2) {
            } else {
                _counter.value++
                _itemsState[index] = _itemsState[index].copy(isChecklist = true)
                val currentList = _itemsStateFlow.value.toMutableList()
                currentList.add(_itemsState[index])
                _itemsStateFlow.value = currentList
            }
        }
        _btnEnabled.value = _counter.value == 3
    }
}