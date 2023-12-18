package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hiservice.mobile.ui.theme.GreyLight
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun ItemHistory(
    namaService: String,
    tanggalService: String,
    namaBengkel: String,
    modifier: Modifier = Modifier
){
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
            Text(
                text = namaService,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = modifier.height(4.dp))
            Row (
                modifier = modifier
            ){
                Text(
                    text = "Bengkel : "
                )
                Text(
                    text = namaBengkel
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            Row (
                modifier = modifier
            ){
                Text(
                    text = "Tanggal : "
                )
                Text(
                    text = tanggalService
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
            namaService = "Service Lengkap",
            tanggalService = "Sabtu, 09 Desember 2023",
            namaBengkel = "Bengkel Motor Bapak Budi",
        )
    }
}