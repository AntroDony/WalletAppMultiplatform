package com.ancraz.mywallet_mult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ancraz.mywallet_mult.presentation.navigation.AppNavigation
import com.ancraz.mywallet_mult.presentation.navigation.NavigationRoute
import com.ancraz.mywallet_mult.presentation.ui.theme.MyWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MyWalletTheme {
                MainActivityScreen(
                    startDestinationRoute = NavigationRoute.HomeScreen
                )
            }
        }
    }
}

@Composable
private fun MainActivityScreen(
    startDestinationRoute: NavigationRoute
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->

        AppNavigation(
            startDestination = startDestinationRoute,
            innerPadding = innerPadding
        )
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}