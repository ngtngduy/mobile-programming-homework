package com.example.week21.ui.bai4

import android.util.Log
import android.util.Log.d
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//              Xếp loại	        Chữ	Nền
//Giỏi	        Color(0xFF2E7D32)	Color(0xFFE8F5E9)
//Khá	        Color(0xFF1565C0)	Color(0xFFE3F2FD)
//Trung bình	Color(0xFFE08A00)	Color(0xFFFFF3E0)
//Yếu	        Color(0xFFC62828)	Color(0xFFFFEBEE)
//Không hợp lệ	Color(0xFF6B7280)	Color(0xFFECEFF1)
fun xepLoai(diem: Double): String {
    if (diem < 0 || diem > 10) return "Không hợp lệ"
    else if (diem >= 8.0) return "Giỏi"
    else if (diem >= 6.5) return "Khá"
    else if (diem >= 5) return "Trung bình"
    else return "Yếu"
}

fun mauChu(loai: String): Color {
    if (loai == "Giỏi") return Color(0xFF2E7D32)
    else if (loai == "Khá") return Color(0xFF1565C0)
    else if (loai == "Trung bình") return Color(0xFFE08A00)
    else if (loai == "Yếu") return Color(0xFFC62828)
    else return Color(0xFF6B7280)
}

fun mauNen(loai: String): Color {
    if (loai == "Giỏi") return Color(0xFFE8F5E9)
    else if (loai == "Khá") return Color(0xFFE3F2FD)
    else if (loai == "Trung bình") return Color(0xFFFFF3E0)
    else if (loai == "Yếu") return Color(0xFFFFEBEE)
    else return Color(0xFFECEFF1)
}

val diem = 8.5
val loaiChinh = xepLoai(diem)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bai4Screen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Xếp loại học lực") },
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Điểm trung bình",
                    fontSize = 16.sp,
                )
                Text(
                    text = "$diem",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text("$loaiChinh",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = mauChu(loaiChinh),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(mauNen(loaiChinh), RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    textAlign = TextAlign.Center
                )

            }
            Text("KIỂM TRA THÊM",modifier = Modifier.padding(horizontal = 16.dp))
            for (d in listOf(9.0,7.0,5.5,3.0,11.0)) {
                val loai = xepLoai(d)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(Color.White,RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "Điểm $d"

                    )
                    Text(
                        text = "$loai",
                        color = mauChu(loai),
                        modifier = Modifier
                            .background(mauNen(loai), RoundedCornerShape(8.dp))
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bai4Preview() {
    Bai4Screen()
}
