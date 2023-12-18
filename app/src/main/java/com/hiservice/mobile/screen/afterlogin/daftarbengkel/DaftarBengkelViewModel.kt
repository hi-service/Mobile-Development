package com.hiservice.mobile.screen.afterlogin.daftarbengkel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.hiservice.mobile.MainViewModel
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DaftarBengkelViewModel (private val repository: Repository): ViewModel(){
    private val _bengkelRekomendasi = MutableStateFlow<List<DataListBengkel>>(mutableListOf())
    val bengkelRekomendasi: StateFlow<List<DataListBengkel>> get() = _bengkelRekomendasi

    private val _bengkelTerdekat = MutableStateFlow<List<DataListBengkel>>(mutableListOf())
    val bengkelTerdekat: StateFlow<List<DataListBengkel>> get() = _bengkelTerdekat
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    fun initViewModel(viewModel: MainViewModel){
        viewModelScope.launch {
            getDataRekomendasi(latLng = LatLng(viewModel.sharedData.value!!.lat,viewModel.sharedData.value!!.lng))
            getDataBengkelTerdekat(latLng = LatLng(viewModel.sharedData.value!!.lat,viewModel.sharedData.value!!.lng))
        }

    }
    private suspend fun getDataRekomendasi(latLng: LatLng){
        _loading.value = true
        try {
            val response = ApiConfig.getApiService("e098cf39-4786-417b-a7c2-8c2119228cb6").getLocBengkel(latLng.latitude,latLng.longitude)
            _bengkelRekomendasi.value = response.data!!
            _bengkelTerdekat.value.sortedBy { it.rating }
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }
    private suspend fun getDataBengkelTerdekat(latLng: LatLng){
        _loading.value = true
        try {
            val response = ApiConfig.getApiService("e098cf39-4786-417b-a7c2-8c2119228cb6").getLocBengkel(latLng.latitude,latLng.longitude)
            _bengkelTerdekat.value = response.data!!
            val bengkelTerdekatSorted = _bengkelTerdekat.value.sortedBy { it.jarak }
            _bengkelTerdekat.value = bengkelTerdekatSorted
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }
}