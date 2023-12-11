package com.hiservice.mobile.screen.afterlogin.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hiservice.mobile.R
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import kotlinx.coroutines.launch

data class DrawerItem(
    val icon: Int,
    val label: String,
    val secondaryLabel: String
)
@Composable
fun DashboardScreen(navigator: NavHostController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        DrawerItem(icon = R.drawable.person_icon, label = "Account", secondaryLabel = ""),
        DrawerItem(icon = R.drawable.info, label = "About", secondaryLabel = ""),
        DrawerItem(icon = 0, label = "", secondaryLabel = ""),
        DrawerItem(icon = R.drawable.settings, label = "Settings", secondaryLabel = ""),
        DrawerItem(icon = R.drawable.logout, label = "Logout", secondaryLabel = ""),
        DrawerItem(icon = 1, label = "", secondaryLabel = ""),
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .padding(start = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Box(
                            contentAlignment= Alignment.Center,
                            modifier = Modifier
                                .size(60.dp)
                                .border(
                                    width = 2.dp,
                                    color = DarkCyan,
                                    shape = CircleShape
                                ),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.person),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(58.dp)
                                    .clip(CircleShape)
                                    .clickable { },
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Ricky Triyoga Wardhana",
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            fontSize = 16.sp,
                        )
                        Text(
                            text = "Customer",
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 14.sp,
                            color = Color(0xFFA6A6A6)
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = Color(0xFFA6A6A6), thickness = 0.5.dp, modifier = Modifier.padding(end = 30.dp, start = 10.dp))
                    }

                }
                Column(modifier = Modifier.fillMaxSize()) {
                    items.forEach { item ->
                        if(item.icon == 0){
                            Spacer(modifier = Modifier.weight(1f))
                            Divider(color = Color(0xFFA6A6A6), thickness = 0.5.dp, modifier = Modifier.padding(end = 30.dp, start = 10.dp).padding(bottom = 10.dp))
                        }else{
                            NavigationDrawerItem(
                                label = { Text(text = item.label) },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                },
                                icon = {
                                    if(item.icon!=1){
                                        Image(
                                            painterResource(item.icon),
                                            contentDescription = "",
                                            contentScale = ContentScale.Inside,
                                            modifier = Modifier
                                                .height(30.dp)
                                                .width(30.dp)
                                        )
                                    }else{

                                    }
                                     },
                                badge = {},
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                                    .height(50.dp)
                            )
                        }
                        
                    }
                }

            }
        },
        content = {

            DashboardContent(
                openNavDrawer = { scope.launch { drawerState.open() } },
                navigator = navigator
            )
        }
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardContent(modifier : Modifier = Modifier,navigator: NavHostController,openNavDrawer: () -> Unit){
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        TopHeadBar(text = "Dashboard", onClick = {
            openNavDrawer()
        })
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),horizontalArrangement = Arrangement.SpaceEvenly) {
            BoxMenuComponent(image = R.drawable.service_menu, text = "Mulai Layanan")
            BoxMenuComponent(image = R.drawable.shop_menu, text = "Part Shop")
        }
        Spacer(modifier = modifier.height(16.dp))
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),horizontalArrangement = Arrangement.SpaceEvenly) {
            BoxMenuComponent(image = R.drawable.reminder_menu, text = "Riwayat Service", onClick =
            {
                    coroutineScope.launch {
                        navigator.navigate("login")
                }
            })
            BoxMenuComponent(image = R.drawable.consult_menu, text = "E - Consult")
        }
        Spacer(modifier = modifier.height(24.dp))
        Text(text = "Artikel Terkait", fontWeight = FontWeight.SemiBold, fontSize = 22.sp, modifier = Modifier.padding(start = 32.dp, end = 32.dp))
        Spacer(modifier = modifier.height(6.dp))
        CarouselCard()
    }
}

@Preview(showBackground = true)
@Composable
fun GetPrev() {
    HiServiceTheme {

    }
}