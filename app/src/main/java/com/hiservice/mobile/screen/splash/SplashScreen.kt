package com.hiservice.mobile.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hiservice.mobile.R
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.YellowGold

@ExperimentalAnimationApi
@Composable
fun SplashScreenAnimation(navigator: NavHostController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    val showProgressBar = remember { mutableStateOf(false) }
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: SplashScreenViewModel = viewModel(factory = viewModelFactory)
    val session: State<UserModel?> = viewModel.session.collectAsState(initial = null)
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 3f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        showProgressBar.value = true
        if(session.value?.isLogin == true){
            navigator.navigate("dashboard"){
                popUpTo("splash") { inclusive = true }
            }
        }else{
            navigator.navigate("on-board"){
                popUpTo("splash") { inclusive = true }
            }
        }
    }


    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo_apps),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value))
            Spacer(modifier = Modifier.height(50.dp))
            if (showProgressBar.value) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                    color = YellowGold, backgroundColor = DarkCyan.copy(0.8f)
                )
            }
        }
        Box(modifier = Modifier.fillMaxSize()){
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "V.1.0.0",
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}