package com.example.worldtatu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.worldtatu.ui.WorldTatuApp
import com.example.worldtatu.ui.theme.WorldTatuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDark = rememberSaveable { mutableStateOf(false) }
            WorldTatuTheme(useDarkTheme = isDark.value) {
                WorldTatuApp(
                    useDarkTheme = isDark.value,
                    onToggleTheme = { isDark.value = !isDark.value }
                )
            }
        }
    }
}

