package com.hiservice.mobile.screen.afterlogin.riwayat.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.ItemHistory
import com.hiservice.mobile.components.SearchBarCustom
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.HistoryFakeData
import com.hiservice.mobile.data.model.HistoryModelWrapper
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.KeluhanViewModel
import com.hiservice.mobile.screen.statusorder.chatBubble
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.WhiteReal
import com.hiservice.mobile.ui.theme.YellowGold
import com.hiservice.mobile.util.formatDate

@Composable
fun RiwayatServiceContent(
    modifier: Modifier = Modifier,
    navigator: NavHostController
){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: RiwayatServiceViewModel = viewModel(factory = viewModelFactory)
    val dataHistory by viewModel.historyData.collectAsState()
    Column (
        modifier = modifier.fillMaxSize()
    ){
        TopHeadBar(text = "Riwayat Service", isBack = true, onClick = {navigator.popBackStack()})
        Column (
            modifier = modifier
                .padding(horizontal = 32.dp)
        ){
            SearchBarCustom(hint = "Cari Riwayat", onClickSearch = {})
            LazyColumn (
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.padding(top = 16.dp)
            ){
                items(items = dataHistory, key = {
                    it.orderId.toString()
                }) { item ->
                    ItemHistory(
                        orderID = item.orderId.toString(),
                        namaService = "Layanan",
                        tanggalService = item.waktu.toString(),
                        namaBengkel = item.namaBengkel!!,
                        descService = item.deskripsi!!,
                        kmService = item.kmSebelum.toString(),
                        kmNext = item.kmSesudah.toString(),
                        status = item.status.toString()
                    )
                }
            }
        }
    }
}

fun generateDummyData(): List<HistoryModelWrapper> {
    return HistoryFakeData.listHistory.map {
        HistoryModelWrapper(
            history =  it
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RiwayatServiceContentPreview(){
    val dummyData = generateDummyData()
    HiServiceTheme {

    }
}