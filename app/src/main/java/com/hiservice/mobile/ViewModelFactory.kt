package com.hiservice.mobile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.injection.Injection
import com.hiservice.mobile.screen.afterlogin.daftarbengkel.DaftarBengkelViewModel
import com.hiservice.mobile.screen.afterlogin.dashboard.DashboardViewModel
import com.hiservice.mobile.screen.afterlogin.profil.ProfilViewModel
import com.hiservice.mobile.screen.afterlogin.riwayat.history.RiwayatServiceViewModel
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.KeluhanViewModel
import com.hiservice.mobile.screen.afterlogin.services.detail_bengkel.DetailBengkelViewModel
import com.hiservice.mobile.screen.afterlogin.services.first_page_detail.FirstPageViewModel
import com.hiservice.mobile.screen.authentication.login.LoginViewModel
import com.hiservice.mobile.screen.authentication.register.RegisterViewModel
import com.hiservice.mobile.screen.part_shop.daftarbengkel.DaftarBengkelShopViewModel
import com.hiservice.mobile.screen.part_shop.detail_pengguna.UserShopDetailViewModel
import com.hiservice.mobile.screen.part_shop.explore_shop.ExploreShopViewModel
import com.hiservice.mobile.screen.splash.SplashScreenViewModel
import com.hiservice.mobile.screen.statusorder.StatusOrderViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(ProfilViewModel::class.java)) {
            return ProfilViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(UserShopDetailViewModel::class.java)) {
            return UserShopDetailViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(KeluhanViewModel::class.java)) {
            return KeluhanViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DaftarBengkelViewModel::class.java)) {
            return DaftarBengkelViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailBengkelViewModel::class.java)) {
            return DetailBengkelViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(StatusOrderViewModel::class.java)) {
            return StatusOrderViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(RiwayatServiceViewModel::class.java)) {
            return RiwayatServiceViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(FirstPageViewModel::class.java)) {
            return FirstPageViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DaftarBengkelShopViewModel::class.java)) {
            return DaftarBengkelShopViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(ExploreShopViewModel::class.java)) {
            return ExploreShopViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}