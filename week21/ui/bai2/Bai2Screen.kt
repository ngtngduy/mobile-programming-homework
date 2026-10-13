package com.example.week21.ui.bai2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun dinhDang(x: Double): String {
    return if (x % 1.0 == 0.0) x.toLong().toString() else x.toString()
}
@Composable
fun Bai2Screen() {
    var so1 by remember {mutableStateOf("")}
    var so2 by remember {mutableStateOf("")}
    var phepToan by remember {mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bài thực hành 3")
        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = so1,
            onValueChange =  { so1 = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { phepToan = "+" },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC94C4C)),
                border = if (phepToan == "+") BorderStroke(2.dp, Color.Black) else null
                ) { Text("+") }
            Button(onClick = { phepToan = "-" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                border = if (phepToan == "-") BorderStroke(2.dp, Color.Black) else null
                ) { Text("-") }
            Button(onClick = { phepToan = "*" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                border = if (phepToan == "*") BorderStroke(2.dp, Color.Black) else null
                ) { Text("*") }
            Button(onClick = { phepToan = "/" },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
                border = if (phepToan == "/") BorderStroke(2.dp, Color.Black) else null
                ) { Text("/") }
        }

        OutlinedTextField(
            value = so2,
            onValueChange = {so2 = it},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        val a = so1.toDoubleOrNull()
        val b = so2.toDoubleOrNull()


        val ketQua = if (a == null || b == null) {
            ""
        } else {
            when (phepToan) {
                "+" -> dinhDang(a+b)
                "-" -> dinhDang(a-b)
                "*" -> dinhDang(a*b)
                "/" ->  dinhDang(a/b)
                else -> ""
            }
        }
        Text("Ket qua: ${ketQua}")
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bai2Preview() {
    Bai2Screen()
}