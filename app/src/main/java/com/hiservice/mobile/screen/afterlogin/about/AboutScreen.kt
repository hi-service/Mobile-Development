package com.hiservice.mobile.screen.afterlogin.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hiservice.mobile.R
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun AboutScreen(navigator : NavHostController){
    Column {
        TopHeadBar(text = "Tentang Aplikasi", isBack = true, onClick = {navigator.popBackStack()})
        Column (
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Divider(thickness = 36.dp, color = Color.Transparent)
            Image(
                painter = painterResource(id = R.drawable.logo_apps),
                contentDescription = "Logo Apps",
                Modifier.size(124.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(text = "Hi Service", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            Text(text = "versi 1.0", fontSize = 14.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Apa itu Hi Service?",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Hi Service adalah sebuah layanan digital yang bergerak untuk membantu UMKM terutama di bidang bengkel.\n" +
                        "\n" +
                        "Disini Hi Service menawarkan layanan untuk Home Service ataupun juga Service di tempat.\n" +
                        "\n" +
                        "Lalu, selain layanan tersebut, Kami juga menawarkan adanya Motor reminder dan Part shop yang membantu bengkel untuk menjual \n" +
                        "barang barang atau sparepart yang ada pada bengkel semua.\n" +
                        "\n" +
                        "Hi Service sendiri menargetkan pada target pasar para Mahasiswa atau para Pekerja yang mungkin kurang memiliki waktu luang untuk melakukan service mandiri \n" +
                        "atau ke tempat secara langsung.\n" +
                        "\n" +
                        "Lalu diharapkan juga pada Hi Service ini dapat membantu dalam pengurangan emisi karbon yang disebabkan oleh kurangnya perhatian dalam perawatan kendaraan bermotor.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Tim Pengembang",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Team pengembang dibagi menjadi 3 divisi yaitu Divisi Cloud Computing (CC), Divisi Mobile Develompent (MD), dan Divisi Machine Learning (ML)",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Divisi MD",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.img_contoh_pria),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    )
                    Spacer(Modifier.height(2.dp))
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .background(
                                color = YellowGold,
                                RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                            )
                    ){
                        Text(
                            text = "Ricky Triyoga W.",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }

                }
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.img_contoh_pria),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    )
                    Spacer(Modifier.height(2.dp))
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .background(
                                color = YellowGold,
                                RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                            )
                    ){
                        Text(
                            text = "Rizki Fauzi",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }

                }
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Divisi CC",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.img_contoh_pria),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    )
                    Spacer(Modifier.height(2.dp))
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .background(
                                color = YellowGold,
                                RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                            )
                    ){
                        Text(
                            text = "Mufti Kholil R.",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }

                }
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.img_contoh_pria),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    )
                    Spacer(Modifier.height(2.dp))
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .background(
                                color = YellowGold,
                                RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                            )
                    ){
                        Text(
                            text = "Rizal Fantofani",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }

                }
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Divisi ML",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.img_contoh_wanita),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    )
                    Spacer(Modifier.height(2.dp))
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .background(
                                color = YellowGold,
                                RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                            )
                    ){
                        Text(
                            text = "Dina Safitri Y.",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }

                }
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.img_contoh_wanita),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    )
                    Spacer(Modifier.height(2.dp))
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .background(
                                color = YellowGold,
                                RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                            )
                    ){
                        Text(
                            text = "Fath' Hana S. B.",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.img_contoh_wanita),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    )
                    Spacer(Modifier.height(2.dp))
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .background(
                                color = YellowGold,
                                RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                            )
                    ){
                        Text(
                            text = "Nadiyah Jihan F.",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Terima kasih",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun AboutScreenPreview(){
    HiServiceTheme {

    }
}
