package com.hiservice.mobile.data

import com.hiservice.mobile.data.localstorage.UserPref
import com.hiservice.mobile.data.model.PartShopModelWrapper
import com.hiservice.mobile.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository private constructor(
    private val preference: UserPref
) {

    private val orderSparePart = mutableListOf<PartShopModelWrapper>()

    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    suspend fun logout() {
        preference.logout()
    }

    fun getAllSparePart(): Flow<List<PartShopModelWrapper>> {
        return flowOf(orderSparePart)
    }

    fun updateOrderReward(partId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderSparePart.indexOfFirst { it.partSell.id == partId }
        val result = if (index >= 0) {
            val orderPart = orderSparePart[index]
            orderSparePart[index] =
                orderPart.copy(partSell = orderPart.partSell, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderSparePart(): Flow<List<PartShopModelWrapper>> {
        return getAllSparePart()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            preference: UserPref,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(preference)
            }
    }
}