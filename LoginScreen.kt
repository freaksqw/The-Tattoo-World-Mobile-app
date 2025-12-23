package com.example.worldtatu.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(
    onSuccess: (email: String) -> Unit,
    modifier: Modifier = Modifier
)
{
    val (email, setEmail) = remember { mutableStateOf("demo@tattoo.app") }
    val (password, setPassword) = remember { mutableStateOf("password") }
    val (isRegister, setIsRegister) = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(if (isRegister) "Регистрация" else "Вход")
        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Text(if (isRegister) "Уже есть аккаунт?" else "Нет аккаунта?")
            TextButton(onClick = { setIsRegister(!isRegister) }) {
                Text(if (isRegister) "Войти" else "Зарегистрируйтесь")
            }
        }
        Button(
            onClick = { onSuccess(email) },
            modifier = Modifier.fillMaxWidth()
        ) { Text(if (isRegister) "Зарегистрироваться" else "Войти") }
        Text(
            "Для демо данные не проверяются, переключение только меняет текст.",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

