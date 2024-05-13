package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButtom(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = Color(0xFFFFA500),
) {
    Button(
        onClick = onClick,
        modifier = modifier.width(IntrinsicSize.Min)
            .height(50.dp)
            .background(color = backgroundColor, shape)
            .padding(horizontal = 16.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Visible
        )
    }
}



@Preview
@Composable
fun CustomButtomPreview(){
    CustomButtom(
        text = "Cadastrar",
        onClick = { /* TODO: Handle button click */ },
        modifier = Modifier.padding(top = 16.dp)
    )
}