package com.hiservice.mobile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import coil.compose.AsyncImage
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun ItemKeranjang(
    partId: Long,
    image: String,
    title: String,
    totalHarga: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Foto Produk",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(120.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = modifier.width(12.dp))
        Column(
            modifier = modifier.height(120.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium
            )
            val formattedAmount = String.format("Rp%,d", totalHarga)
            Text(
                text = formattedAmount,
                color = DarkCyan,
                fontWeight = FontWeight.Bold
            )
            ProductCounter(
                orderId = partId,
                orderCount = count,
                onProductIncreased = { onProductCountChanged(partId, count + 1) },
                onProductDecreased = { onProductCountChanged(partId, count - 1) }
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ItemKeranjangPreview(){
    HiServiceTheme {
        ItemKeranjang(
            partId = 1,
            image = "https://www.trijayapart.com/wp-content/uploads/2021/03/Gear-Sepeda-Motor.png",
            title = "SSS 43T-520 Gear Belakang Motor for Honda Tiger",
            totalHarga = 379000,
            count = 1,
            onProductCountChanged = {rewardId, count ->}
        )
    }
}