package com.hiservice.mobile.screen.afterlogin.dashboard

import android.graphics.BlurMaskFilter.Blur
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun BoxMenuComponent(modifier: Modifier = Modifier, image: Int, text : String){
    Card(
        modifier = modifier
            .width(145.dp)
            .height(145.dp)
            .clip(RoundedCornerShape(15.dp)).clickable {
            },
        elevation = CardDefaults.cardElevation(defaultElevation =  10.dp),

        ) {
            Column(
                modifier = modifier.width(145.dp)
                    .height(145.dp).background(YellowGold),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = modifier.height(80.dp).width(80.dp),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = image),
                    contentDescription = "Button image"
                )
                Spacer(modifier = Modifier.height(2.dp).fillMaxWidth(0.7f))
                Text(text = text)
            }
    }
}
@Preview()
@Composable
fun GreetingPreview() {
    HiServiceTheme {
        BoxMenuComponent(image = R.drawable.consult_menu, text = "Consult Menu")
    }
}