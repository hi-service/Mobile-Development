package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.WhiteReal

@Composable
fun ProductCounter(
    orderId: Long,
    orderCount: Int,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .background(color = DarkCyan, RoundedCornerShape(8.dp)),
            Alignment.Center
        ) {
            Icon(
                painterResource(R.drawable.ic_minus),
                contentDescription = "- Button",
                modifier = Modifier
                    .clickable {
                        onProductDecreased(orderId)
                    }
                    .size(16.dp),
                tint = WhiteReal
            )
        }
        Text(
            text = orderCount.toString(),
            modifier = modifier
                .padding(start = 4.dp, end = 4.dp)
                .width(50.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Box(
            modifier = Modifier
                .size(26.dp)
                .background(color = DarkCyan, RoundedCornerShape(8.dp)),
            Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = "+ Button",
                modifier = Modifier
                    .clickable {
                        onProductIncreased(orderId)
                    }
                    .size(16.dp),
                tint = WhiteReal
            )

        }
    }
}

@Preview
@Composable
fun ProductCounterPreview() {
    HiServiceTheme {
        ProductCounter(
            orderId = 1,
            orderCount = 0,
            onProductIncreased = { },
            onProductDecreased = { }
        )
    }
}