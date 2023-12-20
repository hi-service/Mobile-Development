package com.hiservice.mobile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hiservice.mobile.data.retrofit.gson.DataBengkelShopItem
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun CardDaftarBengkel(
    daftarBengkel: DataListBengkel,
    modifier:Modifier = Modifier,
    OnClick : () -> Unit
){
    var iconColor = YellowGold
    Row(Modifier.clickable{
        OnClick.invoke()
    }) {
        AsyncImage(
            model = daftarBengkel.url_photo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(15))
        )
        Spacer(modifier = modifier.width(8.dp))
        Column {
            Text(
                text = daftarBengkel.namaBengkel!!,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Row {
                Text(text = daftarBengkel.rating.toString())
                Spacer(modifier = modifier.width(8.dp))
                LazyRow(
                    content = {
                        items(5) { index ->
                            if(index < daftarBengkel.rating!!.toInt()){
                                iconColor = YellowGold
                            }else{
                                iconColor = Color(0xFFD9D9D9)
                            }
                            Icon(
                                Icons.Outlined.Star,
                                contentDescription = "Rating ",
                                tint = iconColor
                            )
                        }
                    }
                )
            }
            Text(text = "Jarak : ${daftarBengkel.jarak!!.toInt()} Km", color = GreyDark)
        }
    }
}
@Composable
fun CardDaftarBengkelShop(
    daftarBengkel: DataBengkelShopItem,
    modifier:Modifier = Modifier,
    OnClick : () -> Unit
){
    var iconColor = YellowGold
    Row(Modifier.clickable{
        OnClick.invoke()
    }) {
        AsyncImage(
            model = daftarBengkel.urlPhoto,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(15))
        )
        Spacer(modifier = modifier.width(8.dp))
        Column {
            Text(
                text = daftarBengkel.namaBengkel!!,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Text(text = "Total Barang Dimiliki : ${daftarBengkel.totalBarang!!}", color = GreyDark)
            Text(text = "Alamat :", color = GreyDark)
            Text(text = daftarBengkel.alamat_bengkel!!, color = GreyDark)
            Text(text = "Jarak : ${daftarBengkel.jarak!!} Km", color = GreyDark)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CardDaftarBengkelPreview() {
    HiServiceTheme {
    }
}