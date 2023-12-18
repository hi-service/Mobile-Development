package com.hiservice.mobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun TopHeadBar(modifier: Modifier = Modifier,onClick: ()-> Unit = {}, isBack : Boolean = false,text : String) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            if(isBack){
                Icon(
                    painter = painterResource(R.drawable.back_btn),
                    contentDescription = stringResource(id = R.string.bus_content_description),
                    modifier = modifier
                        .clickable { onClick() }
                        .size(50.dp)
                        .padding(vertical = 10.dp, horizontal = 12.dp)
                )
            }else{
                Box(
                    contentAlignment= Alignment.Center,
                    modifier = Modifier
                        .size(50.dp)
                        .border(
                            width = 2.dp,
                            color = DarkCyan,
                            shape = CircleShape
                        ),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .clickable { onClick() },
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF263238)
            )
            Box(modifier = modifier.size(50.dp))
        }
    }
}

@Composable
fun TopHeadBarPartShop(modifier: Modifier = Modifier,onClickBack: ()-> Unit = {}, onClickCart: ()-> Unit = {}, text : String) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(R.drawable.back_btn),
                contentDescription = stringResource(id = R.string.bus_content_description),
                modifier = modifier
                    .clickable { onClickBack() }
                    .size(50.dp)
                    .padding(vertical = 10.dp, horizontal = 12.dp)
            )
            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF263238)
            )
            Icon(
                painter = painterResource(R.drawable.shopping_cart),
                contentDescription = "Icon Cart",
                modifier = modifier
                    .clickable{onClickCart()}
                    .size(50.dp)
                    .padding(vertical = 10.dp, horizontal = 12.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopHeadBarPreview(){
    HiServiceTheme {
        TopHeadBar(onClick = {}, text = "Explore", isBack = true)
    }
}

@Composable
@Preview(showBackground = true)
fun TopHeadBarPartShopPreview(){
    HiServiceTheme {
        TopHeadBarPartShop(onClickBack = {}, onClickCart = {}, text = "Explore")
    }
}