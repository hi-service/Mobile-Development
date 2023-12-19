@file:Suppress("DEPRECATION")

package com.hiservice.mobile.screen.afterlogin.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.WhiteReal
import com.services.finalsubmissionjetpackcompose.ui.navigation.Screen
import kotlin.math.absoluteValue

@Composable
fun CarouselCard(navController: NavHostController = rememberNavController()){
    val pagerState = rememberPagerState(initialPage = 0)
    val sliderList = listOf(
        R.drawable.imgarticle_ganti_oli,
        R.drawable.imgarticle_handle_rem,
        R.drawable.imgarticle_cvt_masalah
    )

    val sliderListID = listOf(
        1,
        2,
        3
    )

    val sliderListTitle = listOf(
        "Mengapa Harus Ganti Oli Motor Rutin? Ini Penjelasannya!",
        "Handle Rem Motor Terasa Blong? Ternyata Ini Penyebabnya",
        "Hati-Hati! Berikut 3 Faktor yang Membuat CVT Cepat Rusak"
    )

    val sliderListPublisherImg = listOf(
        R.drawable.logo_adirafinance,
        R.drawable.logo_suzuki,
        R.drawable.logo_otorider
    )

    val sldierListPublisherName = listOf(
        "Adira Finance | ",
        "Suzuki | ",
        "Otorider | "
    )

    val sliderListPublishDate = listOf(
        "19 Sep 2023",
        "12 Apr 2021",
        "23 Sep 2020"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier
                    .height(200.dp)
            ){page ->
                Card (
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.50f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                                .also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .background(color = WhiteReal)
                        .border(width = 1.dp, color = DarkCyan, RoundedCornerShape(15.dp))
                ){
                    Box(
                        modifier = Modifier
                            .clickable { navController.navigate(Screen.DetailArticle.createRoute(sliderListID[page])) },
                        Alignment.BottomStart
                    ){
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(sliderList[page])
                                .crossfade(true)
                                .scale((Scale.FILL))
                                .build(),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            contentDescription = null
                        )
                        Row (Modifier.padding(14.dp).background(color = WhiteReal.copy(alpha = 0.6f)).clip(RoundedCornerShape(15.dp))){
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(sliderListPublisherImg[page])
                                    .crossfade(true)
                                    .scale((Scale.FILL))
                                    .build(),
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(15.dp)),
                                contentDescription = null
                            )
                            Column {
                                Text(text = sliderListTitle[page], fontWeight = FontWeight.SemiBold)
                                Row {
                                    Text(text = sldierListPublisherName[page], fontSize = 12.sp)
                                    Text(text = sliderListPublishDate[page], fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarouselCardPreview(){
    HiServiceTheme {
        CarouselCard()
    }
}