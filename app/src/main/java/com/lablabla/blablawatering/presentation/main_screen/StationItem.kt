package com.lablabla.blablawatering.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lablabla.blablawatering.domain.model.Station

@Composable
fun StationItem(
    station: Station,
    modifier: Modifier = Modifier
) {
    val backroundColor = if (station.state) Color.Green else Color.Red
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = station.name,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = modifier.height(8.dp))
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .size(26.dp)
                .clip(CircleShape)
                .background(backroundColor)
        )
    }
}