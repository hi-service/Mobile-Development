package com.hiservice.mobile

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.retrofit.gson.DetailBengkel
import com.hiservice.mobile.screen.afterlogin.daftarbengkel.DaftarBengkel
import com.hiservice.mobile.screen.afterlogin.dashboard.DashboardScreen
import com.hiservice.mobile.screen.afterlogin.profil.ProfilScreen
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.DaftarKeluhan
import com.hiservice.mobile.screen.afterlogin.services.detail_bengkel.DetailBengkelScreen
import com.hiservice.mobile.screen.afterlogin.services.first_page_detail.FirstPageDetail
import com.hiservice.mobile.screen.authentication.login.LoginContent
import com.hiservice.mobile.screen.authentication.register.RegisterContent
import com.hiservice.mobile.screen.no_connection.NoConnection
import com.hiservice.mobile.screen.on_board.OnBoardingScreen
import com.hiservice.mobile.screen.splash.SplashScreenAnimation
import com.hiservice.mobile.screen.statusorder.AlertRating
import com.hiservice.mobile.screen.statusorder.StatusOrderScreen
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.util.Connection.Companion.isOnline
import com.services.finalsubmissionjetpackcompose.ui.navigation.Screen

@Suppress("DEPRECATION")
private lateinit var auth: FirebaseAuth
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        setContent {
            HiServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    HiService()
                }
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HiService(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
    ) {
    val viewModel: MainViewModel = viewModel()
        NavHost(
            navController = navController,
            startDestination = if(isOnline(context)){
                Screen.Splash.route
            }else{
                Screen.NoConnection.route
            }
        ) {
            composable(Screen.About.route){

            }
            composable(Screen.Splash.route) {
                SplashScreenAnimation(navController)
            }
            composable(Screen.OnBoard.route) {
                OnBoardingScreen(navToLogin = {
                    navController.navigate("login")
                })
            }
            composable(Screen.Login.route) {
                LoginContent(navigator = navController)
            }
            composable(Screen.Register.route) {
                RegisterContent(navigator = navController)
            }
            composable(Screen.Dashboard.route) {
                DashboardScreen(navigator = navController)
            }
            composable(Screen.Service_Status_Order.route) {
                StatusOrderScreen(navigator = navController)
            }
            composable(Screen.Profile.route) {
                ProfilScreen(linkPhotoUser = "https://media.licdn.com/dms/image/D4E03AQEbGPRR9eGXLQ/profile-displayphoto-shrink_800_800/0/1694487158984?e=1707955200&v=beta&t=MGU0GsaG-PW8kZJdvWNIXsLNEP8Yvf_extevTCAfYRQ",nama = "Ricky Triyoga Wardhhana", nomorHp = "082131029815", navigator = navController)
            }
            composable(Screen.Service_Detail.route) {
                FirstPageDetail(navController,viewModel)
            }
            composable(Screen.Service_Keluhan_User.route) {
                DaftarKeluhan(navigator = navController)
            }
            composable(Screen.Service_Daftar_Bengkel.route) {
                DaftarBengkel(navController,viewModel)
            }
            composable(
                route = Screen.Service_Konfirmasi_Order.route,
                arguments = listOf(navArgument("idBengkel") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("idBengkel") ?: 1
                DetailBengkelScreen(id = id,navigator = navController, mainViewModel = viewModel)
            }
            composable(Screen.NoConnection.route) {
                NoConnection {
                    navController.navigate("splash")
                }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HiServiceTheme {

    }
}