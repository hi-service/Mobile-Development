package com.hiservice.mobile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.hiservice.mobile.data.fake_data.BengkelFakeData
import com.hiservice.mobile.data.model.BengkelModel
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun ReviewerCard(
    daftarBengkel: BengkelModel,
    linkPhotoReviewer: String,
    namaReviewer:String,
    isiReview: String,
    modifier: Modifier = Modifier
){
    var iconColor = YellowGold
    Row{
        AsyncImage(
            model = linkPhotoReviewer,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(50.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(15))
        )
        Spacer(modifier = modifier.width(8.dp))
        Column {
            Row {
                Text(
                    text = namaReviewer,
                    fontWeight = FontWeight.Bold,
                )
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
                                tint = iconColor,
                                modifier = Modifier.size(height = 10.dp, width = 10.dp)
                            )
                        }
                    }
                )
            }
            Text(text = isiReview, color = GreyDark)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewerCardPreview() {
    HiServiceTheme {
        ReviewerCard(
            daftarBengkel = BengkelFakeData.listBengkel[0],
            linkPhotoReviewer = "https://unsplash.com/photos/brown-wooden-table-with-chairs-ngLt4Y1vI_Q",
            namaReviewer = "Bengkel Bapak Udin",
            isiReview = "Sangatlah mantap"
        )
    }
}