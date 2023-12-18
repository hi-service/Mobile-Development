package com.hiservice.mobile.screen.part_shop.detail_sparepart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hiservice.mobile.components.ButtonNormal
import com.hiservice.mobile.components.ButtonNormalOutlined
import com.hiservice.mobile.components.ProductCounter
import com.hiservice.mobile.components.TopHeadBarPartShop
import com.hiservice.mobile.ui.theme.GreyLight
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun DetailSparePartScreen(){

}

@Composable
fun DetailSparePartContent(
    modifier: Modifier = Modifier,
    linkPhotoProduk: String,
    title: String,
    namaToko: String,
    kategori: String,
    harga: Int,
    count: Int,
    stok: Int,
    merk: String,
    descProduct: String,
    onAddToCart: (count: Int) -> Unit
){
    var totalHarga by rememberSaveable { mutableIntStateOf(0) }
    var orderCount by rememberSaveable { mutableIntStateOf(count) }
    Column(modifier = modifier.fillMaxSize()) {
        TopHeadBarPartShop(text = "Detail Produk", onClickBack = {}, onClickCart = {})
        Column (
            modifier
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Box(modifier = modifier, Alignment.BottomStart){
                    AsyncImage(
                        model = linkPhotoProduk,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(15.dp))
                    )
                    Row(
                        modifier = modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(text = "Stok : " )
                        Text(text = stok.toString())
                    }
                }
                Spacer(modifier = modifier.height(12.dp))
                Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                Column (
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Row {
                        Text(text = "Toko : ")
                        Text(text = namaToko, fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Kategori : ")
                        Text(text = kategori, fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Merk : ")
                        Text(text = merk, fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(modifier = modifier.height(12.dp))
                    Text(text = "Deskripsi Produk :", fontWeight = FontWeight.SemiBold)
                    Text(
                        text = descProduct,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier.verticalScroll(rememberScrollState()),
                    )
                }
            }

            Spacer(modifier = modifier.height(12.dp))

            Column {
                Row (
                    modifier = modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(color = GreyLight, RoundedCornerShape(15.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row (
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        totalHarga = harga * orderCount
                        val formattedAmount = String.format("Rp%,d", totalHarga)
                        Text(text = formattedAmount, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
                        ProductCounter(
                            1,
                            orderCount,
                            onProductIncreased = { orderCount++ },
                            onProductDecreased = { if (orderCount > 0) orderCount-- }
                        )
                    }
                }
                Spacer(modifier = modifier.height(12.dp))
                Row (
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ){
                    ButtonNormalOutlined(
                        text = "Keranjang",
                        onClick = { },
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    ButtonNormal(
                        text = "Beli",
                        onClick = { },
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailSparePartContentPreview() {
    HiServiceTheme {
        DetailSparePartContent(
            linkPhotoProduk = "https://www.trijayapart.com/wp-content/uploads/2021/03/Gear-Sepeda-Motor.png",
            title = "SSS 43T-520 Gear Belakang Motor for Honda Tiger",
            namaToko = "Bengkel Bapak Ujang",
            kategori = "Suku Cadang Motor",
            harga = 379000,
            count = 1,
            stok = 255,
            merk = "Raja Motor",
            descProduct = "Berfungsi sebagai media penyalur putaran mesin ke roda bagian belakang. Berfungsi sebagai media penyalur putaran mesin ke roda bagian belakang. ",
            onAddToCart = {}
        )
    }
}