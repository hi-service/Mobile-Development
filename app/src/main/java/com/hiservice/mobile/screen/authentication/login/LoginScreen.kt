package com.hiservice.mobile.screen.authentication.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.AlertDialogComponent
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.EmailInputText
import com.hiservice.mobile.components.LoadingComponent
import com.hiservice.mobile.components.PasswordInputText
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.localstorage.UserPref
import com.hiservice.mobile.data.localstorage.dataStore
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
){

}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    navToRegister: () -> Unit
){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: LoginViewModel = viewModel(factory = viewModelFactory)
    val scrollState = rememberScrollState()
    val emailText by viewModel.email
    val passwordText by viewModel.password
    val loading by viewModel.loading
    val alert by viewModel.alert
    val alertData by viewModel.alertData
    Column (
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(start = 32.dp, end = 32.dp, top = 44.dp, bottom = 44.dp)
    ){
        IconButton(
            onClick = { /*TODO*/ },
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                modifier = modifier
                    .size(32.dp),
            )
        }
        Spacer(modifier = modifier.height(36.dp))
        Text(
            text = "Hi user, let's sign you in.",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 26.sp * 1.24f
        )
        Spacer(modifier = modifier.height(14.dp))
        Text(
            text = "I am very happy to see you again. let’s sign in and enjoy our services",
            lineHeight = 16.sp * 1.24f,
            color = GreyDark
        )

        Spacer(modifier = modifier.height(36.dp))

        EmailInputText(text = emailText, onQueryChange = viewModel::emailText)
        Spacer(modifier = modifier.height(16.dp))
        PasswordInputText(text = passwordText, onQueryChange = viewModel::passwordText)

        Spacer(modifier = modifier.height(100.dp))

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(
                text = "Don't have an account? ",
                color = GreyDark
            )
            Text(text = "Register", fontWeight = FontWeight.Bold,modifier = Modifier.clickable {
                navToRegister()
            })
        }
        Spacer(modifier = modifier.height(16.dp))
        ButtonBig(text = "Sign In", onClick = {
            viewModel.loginFunction()
            //openAlertDialog.value = true
        })


    }
    if (alert) {
        AlertDialogComponent(
            onDismissRequest = { viewModel.alertStatus(true) },
            onConfirmation = { viewModel.alertStatus(false) },
            dialogTitle = alertData.status,
            dialogText = alertData.message,
            icon = alertData.icon
        )
    }
    LoadingComponent(modifier,loading,{})
}

@Composable
@Preview(showBackground = true)
fun LoginContentPreview() {
    HiServiceTheme {
        LoginContent(navToRegister = {})
    }
}