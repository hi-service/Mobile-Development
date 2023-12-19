package com.hiservice.mobile.screen.afterlogin.faq

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.components.ItemExpandedList
import com.hiservice.mobile.components.SearchBarCustom
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.generateItemList
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun FAQScreen(){
    val itemList = generateItemList()
    Column {
        TopHeadBar(text = "Pertanyaan Umum", isBack = true, onClick = {})
        Column(Modifier.padding(horizontal = 32.dp)) {
            SearchBarCustom(hint = "Cari pertanyaan", onClickSearch = {})
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn (verticalArrangement = Arrangement.spacedBy(12.dp)){
                items(itemList) { item ->
                    ItemExpandedList(item)
                }
            }
        }
    }
}

@Composable
@Preview
fun FAQScreenPreview(){
    HiServiceTheme {
        FAQScreen()
    }
}