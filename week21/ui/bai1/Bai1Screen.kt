package com.example.week21.ui.bai1

import android.R.attr.fontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week21.R
@Composable
fun Bai1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Anh dai dien",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nguyen Tung Duy - CNXX",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Du dinh tuong lai cua ban la gi sau khi hoc xong mon nay",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bai1Preview() {
    Bai1Screen()
}
