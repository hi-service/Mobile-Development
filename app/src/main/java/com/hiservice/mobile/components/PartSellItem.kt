package com.hiservice.mobile.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun PartSellItem(
    image: String,
    title: String,
    harga: String?,
    phoneNumber: String,

    modifier: Modifier = Modifier,
    context: Context
){
    val hargaValue = harga!!.toInt()
    val formattedAmount = String.format("Rp%,d", hargaValue)
    Column(
        modifier = modifier
            .border(width = 1.dp, color = DarkCyan, RoundedCornerShape(15.dp))
    ){
        AsyncImage(
            model = "http://192.168.1.21/f-lomba/public/uploads/img/items/" + image,
            contentDescription = "Foto Part yang Dijual",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(130.dp)
        )

        Column (
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable{
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            java.lang.String.format(
                                "https://api.whatsapp.com/send?phone=%s&text=%s",
                                phoneNumber,
                                "Halo kak mau order barang $title"
                            )
                        )
                    ))
            }) {
                Image(painter = painterResource(id = R.drawable.logos_whatsapp_icon), contentDescription = "logowa" )
                Text(
                    text = "Order melalui WA",
                    fontSize = 12.sp,
                    color = Color(0xFF60D669)
                    ,modifier = Modifier.padding(horizontal = 2.dp)
                )
            }
            Text(
                //text = stringResource(R.string.part_price, amount),
                text = formattedAmount,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PartSellIntemPreview(){
    HiServiceTheme {
      //  PartSellItem(image = "https://www.trijayapart.com/wp-content/uploads/2021/03/Gear-Sepeda-Motor.png", title = "SSS 43T-520 Gear Belakang Motor for Honda Tiger", harga = 379000)
    }
}