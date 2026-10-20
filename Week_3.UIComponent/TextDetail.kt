package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TextDetailScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Text Detail")},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(

                text = buildAnnotatedString {
                    append("The ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick")
                    }
                    append(" ")
                    withStyle(SpanStyle(color = Color(0xFFB8860B), fontSize = 32.sp)) {
                        append("Brown")
                    }
                    append("\nfox ")
                    withStyle(SpanStyle(letterSpacing = 6.sp)) {
                        append("jumps")
                    }
                    append(" ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append("over")
                    }
                    append("\n")
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the")
                    }
                    append(" ")
                    withStyle(SpanStyle(fontFamily = FontFamily.Cursive, fontStyle = FontStyle.Italic)) {
                        append("lazy")
                    }
                    append(" dog.")
                },
                fontSize = 24.sp,
                    lineHeight = 40.sp
            )
        }
    }
}