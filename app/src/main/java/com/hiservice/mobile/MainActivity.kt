package com.hiservice.mobile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
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
import com.hiservice.mobile.data.model.SharedData
import com.hiservice.mobile.data.model.ShopSharedData
import com.hiservice.mobile.screen.afterlogin.about.AboutScreen
import com.hiservice.mobile.screen.afterlogin.article.DetailArticleScreen
import com.hiservice.mobile.screen.afterlogin.daftarbengkel.DaftarBengkel
import com.hiservice.mobile.screen.afterlogin.dashboard.DashboardScreen
import com.hiservice.mobile.screen.afterlogin.faq.FAQScreen
import com.hiservice.mobile.screen.afterlogin.profil.ImageSelectorAndCropper
import com.hiservice.mobile.screen.afterlogin.profil.ProfilScreen
import com.hiservice.mobile.screen.afterlogin.riwayat.history.RiwayatServiceContent
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.DaftarKeluhan
import com.hiservice.mobile.screen.afterlogin.services.detail_bengkel.DetailBengkelScreen
import com.hiservice.mobile.screen.afterlogin.services.first_page_detail.FirstPageDetail
import com.hiservice.mobile.screen.authentication.login.LoginContent
import com.hiservice.mobile.screen.authentication.register.RegisterContent
import com.hiservice.mobile.screen.no_connection.NoConnection
import com.hiservice.mobile.screen.on_board.OnBoardingScreen
import com.hiservice.mobile.screen.part_shop.daftarbengkel.DaftarBengkelShop
import com.hiservice.mobile.screen.part_shop.detail_pengguna.DetailPenggunaShop
import com.hiservice.mobile.screen.part_shop.explore_shop.ExploreShopContent
import com.hiservice.mobile.screen.splash.SplashScreenAnimation
import com.hiservice.mobile.screen.statusorder.ChatOrder
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
@SuppressLint("SuspiciousIndentation")
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
                viewModel.setShareData(SharedData("","",0.0,0.0,""))
                viewModel.setShopShareData(ShopSharedData(0.0,0.0))
                DashboardScreen(navigator = navController)

            }
            composable(Screen.Service_Status_Order.route) {
                StatusOrderScreen(navigator = navController)
            }
            composable(Screen.Service_Chat_Order.route) {
                ChatOrder(navigator = navController)
            }
            composable(Screen.Profile.route) {
                ProfilScreen(linkPhotoUser = "https://t3.ftcdn.net/jpg/02/99/04/20/360_F_299042079_vGBD7wIlSeNl7vOevWHiL93G4koMM967.jpg",navigator = navController)
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

            composable(Screen.History_Service.route) {
                RiwayatServiceContent(navigator = navController)
            }

            composable(Screen.Shop_User_Detail.route) {
                DetailPenggunaShop(navigator = navController, mainViewModel = viewModel)
            }
            composable(Screen.Shop_Bengkel_List.route) {
                DaftarBengkelShop(navigator = navController, mainViewModel = viewModel)
            }
            composable(
                route = Screen.Shop_Data_Item.route,
                arguments = listOf(navArgument("idBengkel") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("idBengkel") ?: 1
                ExploreShopContent(id = id, navigator = navController)
            }
            composable(Screen.Faq.route) {
                FAQScreen(navigator = navController)
            }
            composable(Screen.About.route) {
                AboutScreen(navController)
            }
            composable(
                route = Screen.DetailArticle.route,
                arguments = listOf(navArgument("articleID") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("articleID") ?: 1
                DetailArticleScreen(articleID = id, navigateBack = { navController.popBackStack() })
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HiServiceTheme {

    }
}