package com.hiservice.mobile.screen.part_shop.explore_shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.components.PartSellItem
import com.hiservice.mobile.components.SearchBarCustom
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.components.TopHeadBarPartShop
import com.hiservice.mobile.data.fake_data.PartShopFakeData
import com.hiservice.mobile.data.model.PartShopModelWrapper
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun ExploreShopContent(
    partSel: List<PartShopModelWrapper>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
){
    Column (modifier = modifier){
        TopHeadBar(text = "Explore", isBack = true)

        Column (modifier = modifier.padding(horizontal = 32.dp)){
            SearchBarCustom(hint = "Cari Sparepart", onClickSearch = {})
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ){
                items(partSel) { data ->
                    PartSellItem(
                        image = data.partSell.image,
                        title = data.partSell.title,
                        harga = data.partSell.harga,
                        modifier = Modifier.clickable {
                            navigateToDetail(data.partSell.id)
                        }
                    )
                }
            }
        }
    }
}

fun generateDummyData(): List<PartShopModelWrapper> {
    return PartShopFakeData.dummyPartSell.map {
        PartShopModelWrapper(
            partSell = it,
            count = 1
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ExploreShopContentPreview(){
    val dummyData = generateDummyData()
    HiServiceTheme {
        ExploreShopContent(
            partSel = dummyData,
            navigateToDetail = {}
        )
    }
}