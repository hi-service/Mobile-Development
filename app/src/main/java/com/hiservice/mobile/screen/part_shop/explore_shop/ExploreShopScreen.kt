package com.hiservice.mobile.screen.part_shop.explore_shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.PartSellItem
import com.hiservice.mobile.components.SearchBarCustom
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.PartShopFakeData
import com.hiservice.mobile.data.model.PartShopModelWrapper
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun ExploreShopContent(
    id : Int,
    modifier: Modifier = Modifier,
) {
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: ExploreShopViewModel = viewModel(factory = viewModelFactory)
    val partItem by viewModel.dataBengkelItem.collectAsState()
    if (partItem.isEmpty()) {
        viewModel.setDataBengkel(id)
    } else {
        Column(modifier = modifier) {
            TopHeadBar(text = "Explore", isBack = true)

            Column(modifier = modifier.padding(horizontal = 32.dp)) {
                SearchBarCustom(hint = "Cari Sparepart", onClickSearch = {})
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = modifier
                ) {
                    items(partItem) { data ->
                        PartSellItem(
                            image = data.image.toString(),
                            title = data.namaBarang!!,
                            harga = data.price.toString(),
                            context = current,
                            phoneNumber = ""
                        )
                    }
                }
            }
        }
    }

}

