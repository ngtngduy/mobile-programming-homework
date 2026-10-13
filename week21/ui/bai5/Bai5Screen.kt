package com.example.week21.ui.bai5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Student(val name: String,val score: Double?)

val danhSach = listOf(
    Student("Nguyễn Văn An",9.0),
    Student("Trần Thị Bình",7.2),
    Student("Lê Minh Chi",null),
    Student("Phạm Quốc Dũng",4.5),
    Student("Võ Thành Em",5.8)
)
fun xepLoai(score: Double): String {
    if (score < 0 || score > 10) return "Không hợp lệ"
    else if (score >= 8.0) return "Giỏi"
    else if (score >= 6.5) return "Khá"
    else if (score >= 5) return "Trung bình"
    else return "Yếu"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Bai5Screen() {
    var tong = 0.0
    var dem = 0
    var gioi = 0
    var chuaCoDiem = 0
    var caoNhat = 0
    for (sv in danhSach) {
        if (sv.score != null) {
            tong = tong + sv.score
            dem++
        }
        if (xepLoai(sv.score) == "Giỏi") {
            gioi++
        }
    }
    val avgScore = if (dem>0) tong / dem else 0.0
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bảng điểm lớp")},
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFF2C4170), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text("Điểm trung bình của lớp", color = Color.White)
                Text("$avgScore",color = Color.White)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Giỏi", color = Color.White)
                    }
                    Column {
                        Text("Chưa có điểm",color = Color.White)
                    }
                    Column {
                        Text("Cao nhất",color = Color.White)
                    }
                }

            }
            Text("DANH SÁCH | ${danhSach.size} SINH VIÊN", modifier = Modifier.padding(16.dp))
            for (sv in danhSach) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column{
                        Text(
                            text = sv.name
                        )
                        Text(
                            text = if (sv.score == null) "Chưa có điểm" else xepLoai(sv.score)
                        )
                    }

                    Text(if (sv.score == null) "—" else xepLoai(sv.score))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bai5Preview() {
    Bai5Screen()
}