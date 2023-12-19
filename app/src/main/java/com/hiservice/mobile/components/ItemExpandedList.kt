package com.hiservice.mobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.R
import com.hiservice.mobile.data.fake_data.generateItemList
import com.hiservice.mobile.data.model.ExpandableCardModel
import com.hiservice.mobile.ui.theme.GreyLight
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun ItemExpandedList(item: ExpandableCardModel) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .clickable { expanded = !expanded }
        .fillMaxWidth()
        .background(color = GreyLight, RoundedCornerShape(15.dp))
    ) {
        Column (
            Modifier.padding(20.dp)
        ){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (Modifier.weight(1f)){
                    Text(text = item.title, fontWeight = FontWeight.Bold, )
                }
                if (expanded){
                    Icon(painter = painterResource(id = R.drawable.arrow_up), contentDescription = "Bawah")
                }else if (!expanded){
                    Icon(painter = painterResource(id = R.drawable.arrow_down), contentDescription = "Bawah")
                }
            }
            if (expanded) {
                Text(text = item.description)
            }
        }
    }
}