package com.hiservice.mobile.screen.afterlogin.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun BoxMenuComponent(modifier: Modifier = Modifier, image: Int, text : String,isActive : Boolean = false,onClick : () -> Unit = {}){
    val Color = remember {
        mutableStateOf(YellowGold)
    }
    if(isActive) {
        Color.value = Color(0xFFFFD200)
    }
    Card(
        modifier = modifier
            .width(145.dp)
            .height(145.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                       onClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation =  6.dp),

        ) {
            Column(
                modifier = modifier
                    .width(145.dp)
                    .height(145.dp)
                    .background(Color.value),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = modifier
                        .height(80.dp)
                        .width(80.dp),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = image),
                    contentDescription = "Button image"
                )
                Spacer(modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(0.7f))
                Text(text = text,modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
    }
}
@Composable
fun BoxArticleComponent(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .height(165.dp)
        .width(270.dp)
        .background(Color.White)){
        Image(
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.image12),
            contentDescription = "Button image"
        )
        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = "Ricky")
                Text(text = "Ricky")
            }
        }
    }
}
@Preview()
@Composable
fun GreetingPreview() {
    HiServiceTheme {
        BoxArticleComponent()
    }
}