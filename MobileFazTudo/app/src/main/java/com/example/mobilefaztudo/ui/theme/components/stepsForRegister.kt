package com.example.faztudo_mb.ui.theme.screens.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


enum class StepState {
    INCOPLETE,
    COMPLETE
}

@Composable
fun RegisterSteps(currentStep: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        ) {
            Step(
                stepState = if (currentStep >= 1) StepState.COMPLETE else StepState.INCOPLETE,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Step(
                stepState = if (currentStep >= 2) StepState.COMPLETE else StepState.INCOPLETE,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))


            Step(
                stepState = if (currentStep >= 3) StepState.COMPLETE else StepState.INCOPLETE,
                modifier = Modifier.weight(1f)
            )


    }
}

@Composable
fun Step(stepState: StepState, modifier: Modifier = Modifier) {
    val circleColor = if (stepState == StepState.COMPLETE) Color.Green else Color.LightGray
    val lineColor = if (stepState == StepState.COMPLETE) Color.Green else Color.LightGray
    val icon = if (stepState == StepState.COMPLETE) Icons.Default.CheckCircle else Icons.Default.AddCircle

    Box(
        modifier = modifier
            .size(40.dp)
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = circleColor
        )
    }


//    if (stepState != StepState.INCOPLETE) {
//        Canvas(
//            modifier = Modifier
//
//                .padding(0.dp),
//            onDraw = {
//                val circleSize = 40.dp.toPx()
//                val halfCircleSize = circleSize / 2
//                val lineY = size.height / 2
//
//                // Desenhar a linha para conectar ao próximo círculo
//                val startX = size.width / 2 - halfCircleSize
//                val endX = size.width / 2 + halfCircleSize
//                drawLine(
//                    color = lineColor,
//                    start = Offset(startX, lineY),
//                    end = Offset(endX, lineY),
//                    strokeWidth = 4f,
//                )
//            }
//        )
//    }


}

@Preview
@Composable
fun RegisterStepsPreview() {
    RegisterSteps(currentStep = 1)
}
