package com.lablabla.blablawatering.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lablabla.blablawatering.domain.model.Device
import androidx.compose.ui.Alignment.Companion.Center
import com.lablabla.blablawatering.presentation.navigation_bar.WateringNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@WateringNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: MainScreenViewModel = hiltViewModel()
    ) {

    val state = viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 10.dp,
            modifier = Modifier
                .padding(16.dp)
        ) {
            DeviceItem(device = Device("Dummy", "0xdummy_address"))
        }
        Divider(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colors.primary),
        ) {
            items(state.value.stations.size) { i->
                StationItem(station = state.value.stations[i])
                if (i < state.value.stations.size) {
                    Divider(
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        if(state.value.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}