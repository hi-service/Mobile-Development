package com.hiservice.mobile.screen.afterlogin.services.detail_bengkel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Divider
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
import coil.compose.AsyncImage
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.ReviewerCard
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.BengkelFakeData
import com.hiservice.mobile.data.model.BengkelModel
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun DetailBengkel(
    daftarBengkel: BengkelModel,
    linkPhotoBengkel: String,
    rateNumber: Double,
    namaBengkel: String,
    descBengkel: String,
    pemilikBengkel: String,
    alamatBengkel: String,
    jamBuka: String,
    modifier: Modifier = Modifier
){
    var iconColor = YellowGold
    Column(modifier = modifier.fillMaxSize()) {
        TopHeadBar(text = "Detail Bengkel", onClick = {

        }, isBack = true)
        Column (modifier.padding(horizontal = 32.dp)){
            AsyncImage(
                model = linkPhotoBengkel,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(15))
            )
            Spacer(modifier = modifier.height(8.dp))
            Row {
                Text(text = "Nama : ")
                Text(text = namaBengkel, fontWeight = FontWeight.Medium)
            }
            Row {
                Text(text = "Rating : ")
                Text(text = rateNumber.toString(), fontWeight = FontWeight.Medium)
                LazyRow(
                    content = {
                        items(5) { index ->
                            if(index < daftarBengkel.rating.toInt()){
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
            Divider(color = GreyDark, modifier = modifier.padding(vertical = 12.dp))
            Text(text = descBengkel)
            Row {
                Text(text = "Pemilik : ")
                Text(text = pemilikBengkel, fontWeight = FontWeight.Medium)
            }
            Row {
                Text(text = "Alamat : ")
                Text(text = alamatBengkel, fontWeight = FontWeight.Medium)
            }
            Row {
                Text(text = "Jam Buka : ")
                Text(text = jamBuka, fontWeight = FontWeight.Medium)
            }
            Divider(color = GreyDark, modifier = modifier.padding(vertical = 12.dp))
            ReviewerCard(
                daftarBengkel = BengkelFakeData.listBengkel[0],
                linkPhotoReviewer = "https://unsplash.com/photos/brown-wooden-table-with-chairs-ngLt4Y1vI_Q",
                namaReviewer = "Bengkel Bapak Udin",
                isiReview = "Sangatlah mantap"
            )
            Spacer(modifier = modifier.height(24.dp))
            ButtonBig(text = "Lanjut") {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailBengkelPreview() {
    HiServiceTheme {
        DetailBengkel(
            daftarBengkel = BengkelFakeData.listBengkel[0],
            linkPhotoBengkel = "https://unsplash.com/photos/brown-wooden-table-with-chairs-ngLt4Y1vI_Q",
            rateNumber  = 4.7,
            namaBengkel = "Bengkel Bapakk Udin",
            descBengkel = "Lorem ipsum dolor sit amet consectetur. Sed libero arcu cursus sapien. Ut magna commodo pharetra lobortis risus eget eget. Gravida sed purus praesent lacus.",
            pemilikBengkel = "Bapak Udin",
            alamatBengkel = "Lorem ipsum, dolor sit, amet consectetur.",
            jamBuka = "08.00 - 17.00",
        )
    }
}