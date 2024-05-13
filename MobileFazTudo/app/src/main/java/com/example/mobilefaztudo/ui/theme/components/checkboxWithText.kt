package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LeftAlignedCheckbox(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit){
    Row (verticalAlignment = Alignment.CenterVertically){
        CheckboxWithText(text = text, checked = false, onCheckedChange = {})
        Spacer(modifier = Modifier.weight(1f))
    }
}
@Composable
fun CheckboxWithText(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1
        )
    }
}


@Preview
@Composable
fun LeftAlignedCheckboxPreview(){
    LeftAlignedCheckbox(
        text = "Quero trabalhar",
        checked = false,
        onCheckedChange = {}
    )
}