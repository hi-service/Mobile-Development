package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold
import com.hiservice.mobile.ui.theme.myFont

@Composable
fun ButtonBig(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    buttonColors : Color = YellowGold,
    textColor : Color = DarkCyan,
    onClick: () -> Unit,
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .background(color = buttonColors),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = myFont,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            modifier = Modifier.align(Alignment.CenterVertically),
            color = textColor,
        )
    }
}

@Composable
fun ButtonNormal(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color : Color = YellowGold,
    textColor : Color= DarkCyan
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .background(color = color),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = myFont,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            modifier = Modifier.align(Alignment.CenterVertically),
            color = textColor,
        )
    }
}

@Composable
fun ButtonNormalOutlined(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(54.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = DarkCyan, RoundedCornerShape(15.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = myFont,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            modifier = Modifier.align(Alignment.CenterVertically),
            color = DarkCyan,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonBigPreview(){
    HiServiceTheme {
        ButtonBig(text = "Register", onClick = {})
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonNormalPreview(){
    HiServiceTheme {
        ButtonNormalOutlined(text = "Register", onClick = {})
    }
}
@Composable
@Preview(showBackground = true)
fun ButtonNormalOutlinedPreview(){
    HiServiceTheme {
        ButtonNormalOutlined(text = "Register", onClick = {})
    }
}