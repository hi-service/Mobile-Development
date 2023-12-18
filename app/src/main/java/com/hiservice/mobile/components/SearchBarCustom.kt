package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.WhiteReal
import com.hiservice.mobile.ui.theme.YellowGold
import com.hiservice.mobile.ui.theme.tipografi

@Composable
fun SearchBarCustom(modifier: Modifier = Modifier, hint: String, onClickSearch: ()-> Unit = {}) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val mediumTextStyle = tipografi.bodyLarge
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = DarkCyan, RoundedCornerShape(10.dp))
            .height(64.dp)
            .padding(end = 8.dp),
        value = text,
        placeholder = { Text(text = hint) },
        onValueChange = {
            text = it
        },
        trailingIcon = {
            IconButton(onClick = { onClickSearch() },
                modifier
                    .background(color = YellowGold, RoundedCornerShape(10.dp))
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_search),
                    contentDescription = "Search Button"
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = WhiteReal,
            unfocusedContainerColor = WhiteReal,
            cursorColor = DarkCyan,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            unfocusedTextColor = DarkCyan,
            focusedTextColor = DarkCyan,
            unfocusedPlaceholderColor = DarkCyan,
            focusedPlaceholderColor = Color.Transparent,
            focusedTrailingIconColor = DarkCyan,
            unfocusedTrailingIconColor = DarkCyan
        ),
        textStyle = mediumTextStyle
    )
//        TextField(
//            value = text,
//            onValueChange = { newText ->
//                onQueryChange(newText)
//            },
//            modifier = Modifier
//                .height(50.dp)
//                .clip(RoundedCornerShape(10.dp)),
//            trailingIcon = {
//                IconButton(
//                    onClick = {onClickSearch()}
//                ) {
//                   Icon(
//                       painter = painterResource(R.drawable.icon_search),
//                       contentDescription = "Search Button"
//                   )
//                }
//            },
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color.Transparent,
//                unfocusedContainerColor = Color.Transparent,
//                disabledContainerColor = Color.Transparent,
//                cursorColor = DarkCyan,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                focusedLabelColor = Color.Transparent,
//                unfocusedLabelColor = Color.Transparent,
//                unfocusedTextColor = DarkCyan,
//                focusedTextColor = DarkCyan,
//            )
//        )
//        IconButton(onClick = {onClickSearch()}) {
//            Box(modifier = modifier
//                .size(height = 50.dp, width = 50.dp)
//                .background(color = YellowGold)
//                .clip(RectangleShape)
//                .clip(RoundedCornerShape(10.dp)),
//                Alignment.Center
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.icon_search),
//                    contentDescription = "Toggle password visibility"
//                )
//            }
//        }
}

@Composable
@Preview(showBackground = true)
fun SearchBarCustomPreview(){
    HiServiceTheme {
        SearchBarCustom(hint = "Cari Sparepart", onClickSearch = {})
    }
}