package com.lablabla.blablawatering

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lablabla.blablawatering.presentation.NavGraphs
//import com.lablabla.blablawatering.presentation.main_screen.NavGraphs
import com.lablabla.blablawatering.presentation.navigation_bar.BottomNavItem
import com.lablabla.blablawatering.presentation.navigation_bar.BottomNavigationBar
import com.lablabla.blablawatering.ui.theme.BlablaWateringTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContent {
            BlablaWateringTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) {
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = NavGraphs.watering
                    )
                }
            }
        }
    }
}