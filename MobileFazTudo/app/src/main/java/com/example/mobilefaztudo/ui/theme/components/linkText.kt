package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RigthAlignedLinkText(text: String, onClick: () -> Unit){
    Row (verticalAlignment = Alignment.CenterVertically){
            Spacer(modifier = Modifier.weight(1f))
            LinkText(text = text, onClick = onClick)
            }
    }



@Composable
fun LinkText(text: String, onClick: () -> Unit){
    ClickableText(
        text = buildAnnotatedString { 
             append(text)  
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                start = 0,
                end = text.length
            )
        },
        onClick = {offset -> 
            if (offset in 0 until text.length) {
                onClick()
            }
        }
    )
}

@Preview
@Composable
fun RigthAlignedLinkTextPreview(){
    RigthAlignedLinkText(text = "Esqueceu sua senha ?") {
        
    }
}