package com.hiservice.mobile.screen.part_shop.keranjang_shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.ItemKeranjang
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.injection.Injection
import com.hiservice.mobile.ui.state.UiState

@Composable
fun KeranjangScreen(
    viewModel: KeranjangViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderSparePart()
            }
            is UiState.Success -> {
                KeranjangContent(
                    uiState.data,
                    onProductCountChanged = { rewardId, count ->
                        viewModel.updateOrderReward(rewardId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}
@Composable
fun KeranjangContent(
    state: KeranjangState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopHeadBar(text = "Keranjang", onClick = {}, isBack = true)
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            items(state.orderSparePart, key = { it.partSell.id }) { item ->
                ItemKeranjang(
                    partId = item.partSell.id,
                    image = item.partSell.image,
                    title = item.partSell.title,
                    totalHarga = item.partSell.harga * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChanged,
                )
            }
        }
    }
}
