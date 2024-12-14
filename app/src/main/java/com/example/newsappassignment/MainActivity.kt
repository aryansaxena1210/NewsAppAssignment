package com.example.newsappassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsappassignment.data.models.Article
import com.example.newsappassignment.data.network.Retrofit.api
import com.example.newsappassignment.ui.theme.NewsAppAssignmentTheme
import com.example.newsappassignment.utils.Constant


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsAppAssignmentTheme  {

                val articles = remember { mutableStateListOf<Article>() }

                LaunchedEffect(Unit) {
                    setArticles(articles)
                }

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "listScreen") {
                    composable("listScreen") {
                        ListScreenView(navController, articles)
                    }

                    composable("detailScreen/{articleUrl}") { backStackEntry ->
                        val articleUrl = backStackEntry.arguments?.getString("articleUrl")
                        DetailScreenView(articleUrl, articles)
                    }
                }
            }
        }
    }

    private suspend fun setArticles(articles: SnapshotStateList<Article>) {
        try {
            val response = api.getTopStories(Constant.APIKEY)
            if (response.isSuccessful) {
                val fetchedArticles = response.body()?.results
                Log.d("MainActivity", "Articles: $articles")
                fetchedArticles?.let {
                    // Update the state with the fetched articles if not null
                    articles.clear()
                    articles.addAll(it)
                }
            } else {
                Log.e(
                    "MainActivity",
                    "Error: ${response.code()} - ${response.message()}"
                )
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exception: ${e.message}")
        }
    }
}
