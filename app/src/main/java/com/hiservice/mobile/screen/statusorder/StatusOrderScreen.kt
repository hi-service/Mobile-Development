package com.hiservice.mobile.screen.statusorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hiservice.mobile.R
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.model.StatusOrderModel
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.GreyDark

@Composable
fun StatusOrderScreen(){
    var data : StatusOrderModel = StatusOrderModel(statusOrder = "waiting", idOrder = 123444, time = "13:00:00 12 Dec 2023",image = R.drawable.statusorder_waiting, text = "Dalam proses...", subText = "Harap tunggu, pesanan anda sedang diproses", isButton = true)
    val status = remember {
        mutableStateOf(data)
    }
    Column {
        TopHeadBar(text = "Status Order", isBack = true, onClick = {

        })
        Text(
            text =  "#${status.value.idOrder} - ${status.value.time}",
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = GreyDark,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(180.dp))
        Image(painter = painterResource(id = status.value.image), contentDescription = "Image Status")
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text =  status.value.text,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = DarkCyan,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text =  status.value.subText,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = GreyDark,
            modifier = Modifier.fillMaxWidth()
        )
        if(status.value.isButton){
            ButtonBig(text = "Batalkan", onClick = {
            }, buttonColors = Color.Red, modifier = Modifier.padding(40.dp), textColor = Color.White)
        }

    }
}