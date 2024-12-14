package com.example.newsappassignment

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.newsappassignment.data.models.Article
import org.junit.Rule
import org.junit.Test

class ListScreenViewTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun articleListRenderTest() {

        val articles = listOf(
            Article(
                title = "Article 1", url = "URL 1",
                byline = "byline 1",
                abstract = "abstract 1"
            ),
            Article(
                title = "Article 2", url = "URL 2",
                byline = "byline 2",
                abstract = "abstract 2"
            )
        )

        rule.setContent {
            ListScreenView(navController = rememberNavController(), articles = articles)
        }

        rule.onNodeWithText("Article 1").assertExists()
        rule.onNodeWithText("Article 2").assertExists()
    }

}