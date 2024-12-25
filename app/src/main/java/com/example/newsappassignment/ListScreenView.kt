package com.example.newsappassignment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.ComposeNodeLifecycleCallback
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsappassignment.data.models.Article
import com.example.newsappassignment.viewmodel.NewsViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ListViewScreen(viewModel: NewsViewModel) {
    val state by viewModel.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {

                LazyColumn {
                    state.articles?.let { articleList ->
                        items(articleList) { article ->
                            ArticleCard(article, onClick = { viewModel.onListScreenCardClicked(article.title) } )
                        }?: item {Text(text = "article list is null")}
                    }
                }

            }
        }
    }
}

@Composable
fun ArticleCard(article: Article, onClick: () -> Unit ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.byline ?: "No byline available",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
