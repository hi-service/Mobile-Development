package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.ui.theme.YellowGold


@Composable
fun AlertDialogComponent(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.1f)), contentAlignment = Alignment.Center){
        AlertDialog(
            icon = {
                Icon(
                    icon,
                    contentDescription = "Example Icon",
                    Modifier.size(height = 50.dp, width = 50.dp)
                )
            },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText, textAlign = TextAlign.Center)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Oke")
                }
            },

            containerColor = Color.White,
            iconContentColor = YellowGold
        )
    }

}


@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    HiServiceTheme {
        val openAlertDialog = remember { mutableStateOf(false) }
        AlertDialogComponent(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
            },
            dialogTitle = "Login Berhasil",
            dialogText = "Klik tombol Oke untuk lanjut ke Dashboard.",
            icon = Icons.Filled.CheckCircle
        )
    }
}