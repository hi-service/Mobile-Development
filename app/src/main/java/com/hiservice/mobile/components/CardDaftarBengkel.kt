package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.hiservice.mobile.data.model.BengkelModel
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun CardDaftarBengkel(
    DaftarBengkel : BengkelModel,
){
    var iconColor = YellowGold
    Column {
            LazyRow(content = {
                items(5) { index ->
                    if(index < DaftarBengkel.rating.toInt()){
                        iconColor = YellowGold
                    }else{
                        iconColor = Color(0xFFD9D9D9)
                    }
                    Icon(
                        Icons.Outlined.Star,
                        contentDescription = "Rating ",
                        tint = iconColor
                    )
                } })

    }
}