package com.pedraza.sebastian.moviesandroidtmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pedraza.sebastian.core.navigation.Route
import com.pedraza.sebastian.moviesandroidtmdb.ui.theme.MoviesAndroidTMDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAndroidTMDBTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState) {
                    NavHost(navController = navController, startDestination = Route.ONBOARDING){
                        composable(Route.ONBOARDING){

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesAndroidTMDBTheme {
        Greeting("Android")
    }
}