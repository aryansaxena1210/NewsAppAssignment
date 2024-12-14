package com.example.newsappassignment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsappassignment.data.models.Article

@Composable
fun ListScreenView(navController : NavController, articles: List<Article>) {


    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            ListItem(navController, article)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }


}

//@Composable
//private fun ListItem(
//    navController: NavController,
//    article: Article
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp)
//            .background(Color.Magenta)
//            .clickable { navController.navigate("detailScreen/${article.title}") },
//        shape = RoundedCornerShape(8.dp),
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//        ) {
//            Text(
//                text = article.title,
//                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = article.byline ?: "No byline available",
//                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
//            )
//        }
//    }
//}

@Composable
private fun ListItem(
    navController: NavController,
    article: Article
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { navController.navigate("detailScreen/${article.title}") },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder for an article image
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = article.byline ?: "No byline available",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}