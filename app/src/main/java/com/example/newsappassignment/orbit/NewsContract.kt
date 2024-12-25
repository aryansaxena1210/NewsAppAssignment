package com.example.newsappassignment.orbit

import com.example.newsappassignment.data.models.Article

data class NewsState(
    val isLoading : Boolean = false,
    val articles : List<Article>? = emptyList(),
//    val article: Article
)

sealed class NewsSideEffects{

    data class Toast(val message : String) : NewsSideEffects()

    data class NavigateToDetails(val Title : String): NewsSideEffects()
}