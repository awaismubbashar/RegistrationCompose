package com.example.registrationcompose.ui.composables

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import com.example.registrationcompose.utils.validateInputs

@Composable
fun RegisterScreen(onRegisterClick: (String, String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "SignUp",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Black
                )
            )
        }
        Spacer(Modifier.height(30.dp))

        InputField(
            "Enter Email",
            email,
            { email = it },
            Icons.Default.Email,
            "example@gmail.com",
            KeyboardType.Email
        )
        Spacer(Modifier.height(15.dp))

        InputField(
            "Enter Name",
            name,
            { name = it },
            Icons.Default.AccountCircle,
            "Awais",
            KeyboardType.Text
        )
        Spacer(Modifier.height(15.dp))

        InputField(
            "Enter Password",
            password,
            { password = it },
            Icons.Default.Lock,
            "******",
            KeyboardType.Password
        )
        Spacer(Modifier.height(35.dp))

        Button(
            onClick = {
                if (context.validateInputs(name = name, password = password, email = email)) {
                    onRegisterClick(name, password, email)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen { _, _, _ -> }
}