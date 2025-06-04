package com.example.registrationcompose.ui.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registrationcompose.utils.validateLoginInput
import com.example.registrationcompose.viewmodel.RegisterViewModel

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: RegisterViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Login",
            style = TextStyle(
                fontSize = 30.sp,
                color = Color.Black
            )
        )
        // Email Textview
        InputField(
            "Enter Email",
            email,
            { email = it },
            Icons.Default.Email,
            "email@mail.com",
            KeyboardType.Email
        )
        Spacer(Modifier.height(20.dp))
        // Password Textview
        InputField(
            "Enter Password",
            email,
            { email = it },
            Icons.Default.Lock,
            "******",
            KeyboardType.Email
        )
        Spacer(Modifier.height(35.dp))
        // Login Button
        Button(
            onClick = {
                if (context.validateLoginInput(email = email, password =  password)) {
                    onLogin(email, password, context, viewModel)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Login")
        }
    }
}

fun onLogin(email: String, password: String, context: Context, viewModel: RegisterViewModel) {
    viewModel.onLogin(
        email = email,
        password = password,
        onSuccess = {
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            // Navigate to home or next screen
        },
        onError = {
            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    )
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}