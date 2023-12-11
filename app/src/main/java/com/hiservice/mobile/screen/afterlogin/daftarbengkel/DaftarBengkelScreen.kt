package com.hiservice.mobile.screen.afterlogin.daftarbengkel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.components.CardDaftarBengkel
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.BengkelFakeData.listBengkel
import com.hiservice.mobile.data.fake_data.BengkelFakeData.listBengkel2
import com.hiservice.mobile.data.model.BengkelModel
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.simpleVerticalScrollbar
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.YellowGold
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun DaftarBengkel() {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopHeadBar(text = "Pilih Bengkel", isBack = true)
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center) {
                Spacer(
                    modifier = Modifier
                        .background(GreyDark)
                        .width(100.dp)
                        .fillMaxHeight()
                )
                Row {
                    Box(
                        Modifier
                            .width(150.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (selectedTabIndex == 0) YellowGold else GreyDark)
                            .clickable { selectedTabIndex = 0 },
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(text = "Rekomendasi")
                        })
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (selectedTabIndex == 1) YellowGold else GreyDark)
                            .clickable { selectedTabIndex = 1 }, content = {
                            Text(text = "Jarak Terdekat")
                        })
                }

            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        val lazyListStateFake1 = rememberLazyListState()
        val lazyListStateFake2 = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        val fakeDataList = if (selectedTabIndex == 0) listBengkel else listBengkel2
        val fakeDataState = if (selectedTabIndex == 0) lazyListStateFake1 else lazyListStateFake2
        LaunchedEffect(fakeDataState) {
            snapshotFlow { fakeDataState.firstVisibleItemIndex }
                .distinctUntilChanged()
                .collect { index ->
                    coroutineScope.launch {
                        fakeDataState.animateScrollToItem(index)
                    }
                }
        }
        Column(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp, bottom = 26.dp)
        ) {
            DaftarBengkelList(fakeDataState,fakeDataList)
        }
    }
}

@Composable
fun DaftarBengkelList( lazyListState: LazyListState, List : List<BengkelModel>){
    LazyColumn (state = lazyListState,modifier = Modifier
        .fillMaxHeight(1f)
        .simpleVerticalScrollbar(lazyListState)
    ){
        itemsIndexed(List) { index, list ->
            CardDaftarBengkel(
                daftarBengkel = list,
                linkPhotoBengkel = "https://media.istockphoto.com/id/1347150429/id/foto/mekanik-profesional-bekerja-pada-mesin-mobil-di-garasi.jpg?s=612x612&w=0&k=20&c=Uw7QwBTEc98rrQPg6j5lmWRe-HHmf7vbiKlZ0WphJHM=",
                namaBengkel = "Bengkel Bapak Udin",
                rateNumber = 4.7,
                descBengkel = "Tenpat service motor dan ganti ban"
            )
            Divider()
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
