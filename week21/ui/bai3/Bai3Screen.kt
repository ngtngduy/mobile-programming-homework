package com.example.week21.ui.bai3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)
val danhSach = listOf(
    Student("Nguyễn Văn An", "an@sv.edu.vn", "0901 234 567"),
    Student("Trần Thị Bình", null, "0912 888 999"),
    Student("Lê Minh Chi", "chi@sv.edu.vn", null),
    Student("Phạm Quốc Dũng", null, null),
    Student("Võ Thanh Em", "em@sv.edu.vn", "0933 111 222"),
    Student("Nguyễn Tùng Duy", "duynt6060@sv.edu.vn","0773158442")
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bai3Screen() {
    var thieuEmail = 0
    for (sv in danhSach) {
        if (sv.email == null) {
            thieuEmail++
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách sinh viên") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2C4170),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            for (sv in danhSach) {
                Spacer(Modifier.height(6.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = sv.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(12.dp))
                    Row {
                        Text("Email: ", fontSize = 14.sp, color = Color.Gray)
                        Text(
                            text = sv.email ?: "Chưa cập nhật",
                            fontSize = 14.sp,
                            color = if (sv.email == null) Color(0xFFC0392B) else Color.Gray,
                            fontStyle = if (sv.email == null) FontStyle.Italic else FontStyle.Normal
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Row {
                        Text("Phone: ", fontSize = 14.sp, color = Color.Gray)
                        Text(
                            text = sv.phone ?: "Chưa cập nhật",
                            fontSize = 14.sp,
                            color = if (sv.phone == null) Color(0xFFC0392B) else Color.Gray,
                            fontStyle = if (sv.phone == null) FontStyle.Italic else FontStyle.Normal
                        )
                    }
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .background(Color(0xFFE6EBF5), RoundedCornerShape(12.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row {
                    Text("Tổng: ")
                    Text(
                        text = "${danhSach.size} sinh viên",
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text("Thiếu email: ")
                    Text(
                        text = "$thieuEmail",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bai3Preview() {
    Bai3Screen()
}