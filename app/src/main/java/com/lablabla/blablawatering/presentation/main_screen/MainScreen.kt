package com.lablabla.blablawatering.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.domain.model.Station
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun MainScreen(
    navigator: DestinationsNavigator,
    //viewModel: CompanyListingsViewModel = hiltViewModel()
    ) {
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
            items(10) { i->
                StationItem(station = Station(id = i, state = (i%2 == 0), gpio=0, name = "Name $i"))
                if (i < 10) {
                    Divider(
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}