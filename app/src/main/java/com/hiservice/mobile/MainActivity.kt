package com.hiservice.mobile

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hiservice.mobile.screen.afterlogin.dashboard.DashboardScreen
import com.hiservice.mobile.screen.afterlogin.services.first_page_detail.FirstPageDetail
import com.hiservice.mobile.screen.authentication.login.LoginContent
import com.hiservice.mobile.screen.authentication.register.RegisterContent
import com.hiservice.mobile.screen.no_connection.NoConnection
import com.hiservice.mobile.screen.on_board.OnBoardingScreen
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
@Composable
fun HiService(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
    ) {
        NavHost(
            navController = navController,
            startDestination = if(isOnline(context)){
                Screen.Login.route
            }else{
                Screen.NoConnection.route
            }
        ) {
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
            composable(Screen.Service_Detail.route) {
                FirstPageDetail()
            }
            composable(Screen.NoConnection.route) {
                NoConnection {
                    navController.navigate("login")
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