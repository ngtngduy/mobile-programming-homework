package com.example.librarymanagementsystem.Screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.librarymanagementsystem.Library
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarymanagementsystem.LibraryItem

@Composable
fun ManageScreen(library: Library) {
    var selectedStudent by remember { mutableStateOf(library.students.first()) }
    var showDialog by remember { mutableStateOf(false) }
    val selectedItems = remember { mutableStateListOf<LibraryItem>() }
    var message by remember { mutableStateOf("") }
    var refresh by remember { mutableIntStateOf(0) }   // đổi số này = ép vẽ lại

    val tick = refresh

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            "Hệ thống\nQuản lý Thư viện",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        // Chọn sinh viên
        Text("Sinh viên", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = selectedStudent.name,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = { showDialog = true }) { Text("Thay đổi") }
        }

        Spacer(Modifier.height(16.dp))

        // Danh sách sách
        Text("Danh sách sách", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE0E0E0))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(library.books) { item ->
                val available = remember(tick) { item.isAvailable() }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item in selectedItems,
                        enabled = available,
                        onCheckedChange = { checked ->
                            if (checked) selectedItems.add(item) else selectedItems.remove(item)
                        }
                    )
                    Text(item.getInfo(), fontSize = 14.sp)
                }
            }
        }

        if (message.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(message, color = Color.Gray)
        }

        Spacer(Modifier.height(12.dp))

        // Nút Thêm
        Button(
            onClick = {
                var success = 0
                selectedItems.forEach { item ->
                    if (library.lendBook(selectedStudent, item)) success++
                }
                message = "${selectedStudent.name} đã mượn thêm $success cuốn"
                selectedItems.clear()
                refresh++
            },
            enabled = selectedItems.isNotEmpty(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Thêm")
        }
    }

    // Hộp thoại chọn sinh viên
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Chọn sinh viên") },
            text = {
                Column {
                    library.students.forEach { student ->
                        TextButton(onClick = {
                            selectedStudent = student
                            showDialog = false
                        }) {
                            Text("${student.name} (${student.id})")
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) { Text("Đóng") }
            }
        )
    }
}