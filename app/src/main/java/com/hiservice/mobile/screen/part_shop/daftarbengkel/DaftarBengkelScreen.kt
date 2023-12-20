package com.hiservice.mobile.screen.part_shop.daftarbengkel

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
import com.hiservice.mobile.components.CardDaftarBengkelShop
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.retrofit.gson.DataBengkelShopItem
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.simpleVerticalScrollbar
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.YellowGold
import com.services.finalsubmissionjetpackcompose.ui.navigation.Screen
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun DaftarBengkelShop(navigator: NavHostController,mainViewModel: MainViewModel) {
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: DaftarBengkelShopViewModel = viewModel(factory = viewModelFactory)
    val listBengkelTerdekat by viewModel.bengkelTerdekat.collectAsState()
    val lazyListStateFake = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    viewModel.initViewModel(mainViewModel)
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopHeadBar(text = "Pilih Bengkel", isBack = true, onClick = {navigator.popBackStack()})
        Spacer(modifier = Modifier.height(10.dp))

        LaunchedEffect(lazyListStateFake) {
            snapshotFlow { lazyListStateFake.firstVisibleItemIndex }
                .distinctUntilChanged()
                .collect { index ->
                    coroutineScope.launch {
                        lazyListStateFake.animateScrollToItem(index)
                    }
                }
        }
        Column(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp, bottom = 26.dp)
        ) {
            DaftarBengkelShop(lazyListStateFake,listBengkelTerdekat,navigator)
        }
    }
}

@Composable
fun DaftarBengkelShop( lazyListState: LazyListState, List : List<DataBengkelShopItem>,navigator: NavHostController){
    LazyColumn (state = lazyListState,modifier = Modifier
        .fillMaxHeight(1f)
        .simpleVerticalScrollbar(lazyListState)
    ){
        itemsIndexed(List) { index, list ->
            CardDaftarBengkelShop(
                daftarBengkel = list,
                ){
                navigator.navigate(Screen.Shop_Data_Item.createRoute(list.id!!.toInt()))
            }
            Divider()
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
