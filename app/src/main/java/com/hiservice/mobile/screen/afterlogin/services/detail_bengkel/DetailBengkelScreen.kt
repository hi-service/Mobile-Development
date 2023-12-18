package com.hiservice.mobile.screen.afterlogin.services.detail_bengkel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hiservice.mobile.MainViewModel
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.LoadingComponent
import com.hiservice.mobile.components.ReviewerCard
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.BengkelFakeData
import com.hiservice.mobile.data.model.BengkelModel
import com.hiservice.mobile.data.retrofit.gson.DeskripsiBengkelItem
import com.hiservice.mobile.screen.afterlogin.services.first_page_detail.FirstPageViewModel
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold
import com.hiservice.mobile.ui.theme.myFont
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun DetailBengkelScreen(
    modifier: Modifier = Modifier,
    id : Int,
    mainViewModel: MainViewModel,
    navigator: NavHostController,
){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: DetailBengkelViewModel = viewModel(factory = viewModelFactory)
    val dataBengkel by viewModel.dataBengkel
    val scrollState = rememberScrollState()
    val dataBengkelItem by viewModel.dataBengkelItem.collectAsState()
    val loading by viewModel.loading
    var iconColor = YellowGold
    var confirmDialog by rememberSaveable {
        mutableStateOf(false)
    }

        if(dataBengkel != null){
            Column {
                TopHeadBar(text = "Order Confirm", onClick = {navigator.popBackStack()
                }, isBack = true)
                Column(modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)) {

                    Column (modifier.padding(horizontal = 32.dp)){
                        AsyncImage(
                            model = dataBengkelItem[0].urlGambar,
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
                            Text(text = dataBengkelItem[0].namaBengkel!!, fontWeight = FontWeight.Medium)
                        }
                        Row {
                            Text(text = "Rating : ")
                            Text(text = dataBengkel?.rating.toString(), fontWeight = FontWeight.Medium)
                            LazyRow(
                                content = {
                                    items(5) { index ->
                                        if(index < dataBengkel!!.rating as Double){
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
                        Text(text = dataBengkelItem[0].deskripsiBengkel!!)
                        Row {
                            Text(text = "Pemilik : ")
                            Text(text = dataBengkelItem[0].pemilikBengkel!!, fontWeight = FontWeight.Medium)
                        }
                        Row {
                            Text(text = "Alamat : ")
                            Text(text = dataBengkelItem[0].alamatBengkel!!, fontWeight = FontWeight.Medium)
                        }
                        Row {
                            Text(text = "Jam Buka : ")
                            Text(text ="${dataBengkelItem[0].jamBuka!!} -  ${dataBengkelItem[0].jamTutup!!}", fontWeight = FontWeight.Medium)
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
                            confirmDialog = true
                            //viewModel.setOrder(id,mainViewModel.sharedData.value!!.copy())
                        }
                        Spacer(modifier = modifier.height(24.dp))

                    }
                }
            }
            if (confirmDialog) {
                AlertDialog(
                    title = {
                        androidx.compose.material.Text(
                            text = "Detail Order",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            modifier = modifier.fillMaxWidth()
                        )
                    },
                    text = {
                        androidx.compose.material.Text(text = "Default")
                    },
                    onDismissRequest = {},
                    confirmButton = {
                        Button(
                            onClick = {confirmDialog = false},
                            modifier = modifier
                                .height(54.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .fillMaxWidth()
                                .background(color = YellowGold),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Text(
                                text = "Lanjut Pilih Bengkel",
                                style = TextStyle(
                                    fontFamily = myFont,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier.align(Alignment.CenterVertically),
                                color = DarkCyan,
                            )
                        }
                    },
                    dismissButton = {}
                )
            }
            LoadingComponent(showDialog = loading, onDismiss = {})
        }else{
            viewModel.setDataBengkel(id)
        }
    }

@Preview(showBackground = true)
@Composable
fun DetailBengkelPreview() {
    val context = LocalContext.current
    DetailBengkelScreen(id = 1, mainViewModel = MainViewModel(), navigator = NavHostController(context))
}