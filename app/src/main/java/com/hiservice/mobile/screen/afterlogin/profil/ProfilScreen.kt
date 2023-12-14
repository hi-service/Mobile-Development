package com.hiservice.mobile.screen.afterlogin.profil

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.InputTextCustom
import com.hiservice.mobile.components.InputTextNoBG
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.screen.afterlogin.dashboard.CarouselCard
import com.hiservice.mobile.screen.authentication.login.LoginViewModel
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.WhiteReal
import com.hiservice.mobile.ui.theme.YellowGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilScreen(
    linkPhotoUser: String,
    nama: String,
    nomorHp: String,
    modifier: Modifier = Modifier
){

    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: ProfilViewModel = viewModel(factory = viewModelFactory)
    val email by viewModel.email

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Column{
        TopHeadBar(text = "Profil", onClick = {}, isBack = true)

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
        ){
            Box(modifier = modifier, Alignment.BottomEnd){
                AsyncImage(
                    model = linkPhotoUser,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(132.dp)
                        .width(132.dp)
                        .clip(CircleShape),
                    contentDescription = null
                )
                Column(modifier = modifier
                    .clip(CircleShape)
                    .background(color = YellowGold)
                    .size(height = 40.dp, width = 40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        imageVector =Icons.Default.Edit,
                        contentDescription = "Button Edit Foto Profil"
                    )
                }
            }

            Spacer(modifier = modifier.height(40.dp))

            Column (modifier.fillMaxWidth()){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(
                        imageVector =Icons.Default.Email,
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Column {
                        Text(text = "E-mail")
                        Text(text = email)
                    }
                }
                Divider(color = GreyDark, modifier = modifier.padding(vertical = 20.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(
                        imageVector =Icons.Default.Person,
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Column {
                        Text(text = "Nama Lengkap")
                        Text(text = nama)
                    }
                    Box(modifier.fillMaxWidth(), Alignment.CenterEnd){
                        IconButton(onClick = { isSheetOpen = true }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit Nama", tint = YellowGold)
                        }
                    }
                }
                Divider(color = GreyDark, modifier = modifier.padding(vertical = 20.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(
                        imageVector =Icons.Default.Call,
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Column {
                        Text(text = "No Handphone")
                        Text(text = nomorHp)
                    }
                    Box(modifier.fillMaxWidth(), Alignment.CenterEnd){
                        IconButton(
                            onClick = { isSheetOpen = true }
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit No HP", tint = YellowGold)
                        }
                    }
                }
            }
        }

        if (isSheetOpen){
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { isSheetOpen = false },
                dragHandle = null,
                containerColor = WhiteReal
            ) {
                Column (modifier = modifier.padding(32.dp).fillMaxWidth().height(120.dp)) {
                    InputTextNoBG(
                        hint = "Rizki Fauzi",
                        text = "",
                        onQueryChange = { }
                    )
                    Row(horizontalArrangement = Arrangement.End, modifier = modifier.fillMaxWidth()) {
                        TextButton(onClick = { isSheetOpen = false }, Modifier.padding(12.dp)) {
                            Text(text = "Batal", color = YellowGold)
                        }
                        TextButton(onClick = { isSheetOpen = false }, Modifier.padding(12.dp)) {
                            Text(text = "Simpan", color = YellowGold)
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilScreenPreview(){
    HiServiceTheme {
        ProfilScreen(
            linkPhotoUser = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Keanu_Reeves_%28crop_and_levels%29_%28cropped%29.jpg/330px-Keanu_Reeves_%28crop_and_levels%29_%28cropped%29.jpg",
            nama = "Ajat Karburator",
            nomorHp = "+6289700059872"
        )
    }
}