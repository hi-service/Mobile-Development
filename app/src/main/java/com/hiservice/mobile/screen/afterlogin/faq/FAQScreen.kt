package com.hiservice.mobile.screen.afterlogin.faq

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.components.ItemExpandedList
import com.hiservice.mobile.components.SearchBarCustomFAQ
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.generateItemList
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun FAQScreen() {
    val itemList = generateItemList()
    val searchQuery = remember { mutableStateOf("") }
    Column {
        TopHeadBar(text = "Pertanyaan Umum", isBack = true, onClick = {})
        Column(Modifier.padding(horizontal = 32.dp)) {
            SearchBarCustomFAQ(
                hint = "Cari pertanyaan",
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                onClickSearch = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn (verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(bottom = 32.dp)){
                items(itemList.filter { item ->
                    item.title.contains(searchQuery.value, ignoreCase = true)
                }) { item ->
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