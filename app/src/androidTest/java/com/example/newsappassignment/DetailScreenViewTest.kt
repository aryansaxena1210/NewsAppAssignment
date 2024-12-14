package com.example.newsappassignment

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.newsappassignment.data.models.Article
import org.junit.Rule
import org.junit.Test

class DetailScreenViewTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun detailScreenView_displaysCorrectArticle() {
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
            DetailScreenView(articleTitle = "Article 1", articles = articles)
        }

        rule.onNodeWithText("Article 1").assertExists()
        rule.onNodeWithText("byline 1").assertExists()
    }

}