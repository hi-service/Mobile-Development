package com.hiservice.mobile.screen.part_shop.keranjang_shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KeranjangViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<KeranjangState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<KeranjangState>>
        get() = _uiState

    fun getAddedOrderSparePart() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderSparePart()
                .collect { orderReward ->
                    val totalRequiredPoint =
                        orderReward.sumOf { it.partSell.harga * it.count }
                    _uiState.value = UiState.Success(KeranjangState(orderReward, totalRequiredPoint))
                }
        }
    }

    fun updateOrderReward(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(rewardId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderSparePart()
                    }
                }
        }
    }
}