package com.example.newsappassignment

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsappassignment.viewmodel.NewsViewModel
import org.orbitmvi.orbit.compose.collectSideEffect


class MainActivity : ComponentActivity() {
    private val viewModel : NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "articles") {

                composable("articles") {
                    ListViewScreen(viewModel)
                }

                composable("articles/{articleTitle}"){ navBackStackEntry ->
                    val articleTitle = navBackStackEntry.arguments?.getString("articleTitle")
                    DetailScreenView(viewModel, articleTitle?: "")

                }
            }


            val currentContext = LocalContext.current
            viewModel.collectSideEffect { viewModel.handleSideEffect(
                it,
                currentContext,
                navController
            )}

        }



    }


}