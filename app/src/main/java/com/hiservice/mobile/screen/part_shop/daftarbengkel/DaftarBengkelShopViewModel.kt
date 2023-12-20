package com.hiservice.mobile.screen.part_shop.daftarbengkel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.hiservice.mobile.MainViewModel
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.data.retrofit.gson.DataBengkelShopItem
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DaftarBengkelShopViewModel (private val repository: Repository): ViewModel() {

    private val _bengkelTerdekat = MutableStateFlow<List<DataBengkelShopItem>>(mutableListOf())
    val bengkelTerdekat: StateFlow<List<DataBengkelShopItem>> get() = _bengkelTerdekat
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading
    private val _session = MutableStateFlow<UserModel?>(null)
    fun initViewModel(viewModel: MainViewModel) {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getDataRekomendasi(
                        latLng = LatLng(
                            viewModel.shopSharedData.value!!.lat,
                            viewModel.shopSharedData.value!!.lng
                        )
                    )
                }
            }
        }
    }

    private suspend fun getDataRekomendasi(latLng: LatLng) {
        _loading.value = true
        try {
            val response = ApiConfig.getApiService(_session.value!!.token)
                .getBengkelShop(latLng.latitude,latLng.longitude)
            _bengkelTerdekat.value = response.dataBengkelShop!!
            val bengkelTerdekatSorted = _bengkelTerdekat.value.sortedBy { it.jarak }
            _bengkelTerdekat.value = bengkelTerdekatSorted
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        } finally {
            _loading.value = false
        }
    }
}
