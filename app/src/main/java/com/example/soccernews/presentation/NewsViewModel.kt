package com.example.soccernews.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.soccernews.data.model.News
import com.example.soccernews.domain.NewsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun getNewsService() {

        viewModelScope.launch {
            newsUseCase.getListNews()
                .onStart {
                    _state.postValue(State.Loading)
                }
                .catch {
                    _state.postValue(State.Error(it.toString()))
                }
                .collect {
                    _state.postValue(State.Success(it))
                }
        }

    }

    fun saveNews(news: News) {
        viewModelScope.launch {
            newsUseCase.insertFavorite(news)
                .onStart {
                    _state.postValue(State.Loading)
                }
                .catch {
                    _state.postValue(State.Error(it.toString()))
                }
                .collect {
                    _state.postValue(State.Save)
                }
        }
    }



    sealed class State{
        object Loading: State()
        object Save: State()
        data class Success(val list: List<News>): State()
        data class Error(val error: String): State()
    }

}