package com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.AlertDialogComponent
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.LoadingComponent
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.KeluhanFakeData
import com.hiservice.mobile.data.model.Keluhan
import com.hiservice.mobile.screen.afterlogin.services.first_page_detail.FirstPageViewModel
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp,
    color: Color = YellowGold
): Modifier {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0.5f
    val duration = if (state.isScrollInProgress) 0 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration), label = ""
    )

    return drawWithContent {
        drawContent()
        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f
        if (needDrawScrollbar && firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = (state.layoutInfo.visibleItemsInfo.size * elementHeight) - 20
            drawRoundRect(
                color = color,
                topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()), // Mengatur radius lengkung atas dan bawah
                alpha = alpha
            )
        }
    }
}
@Composable
fun DaftarKeluhan(modifier: Modifier = Modifier,navigator: NavHostController){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: KeluhanViewModel = viewModel(factory = viewModelFactory)
    val scrollState = rememberScrollState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val counter = remember{mutableStateOf(0)}
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
        }, isBack = true)
        Column (modifier = modifier
            .verticalScroll(scrollState)
            .padding(start = 26.dp, end = 26.dp, bottom = 26.dp)){
            Text(text = "Pilih maksimal 3 gejala pada motor", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn (state = lazyListState,modifier = modifier
                .height(400.dp)
                .simpleVerticalScrollbar(lazyListState)
                .border(1.dp, DarkCyan, RoundedCornerShape(15.dp))
            ){
                itemsIndexed(viewModel.itemsState) { index, keluhan ->
                    DaftarKeluhanContent(keluhan = keluhan, modifier = Modifier.fillMaxWidth(), index = index, viewModel = viewModel,counter = counter.value)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ButtonBig(text = "Lanjut") {
                navigator.navigate("service/daftar-bengkel")
            }
            LoadingComponent(showDialog = loading, onDismiss = {})

        }
    }
}

@Composable
fun DaftarKeluhanContent(
    keluhan: Keluhan,
    index  :Int,
    counter : Int,
    modifier: Modifier = Modifier,
    viewModel: KeluhanViewModel
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {
            viewModel.changeData(index)
        }
    ){
        Checkbox(
            checked = keluhan.isChecklist,
            onCheckedChange = { isChecked ->
                viewModel.changeData(index)},
            colors = CheckboxDefaults.colors(uncheckedColor = Color.Gray, checkedColor = YellowGold))// Jangan reaktif saat checkbox diklik
        Text(text = keluhan.namaKeluhan, modifier = Modifier.padding(start = 8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun DaftarKeluhanPreview(){
    HiServiceTheme {
        //DaftarKeluhan()
    }
}

@Preview(showBackground = true)
@Composable
fun DaftarKeluhanContentPreview(){
    HiServiceTheme {

    }
}