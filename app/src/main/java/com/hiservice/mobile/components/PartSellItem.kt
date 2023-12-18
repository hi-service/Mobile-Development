package com.hiservice.mobile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun PartSellItem(
    image: String,
    title: String,
    harga: Int,
    modifier: Modifier = Modifier
){
    val formattedAmount = String.format("Rp%,d", harga)
    Column(
        modifier = modifier
            .border(width = 1.dp, color = DarkCyan, RoundedCornerShape(15.dp))
//            .background(color = GreyLight, RoundedCornerShape(10.dp))
    ){
        AsyncImage(
            model = image,
            contentDescription = "Foto Part yang Dijual",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(130.dp)
                .clip(RoundedCornerShape(15.dp))
//                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
//                .size(height = 108.dp, width = 140.dp)
        )

        Column (
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
            Text(
                //text = stringResource(R.string.part_price, amount),
                text = formattedAmount,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PartSellIntemPreview(){
    HiServiceTheme {
        PartSellItem(image = "https://www.trijayapart.com/wp-content/uploads/2021/03/Gear-Sepeda-Motor.png", title = "SSS 43T-520 Gear Belakang Motor for Honda Tiger", harga = 379000)
    }
}