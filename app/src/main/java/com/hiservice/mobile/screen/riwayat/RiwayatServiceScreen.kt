package com.hiservice.mobile.screen.riwayat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.components.ItemHistory
import com.hiservice.mobile.components.PartSellItem
import com.hiservice.mobile.components.SearchBarCustom
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.components.TopHeadBarPartShop
import com.hiservice.mobile.data.fake_data.HistoryFakeData
import com.hiservice.mobile.data.fake_data.PartShopFakeData
import com.hiservice.mobile.data.model.HistoryModelWrapper
import com.hiservice.mobile.data.model.PartShopModelWrapper
import com.hiservice.mobile.screen.part_shop.explore_shop.ExploreShopContent
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun RiwayatServiceContent(
    history: List<HistoryModelWrapper>,
    modifier: Modifier = Modifier,
    navigateToDetailService: (Long) -> Unit
){
    Column (
        modifier = modifier.fillMaxSize()
    ){
        TopHeadBar(text = "Riwayat Service", isBack = true, onClick = {})
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
                items(history) { data ->
                    ItemHistory(
                        namaService = data.history.namaService,
                        tanggalService = data.history.tanggalService,
                        namaBengkel = data.history.namaBengkel,
                        modifier = Modifier.clickable {
                            navigateToDetailService(data.history.id)
                        }
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
        RiwayatServiceContent(
            history = dummyData,
            navigateToDetailService = {}
        )
    }
}