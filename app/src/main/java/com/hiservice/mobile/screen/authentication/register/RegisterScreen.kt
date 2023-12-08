package com.hiservice.mobile.screen.authentication.register

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
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.EmailInputText
import com.hiservice.mobile.components.InputTextCustom
import com.hiservice.mobile.components.LoadingComponent
import com.hiservice.mobile.components.PasswordInputText
import com.hiservice.mobile.screen.authentication.login.LoginViewModel
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
){

}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    navToLogin : () -> Unit
){
    val current = LocalContext.current
    val viewModelFactory = remember { ViewModelFactory.getInstance(current) }
    val viewModel: RegisterViewModel = viewModel(factory = viewModelFactory)
    val emailText by viewModel.email
    val passwordText by viewModel.password
    val loading by viewModel.loading
    val scrollState = rememberScrollState()
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
            text = "Hi user, let's create your new account.",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 26.sp * 1.24f
        )
        Spacer(modifier = modifier.height(14.dp))
        Text(
            text = "Create an account so you can contact a mechanic at your location from anywhere.",
            lineHeight = 16.sp * 1.24f,
            color = GreyDark
        )

        Spacer(modifier = modifier.height(36.dp))

/*        InputTextCustom(
            hint = "Full name",

        )*/
        Spacer(modifier = modifier.height(16.dp))
        EmailInputText(text = emailText, onQueryChange = viewModel::emailText)
        Spacer(modifier = modifier.height(16.dp))
        PasswordInputText(text = passwordText, onQueryChange = viewModel::passwordText)

        Spacer(modifier = modifier.height(85.dp))
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(
                text = "Already have an account? ",
                color = GreyDark
            )
            Text(text = "Sign In", fontWeight = FontWeight.Bold , modifier = Modifier.clickable{
                navToLogin()
            })

        }
        Spacer(modifier = modifier.height(16.dp))
        ButtonBig(text = "Register", onClick = {
            viewModel.registerFunction()
        })
        LoadingComponent(loading,{})
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterContentPreview() {
    HiServiceTheme {
        RegisterContent(navToLogin ={})
    }
}