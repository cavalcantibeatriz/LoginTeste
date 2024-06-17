package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InputWithIcon(
    icon: ImageVector,
    placeholder: String,
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.White
            ),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .height(IntrinsicSize.Min),

            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        )
                    }
                        innerTextField()
                    }
                }
            }
        )
    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = Color.White,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 3f,
        )
    }
}

@Composable
fun ExampleUsage() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        InputWithIcon(
            icon = Icons.Default.Person,
            placeholder = "Nome",
            modifier = Modifier.weight(1f),
            value = "",
            onValueChange = {/* TODO: Handle value change */}
        )
    }
}

@Preview
@Composable
fun ExampleUsagePreview(){
    ExampleUsage()
}