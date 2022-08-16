package com.example.soccernews.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.soccernews.data.model.News
import com.example.soccernews.domain.NewsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {

    private val _state = MutableLiveData<StateFavorite>()
    val state: LiveData<StateFavorite> = _state

    fun getNewsFavorite() {
        viewModelScope.launch {
            newsUseCase.getListNewsFavorite()
                .onStart {
                    _state.postValue(StateFavorite.Loading)
                }
                .catch {
                    _state.postValue(StateFavorite.Error(it.toString()))
                }
                .collect {
                    _state.postValue(StateFavorite.Success(it))
                }
        }
    }

    fun saveNews(news: News) {
        viewModelScope.launch {
            newsUseCase.insertFavorite(news)
                .onStart {
                    _state.postValue(StateFavorite.Loading)
                }
                .catch {
                    _state.postValue(StateFavorite.Error(it.toString()))
                }
                .collect {
                    _state.postValue(StateFavorite.Save)
                }
        }
    }

    sealed class StateFavorite{
        object Loading: StateFavorite()
        object Save: StateFavorite()
        data class Success(val list: List<News>): StateFavorite()
        data class Error(val error: String): StateFavorite()
    }

}