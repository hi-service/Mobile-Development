package com.hiservice.mobile.screen.statusorder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hiservice.mobile.components.ButtonNormal
import com.hiservice.mobile.components.inputTextLarge
import com.hiservice.mobile.ui.theme.YellowGold

@Composable
fun AlertRating(count : Int,counter : (Int) -> Unit,commentText : String,onCommentChange : (String) -> Unit,onDismiss: () -> Unit,onClickedSend: () -> Unit){
    val Star = remember {
        mutableStateOf((listOf(1,2,3,4,5)))
    }
    Box(
        Modifier
            .background(Color.Black.copy(0.2f))
            .fillMaxSize(), contentAlignment = Alignment.Center){
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .width(600.dp)
                .height(400.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color = Color.White), contentAlignment = Alignment.Center){
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Berikan rating anda !")
                LazyRow(
                    content = {
                        items(Star.value.indices.count()) { index ->
                            if(index+1 > count){
                                Icon(
                                    Icons.Outlined.Star,
                                    contentDescription = "Rating",
                                    tint = Color.Gray,
                                    modifier = Modifier.clickable {
                                        counter(index)
                                    }
                                )
                            }else{
                                Icon(
                                    Icons.Outlined.Star,
                                    contentDescription = "Rating",
                                    tint = YellowGold,
                                    modifier = Modifier.clickable {
                                        counter(index)
                                    }
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))
                inputTextLarge(text = commentText,onQueryChange = onCommentChange, hint = "Berikan penilaian anda")
                Box(modifier = Modifier.padding(20.dp)){
                    ButtonNormal(text = "Kirim", onClick = {
                        onClickedSend()
                    })
                }
                Text(text = "Lewati", modifier = Modifier.clickable{
                    onDismiss()
                })


            }
        }
    }
    }
}
