package com.example.mobilefaztudo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilefaztudo.ui.theme.MobileFazTudoTheme


class MainActivity : ComponentActivity() {
    private val loginViewModel= LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileFazTudoTheme {
                LoginScreen(loginViewModel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    val loginViewModel= LoginViewModel()
    MobileFazTudoTheme {
        LoginScreen(loginViewModel)
    }
}

