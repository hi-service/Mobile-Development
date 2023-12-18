package com.hiservice.mobile.screen.statusorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hiservice.mobile.R
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.AlertDialogComponent
import com.hiservice.mobile.components.ButtonNormal
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.model.StatusOrderModel
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.GreyDark

@Composable
fun StatusOrderScreen(navigator : NavHostController,modifier : Modifier = Modifier){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: StatusOrderViewModel = viewModel(factory = viewModelFactory)
    val statusOrder by viewModel.orderStatus.collectAsState(StatusOrderModel(
        statusOrder = "Unknown",
        idOrder = "Unknown",
        time = "",
        image = R.drawable.statusorder_waiting,
        text = "",
        subText = "",
        isButton = false,
        buttonText = "",
        buttonColor = Color.Red,
        buttonClick = {}
    ))
    val count by viewModel.countRating
    val ratingInput by viewModel.isFinished
    val textRating by viewModel.textPenilaian
    val cancelOrder by viewModel.isCancel
    Column {
        TopHeadBar(text = "Status Order", isBack = true, onClick = {navigator.popBackStack()
        })
        Text(
            text =  "#${statusOrder.idOrder} - ${statusOrder.time}",
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = GreyDark,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(180.dp))
        Image(painter = painterResource(id = statusOrder.image), contentDescription = "Image Status")
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text =  statusOrder.text,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = DarkCyan,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text =  statusOrder.subText,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = GreyDark,
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),

            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            ButtonNormal(
                text = statusOrder.buttonText,
                onClick = {statusOrder.buttonClick.invoke() },
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = statusOrder.buttonColor,
                textColor = Color.White
            )
            ButtonNormal(
                text = "Chat Bengkel",
                onClick = {
                    navigator.navigate("service/chat-order")
                },
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }


    }
    if(cancelOrder){
        AlertDialogComponent(
            onDismissRequest = { viewModel.setCancel() },
            onConfirmation = { viewModel.setOrderStatusStatus("canceled")
                viewModel.setCancel()
                navigator.navigate("dashboard"){
                    popUpTo("service/status-order") { inclusive = true }
                }},
            dialogTitle = "Membatalkan pesanan",
            dialogText = "Apakah anda yakin untuk membatalkan pesanan?",
            icon = Icons.Outlined.Warning,
            isCancel = true
        )
    }

    if(ratingInput){
        AlertRating(count = count, counter = viewModel::setCountRating, onCommentChange = viewModel::setTextPenilaian, commentText = textRating, onDismiss = {
             viewModel.setFinish()
            viewModel.setOrderStatusStatus("finished")
            navigator.navigate("dashboard"){
                popUpTo("service/status-order") { inclusive = true }
            }
        }, onClickedSend = {
            viewModel.setOrderRating()
            navigator.navigate("dashboard"){
                popUpTo("service/status-order") { inclusive = true }
            }
        })
    }


}