package com.lablabla.blablawatering.presentation.stations_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lablabla.blablawatering.R
import com.lablabla.blablawatering.presentation.navigation_bar.WateringNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@WateringNavGraph
@Destination
@Composable
fun StationsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.stations))
    }
}