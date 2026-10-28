package com.example.dataflownavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
val BlueMain = Color(0xFF5B8DEF)

// Khung chung: nút back (tùy chọn), logo, SmartTasks, tiêu đề, mô tả, nội dung
@Composable
fun AuthLayout(
    title: String,
    subtitle: String,
    onBackClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth().height(56.dp), verticalAlignment = Alignment.CenterVertically) {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                    colors = IconButtonDefaults.iconButtonColors(containerColor = BlueMain)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Quay lại", tint = Color.White)
                }
            }
        }
        Spacer(Modifier.height(32.dp))
        Image(painterResource(R.drawable.uth_logo), null, Modifier.height(72.dp))
        Spacer(Modifier.height(8.dp))
        Text("SmartTasks", color = BlueMain, fontSize = 20.sp)
        Spacer(Modifier.height(32.dp))
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(subtitle, fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
        Spacer(Modifier.height(24.dp))
        content()
    }
}

@Composable
fun BlueButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(48.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BlueMain)
    ) { Text(text) }
}

// Màn 1: nhập email. Nhận lại dữ liệu khi Submit ở màn 4 (nếu có)
@Composable
fun ForgotPasswordScreen(
    receivedEmail: String,
    receivedOtp: String,
    receivedPass: String,
    onNext: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    AuthLayout("Forget Password?", "Enter your Email, we will send you a verification code.") {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Your Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        BlueButton("Next") { onNext(email) }

        if (receivedEmail.isNotEmpty()) {
            Spacer(Modifier.height(24.dp))
            Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFE3EEFB))) {
                Column(Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Dữ liệu nhận từ màn hình 4", fontWeight = FontWeight.Bold)
                    Text("email: $receivedEmail")
                    Text("otp: $receivedOtp")
                    Text("pass: $receivedPass")
                }
            }
        }
    }
}

// Màn 2: nhập OTP 5 ô
@Composable
fun VerificationScreen(email: String, onBackClick: () -> Unit, onNext: (String) -> Unit) {
    var otp by remember { mutableStateOf("") }
    AuthLayout(
        "Verify Code",
        "Enter the code we just sent you on your registered Email\n($email)",
        onBackClick
    ) {
        val focusManager = LocalFocusManager.current

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(5) { i ->
                OutlinedTextField(
                    value = otp.getOrNull(i)?.toString() ?: "",
                    onValueChange = { new ->
                        if (new.length <= 1 && new.all { it.isDigit() }) {
                            otp = if (new.isEmpty()) otp.dropLast(1)
                            else (otp.take(i) + new).take(5)
                            if (new.isNotEmpty() && i < 4) focusManager.moveFocus(FocusDirection.Next)
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        BlueButton("Next") { onNext(otp) }
    }
}

// Màn 3: nhập mật khẩu
@Composable
fun ResetPasswordScreen(onBackClick: () -> Unit, onNext: (String) -> Unit) {
    var pass by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    AuthLayout(
        "Create new password",
        "Your new password must be different form previously used password",
        onBackClick
    ) {
        OutlinedTextField(
            value = pass, onValueChange = { pass = it },
            label = { Text("Password") }, singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = confirm, onValueChange = { confirm = it },
            label = { Text("Confirm Password") }, singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        BlueButton("Next") { onNext(pass) }
    }
}

// Màn 4: xem lại cả 3 dữ liệu
@Composable
fun ConfirmScreen(
    email: String,
    otp: String,
    pass: String,
    onBackClick: () -> Unit,
    onSubmit: () -> Unit
) {
    AuthLayout("Confirm", "We are here to help you!", onBackClick) {
        OutlinedTextField(value = email, onValueChange = {}, readOnly = true,
            modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = otp, onValueChange = {}, readOnly = true,
            modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = "*".repeat(pass.length), onValueChange = {}, readOnly = true,
            modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        BlueButton("Submit", onSubmit)
    }
}