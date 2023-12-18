package com.hiservice.mobile.screen.afterlogin.services.first_page_detail

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.location.Address
import android.location.Geocoder
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.ktx.utils.sphericalDistance
import com.hiservice.mobile.MainViewModel
import com.hiservice.mobile.R
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.InputTextCustom
import com.hiservice.mobile.components.LoadingComponent
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.components.inputTextLarge
import com.hiservice.mobile.data.model.SharedData
import com.hiservice.mobile.screen.afterlogin.dashboard.DashboardViewModel
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.GreyLight
import com.hiservice.mobile.util.MapsUtility
import com.hiservice.mobile.util.MapsUtility.Companion.hasLocationPermission
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Locale


@OptIn(UiToolingDataApi::class)
@Composable
fun FirstPageDetail(navigator : NavHostController,mainViewModel: MainViewModel){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: FirstPageViewModel = viewModel(factory = viewModelFactory)
    val loading by viewModel.loading
    LoadingComponent(showDialog = loading, onDismiss = {})
    Column {
        MapComponent(context  = current, viewModel = viewModel, mainViewModel = mainViewModel, navigator = navigator)
    }
}
fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorResourceId: Int): BitmapDescriptor? {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResourceId)
    val bitmap = vectorDrawable?.toBitmap()
    return bitmap?.let { BitmapDescriptorFactory.fromBitmap(it) }
}
@Composable
fun MapComponent(modifier: Modifier = Modifier, context: Context,viewModel: FirstPageViewModel,mainViewModel: MainViewModel,navigator: NavHostController) {
    TopHeadBar(text = "Detail Order", isBack = true, onClick = {
        navigator.popBackStack()
    })
    val location = remember { mutableStateOf(LatLng(-7.983908, 112.621391)) }
    val location_user_mark = remember { mutableStateOf(LatLng(-7.983908, 112.621391)) }

    val coroutineScope = rememberCoroutineScope()
    val currentLat = remember {
        mutableStateOf(0.0)
    }
    val currentLng = remember {
        mutableStateOf(0.0)

    }
    val itemMarker by viewModel.itemsStateFlow.collectAsState()
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                MapsUtility.getCurrentLocation(context) { lat, long ->
                    location.value = LatLng(lat, long)
                }
            }
        }
    )
    val deskripsi by viewModel.deskripsi
    val noHp by viewModel.noHp

    if (hasLocationPermission(context)) {
        MapsUtility.getCurrentLocation(context) { lat, long ->
            location.value = LatLng(lat, long)
            location_user_mark.value = LatLng(lat, long)
        }
    } else {
        LaunchedEffect("setPermission") {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location.value, 10f)
    }

    LaunchedEffect(location.value) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition(location.value, 15f, 0f, 0f)
            ),
            durationMs = 1000
        )
    }

    val listText = remember { mutableStateOf("....") }
    LaunchedEffect(cameraPositionState) {
        snapshotFlow { cameraPositionState.position.target }
            .debounce(200)
            .collect {
                coroutineScope.launch {
                    currentLat.value = it.latitude
                    currentLng.value = it.longitude
                    try {
                        val geocoder = Geocoder(context, Locale("id", "Indonesia"))
                        val locationList = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                        listText.value = locationList?.get(0)?.getAddressLine(0) ?: "Lokasi tidak ditemukan"
                    } catch (e: Exception) {
                        listText.value = "Tidak Ditemukan"
                    }
                }
                 }
    }
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { latLng ->
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(LatLng(latLng.latitude, latLng.longitude), 15f, 0f, 0f)
                            ),
                            durationMs = 1000
                        )
                        try {
                            val geocoder = Geocoder(context, Locale("id", "Indonesia"))
                            val locationList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                            listText.value = locationList?.get(0)?.getAddressLine(0) ?: "Lokasi tidak ditemukan"
                        } catch (e: Exception) {
                            listText.value = "Tidak Ditemukan"
                        }
                    }


                }
            ){
                itemMarker.forEach{
                    Marker(
                        state = MarkerState(position = LatLng(it.lat as Double, it.lng as Double)),
                        title = it.namaBengkel,
                        snippet = it.jenisBengkel
                    )
                }
                Marker(
                    state = MarkerState(position = location_user_mark.value),
                    title = "Posisi anda",
                    icon = bitmapDescriptorFromVector(context, R.drawable.baseline_person_pin_24)
                )
            }
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.maps_pin),
                    contentDescription = "marker",
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
                Text(text = listText.value)
            }

        }
            TextField(
                value = deskripsi,
                onValueChange = viewModel::deskripsiText,
                placeholder = {
                              Text(text = "Detail Permasalahan")
                },
                label = null, // Set label menjadi null untuk menghilangkannya
                modifier = modifier
                    .clip(RoundedCornerShape(40.dp))
                    .fillMaxWidth()
                    .padding(20.dp),
                minLines = 5,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GreyLight,
                    unfocusedContainerColor = GreyLight,
                    disabledContainerColor = GreyLight,
                    cursorColor = DarkCyan,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = GreyDark,
                    unfocusedLabelColor = GreyDark,
                )
            )
        InputTextCustom(hint = "No.Hp", text = noHp, onQueryChange = viewModel::noHpText, modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.weight(1f))
     ButtonBig(text = "Lanjut",modifier = Modifier.padding(20.dp)) {
        mainViewModel.setShareData(
            SharedData(deskripsiMasalah = deskripsi,noHp = noHp,currentLat.value,currentLng.value,listText.value)
        )
         navigator.navigate("service/keluhan")
     }
        Spacer(modifier = Modifier.height(20.dp))
    }
}



