package com.hiservice.mobile.screen.no_connection

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.google.android.gms.maps.model.LatLng
import com.hiservice.mobile.R
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.LoadingComponent
import com.hiservice.mobile.util.Connection.Companion.isOnline
import java.nio.file.Files.size

@Composable
fun NoConnection(navToLogin : ()-> Unit){
    val context = LocalContext.current
    val loading = rememberSaveable { mutableStateOf(false) }
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column {
           Text(
                text = "No Connection",
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF263238),
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = R.drawable.no_connection).apply(block = {
                        size(Size.ORIGINAL)
                    }).build(), imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
            )


            ButtonBig("Reconnect", onClick = {
                loading.value = true
                if (isOnline(context)){
                    navToLogin()
                }else{

                }
                loading.value = false
            }, modifier = Modifier.padding(40.dp))
            LoadingComponent(showDialog = loading.value, onDismiss = {})
        }


    }
}