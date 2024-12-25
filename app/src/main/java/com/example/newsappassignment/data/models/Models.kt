package com.example.newsappassignment.data.models

data class TopStoriesResponse(
    val results: List<Article>
)


data class Article(
    val title: String,
    val byline: String,
    val abstract: String,
    val url: String
)