package com.pedraza.sebastian.moviesandroidtmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pedraza.sebastian.core.navigation.Routes
import com.pedraza.sebastian.movie_presentation.movie_detail.MovieDetailScreen
import com.pedraza.sebastian.movie_presentation.movie_list.MovieListScreen
import com.pedraza.sebastian.moviesandroidtmdb.navigation.navigate
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

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(navController = navController, startDestination = Routes.MOVIE_LIST) {
                        composable(Routes.MOVIE_LIST) {
                            MovieListScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(
                            "${Routes.MOVIE_DETAIL}/{${Routes.MOVIE_ID_ARGUMENT}}",
                            arguments = listOf(navArgument(Routes.MOVIE_ID_ARGUMENT) {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.getInt(Routes.MOVIE_ID_ARGUMENT)
                                ?.let { movieId ->
                                    MovieDetailScreen(
                                        scaffoldState = scaffoldState,
                                        onNavigate = navController::navigate,
                                        movieId = movieId,
                                        onNavigateUp = {
                                            navController.navigateUp()
                                        }
                                    )
                                }
                        }
                    }
                }
            }
        }
    }

}
