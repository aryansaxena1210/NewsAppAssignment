package com.example.newsappassignment.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.newsappassignment.data.models.Article
import com.example.newsappassignment.data.network.Retrofit.api
import com.example.newsappassignment.orbit.NewsSideEffects
import com.example.newsappassignment.orbit.NewsState
import com.example.newsappassignment.utils.Constant
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class NewsViewModel(): ViewModel(), ContainerHost<NewsState, NewsSideEffects> {

    override val container = container<NewsState, NewsSideEffects>(NewsState())

    init {
        setTopStories()
    }

    private fun setTopStories() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        try {
            val fetchedData = api.getTopStories(Constant.APIKEY)
            if(fetchedData.isSuccessful){
                reduce { state.copy( articles = fetchedData.body()?.results?: emptyList(), isLoading = false) }
                Log.i("populated state.articles", state.articles.toString())
            }
            else{
                Log.e("Error while fetching Top Stories", fetchedData.errorBody().toString())
                reduce { state.copy(isLoading = false) }
                postSideEffect(NewsSideEffects.Toast("Error while fetching stories"))
            }

        }catch (e : Exception){
            Log.e("Exception while fetching Top Stories", e.message.toString())
            reduce { state.copy(isLoading = false) }
            postSideEffect(NewsSideEffects.Toast("Exception while fetching stories"))
        }
    }

    fun onListScreenCardClicked(title : String) = intent {
        postSideEffect(NewsSideEffects.NavigateToDetails( title ))
        Log.i("Navigation Side Effect posted", "")
    }

    fun handleSideEffect(it: NewsSideEffects, context: Context, navController: NavHostController ) {
        when(it){
            is NewsSideEffects.NavigateToDetails -> {
                navController.navigate(
                    route = "articles/${it.Title}",
                    )
            }


            is NewsSideEffects.Toast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun findArticleByTitle(title: String): Article? {
        return container.stateFlow.value.articles?.find { it.title==title }
    }


}