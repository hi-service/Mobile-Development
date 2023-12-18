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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hiservice.mobile.MainViewModel
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.CardDaftarBengkel
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.simpleVerticalScrollbar
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.YellowGold
import com.services.finalsubmissionjetpackcompose.ui.navigation.Screen
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun DaftarBengkel(navigator: NavHostController,mainViewModel: MainViewModel) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: DaftarBengkelViewModel = viewModel(factory = viewModelFactory)
    val listBengkelRekomendasi by viewModel.bengkelRekomendasi.collectAsState()
    val listBengkelTerdekat by viewModel.bengkelTerdekat.collectAsState()
    viewModel.initViewModel(mainViewModel)
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopHeadBar(text = "Pilih Bengkel", isBack = true, onClick = {navigator.popBackStack()})
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
        val fakeDataList = if (selectedTabIndex == 0) listBengkelRekomendasi else listBengkelTerdekat
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
            DaftarBengkelList(fakeDataState,fakeDataList,navigator)
        }
    }
}

@Composable
fun DaftarBengkelList( lazyListState: LazyListState, List : List<DataListBengkel>,navigator: NavHostController){
    LazyColumn (state = lazyListState,modifier = Modifier
        .fillMaxHeight(1f)
        .simpleVerticalScrollbar(lazyListState)
    ){
        itemsIndexed(List) { index, list ->
            CardDaftarBengkel(
                daftarBengkel = list,
                ){
                navigator.navigate(Screen.Service_Konfirmasi_Order.createRoute(list.id as Int))
            }
            Divider()
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
