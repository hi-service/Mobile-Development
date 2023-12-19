package com.hiservice.mobile.screen.statusorder

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.InputTextCustom
import com.hiservice.mobile.components.LoadingComponent
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan.simpleVerticalScrollbar
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.WhiteReal
import com.hiservice.mobile.ui.theme.YellowGold
import com.hiservice.mobile.util.formatDate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun ChatOrder(modifier: Modifier = Modifier, navigator: NavHostController){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: StatusOrderViewModel = viewModel(factory = viewModelFactory)
    val scrollState = rememberScrollState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val chatModel by viewModel.chatModel.collectAsState()

    val loading by viewModel.loading
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(index)
                }
            }
    }
    Column(modifier = modifier){
        TopHeadBar(text = "Daftar Keluhan", onClick = {
            navigator.popBackStack()
        }, isBack = true)

        Column (modifier = modifier
            .verticalScroll(scrollState)
            .padding(start = 26.dp, end = 26.dp, bottom = 26.dp)){
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn (state = lazyListState,modifier = modifier
                .height(400.dp)
                .width(400.dp)
                .simpleVerticalScrollbar(lazyListState)
                .border(1.dp, DarkCyan, RoundedCornerShape(15.dp))
            ){
                items(items = chatModel, key = {
                    it.id_chat!!
                }) { item ->



                        if(item.sender=="0"){
                            chatBubble(item.message!!,formatDate(item.waktu!!), TextAlign.Start,Arrangement.Start,Alignment.Start, DarkCyan,
                                WhiteReal,paddingEnd = 70.dp)
                        }else{
                            chatBubble(item.message!!,formatDate(item.waktu!!), TextAlign.End,Arrangement.End,Alignment.End, YellowGold,
                                DarkCyan, paddingStart = 70.dp)
                    }




                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row (horizontalArrangement = Arrangement.SpaceBetween){
                InputTextCustom(hint = "Hello", text = "Hello", onQueryChange = {})
                Button(onClick = {}) {
                    Text(text = "Kirim")
                }
            }
            LoadingComponent(showDialog = loading, onDismiss = {})

        }
        LaunchedEffect(chatModel) {
            // Ensure chatModel is not empty before scrolling
            if (chatModel.isNotEmpty()) {
                // Scroll to the last item whenever chatModel is updated
                lazyListState.scrollToItem(chatModel.size - 1)
            }
        }
    }
}
@Composable
fun chatBubble(text:String, tanggal:String,textAlign: TextAlign, arrangement : Arrangement.Horizontal, alignment: Alignment.Horizontal, color: Color = YellowGold,textColor: Color,paddingStart : Dp = 0.dp,paddingEnd : Dp = 0.dp){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .padding(end = paddingEnd, start = paddingStart), horizontalArrangement = arrangement) {
        Column(horizontalAlignment = alignment) {
            Text(text = tanggal, textAlign = textAlign, modifier = Modifier.padding(end = 5.dp, bottom = 5.dp))
            Box(
                Modifier
                    .background(color = color, RoundedCornerShape(15.dp))
                    .padding(10.dp)
                    .defaultMinSize(minWidth = 70.dp)
            ) {
                    Text(text = text, color = textColor,textAlign = TextAlign.Justify)
            }
        }

    }
}
@Composable
@Preview(showBackground = true)
fun PartSellIntemPreview(){
    HiServiceTheme {
        Column {
            chatBubble("The festive season is fast approaching, and what better way to enjoy the holidays than with a double Reward Booster when you join ELITE.","12-18 19:00", TextAlign.Start,Arrangement.Start,Alignment.Start, DarkCyan,
                WhiteReal,paddingEnd = 70.dp)
            chatBubble("Join ELITE, and for a limited time (Dec 25), you'll earn double the standard rewards. It's our way of saying thank you with a festive bonus to make your holidays even more joyful.","12-18 19:00", TextAlign.End,Arrangement.End,Alignment.End, YellowGold,
                DarkCyan, paddingStart = 70.dp)
        }

    }
}