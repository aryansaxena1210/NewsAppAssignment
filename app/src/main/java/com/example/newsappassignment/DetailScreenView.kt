package com.example.newsappassignment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappassignment.data.models.Article


@Composable
fun DetailScreenView(articleTitle: String?, articles : List<Article>) {
    if(articleTitle!=null) {
        val article = remember { mutableStateOf<Article?>(null) }

        article.value = articles.find { it.title == articleTitle }

//        article.value?.let { article ->
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(
//                    text = article.title,
//                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                Text(
//                    text = article.byline ?: "No byline available",
//                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                Text(
//                    text = "Abstract: ${article.abstract}",
//                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                Text(
//                    text = "URL: ${article.url}",
//                    style = MaterialTheme.typography.bodyLarge.copy(
//                        fontSize = 14.sp,
//                        color = Color.Blue
//                    ),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//            }
//        }

        article.value?.let { article ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .background(Color.LightGray),

            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = article.byline ?: "No byline available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(
                        text = article.abstract,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                // URL Component
                Text(
                    text = "URL: ${article.url}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        color = Color.Blue
                    ),
                    modifier = Modifier.padding(bottom = 8.dp, top = 10.dp
                    )
                )
            }
        }

    }

}


