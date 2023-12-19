package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.GreenFun
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.GreyLight
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.RedNotFun

@Composable
fun ItemHistory(
    orderID: String,
    namaService: String,
    tanggalService: String,
    namaBengkel: String,
    descService: String,
    kmService: String,
    kmNext: String,
    status: String,
    modifier: Modifier = Modifier
){
    val color: Color = if (status == "finished") GreenFun else if (status=="ongoing") DarkCyan else RedNotFun
    val statusKmService: String = if (status == "finished") kmService else "-"
    val statusKmNext: String = if (status == "finished") kmNext else "-"
    Box(
        modifier = modifier
            .background(color = GreyLight, RoundedCornerShape(15.dp)),
        Alignment.Center
    ){
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Row {
                Text(
                    text = namaService,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(text = "#ID $orderID",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Divider(modifier.padding(vertical = 12.dp), thickness = 1.dp, color = GreyDark)
            Row (
                modifier = modifier
            ){
                Text(
                    text = "Tanggal : "
                )
                Text(
                    text = tanggalService,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = modifier.padding(vertical = 4.dp))
            Row (
                modifier = modifier
            ){
                Text(
                    text = "Bengkel : "
                )
                Text(
                    text = namaBengkel,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = modifier.padding(vertical = 4.dp))
            Row (
                modifier = modifier
            ){
                Text(
                    text = "Deskripsi : "
                )
                Text(
                    text = descService,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Divider(modifier.padding(vertical = 12.dp), thickness = 1.dp, color = GreyDark)
            Text(text = "Km ketika di Service : ")
            Text(text = statusKmService,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(text = "Dianjurakan Service lagi di Km :")
            Text(
                text = statusKmNext,
                fontWeight = FontWeight.SemiBold)
            Divider(modifier.padding(vertical = 12.dp), thickness = 1.dp, color = GreyDark)
            Row (
                modifier = modifier
            ){
                Text(text = "Status Order : ")
                Text(
                    text = status,
                    fontWeight = FontWeight.SemiBold,
                    color = color
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemHistoryPreview(){
    HiServiceTheme {
        ItemHistory(
            orderID = "12",
            namaService = "Service Lengkap",
            tanggalService = "Sabtu, 09 Desember 2023",
            namaBengkel = "Bengkel Motor Bapak Budi",
            descService = "Ganti oli, ganti kanvas rem,ganti ban tubless",
            kmService = "4000",
            kmNext = "6000",
            status = "Selesai"
        )
    }
}