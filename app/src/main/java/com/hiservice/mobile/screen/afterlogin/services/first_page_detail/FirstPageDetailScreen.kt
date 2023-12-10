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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.hiservice.mobile.util.MapsUtility
import com.hiservice.mobile.util.MapsUtility.Companion.hasLocationPermission
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Locale


@OptIn(UiToolingDataApi::class)
@Composable
fun FirstPageDetail(){
    val context = LocalContext.current
    Column {
        MapComponent(context  = context)
    }
}

@Composable
fun MapComponent(modifier: Modifier = Modifier, context: Context) {
    val location = remember { mutableStateOf(LatLng(-6.200000, 106.816666)) }
    val coroutineScope = rememberCoroutineScope()
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

    if (hasLocationPermission(context)) {
        MapsUtility.getCurrentLocation(context) { lat, long ->
            location.value = LatLng(lat, long)
            Log.d("Loca",location.value.toString())
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

    val listText = remember { mutableStateOf("Ricky") }
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(all = 20.dp)
                .clip(RoundedCornerShape(15.dp))
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
            )
        }

        Text(text = listText.value)
    }
}



