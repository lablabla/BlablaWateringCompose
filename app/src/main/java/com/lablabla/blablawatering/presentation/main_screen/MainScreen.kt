package com.lablabla.blablawatering.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.presentation.navigation_bar.WateringNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@WateringNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
    ) {
    checkPermissions()
    val state = viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 10.dp,
            modifier = Modifier
                .height(120.dp)
                .padding(16.dp)
        ) {
            DeviceItem(device = state.value.device)
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun checkPermissions() {
    val locationPermissionsState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if(event == Lifecycle.Event.ON_START) {
                    locationPermissionsState.launchPermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
}