package com.hiservice.mobile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.GreyLight
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold


@Composable
fun InputTextCustom(hint: String,modifier: Modifier = Modifier,text: String, onQueryChange: (String) -> Unit){

    TextField(
        value = text,
        onValueChange = { newText -> onQueryChange(newText)},
        label = {Text(hint)},
        maxLines = 1,
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GreyLight,
            unfocusedContainerColor = GreyLight,
            disabledContainerColor = GreyLight,
            cursorColor = DarkCyan,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = GreyDark,
            unfocusedLabelColor = GreyDark,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
        )

    )
}

@Composable
fun InputTextNoBG(hint: String,modifier: Modifier = Modifier,text: String, onQueryChange: (String) -> Unit){

    TextField(
        value = text,
        onValueChange = { newText -> onQueryChange(newText)},
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = GreyLight,
            cursorColor = DarkCyan,
            focusedIndicatorColor = YellowGold,
            unfocusedIndicatorColor = YellowGold,
            focusedLabelColor = GreyDark,
            unfocusedLabelColor = GreyDark,
        )
    )
}

@Composable
fun inputTextLarge(modifier: Modifier = Modifier, onQueryChange: (String) -> Unit,text: String,hint: String =""){
    TextField(
        value = text,
        placeholder = {
                      Text(text = hint)
        },
        onValueChange = { newText ->
            onQueryChange(newText)},
        label = null, // Set label menjadi null untuk menghilangkannya
        maxLines = 4,
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth().padding(20.dp),
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
}


@Composable
fun PasswordInputText(modifier: Modifier = Modifier,text: String, onQueryChange: (String) -> Unit) {
    var isError by rememberSaveable  { mutableStateOf(false) }
    var passwordVisibility by rememberSaveable  { mutableStateOf(false) }

    val visibilityIcon: Painter = painterResource(id = if (passwordVisibility) R.drawable.ic_visibility_off else R.drawable.ic_visibility_on)

    if (isError) {
        Text(
            fontSize = 12.sp,
            text = "Password kurang dari 8 karakter",
            color = Color.Red.copy(alpha = 0.5f),
            modifier = modifier.fillMaxWidth()
        )
    }
    TextField(
        value = text,
        onValueChange = { newPassword ->
            onQueryChange(newPassword)
            isError = newPassword.length < 8 // Cek panjang password
        },
        label = { Text("Password") },
        modifier = Modifier
            .height(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = "Toggle password visibility"
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
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
        ,isError = isError,
    )
}


@Composable
fun EmailInputText(modifier: Modifier = Modifier,
                   text: String,
                   onQueryChange: (String) -> Unit) {
    var isError by rememberSaveable  { mutableStateOf(false) }

    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")

    if (isError) {
        Text(
            fontSize = 12.sp,
            text = "Format e-mail salah.",
            color = Color.Red.copy(alpha = 0.5f),
            modifier = modifier.fillMaxWidth()
        )
    }
    TextField(
        value = text,
        onValueChange = { newText ->
            onQueryChange(newText)
            isError = !newText.matches(emailRegex)
        },
        label = { Text("Email") },
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                isError = !text.matches(emailRegex) // Cek validitas email saat tombol "Done" ditekan
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GreyLight,
            unfocusedContainerColor = GreyLight,
            disabledContainerColor = GreyLight,
            cursorColor = DarkCyan,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = GreyDark,
            unfocusedLabelColor = GreyDark,
        ),
        isError = isError,
        singleLine = true
    )
}


@Composable
@Preview(showBackground = true)
fun EmailInputTextPreview() {
    HiServiceTheme {
        EmailInputText(Modifier,"ricky",{
        })
    }
}

@Composable
@Preview(showBackground = true)
fun PasswordInputTextPreview() {
    HiServiceTheme {
        PasswordInputText(Modifier,"", {

        })
    }
}
