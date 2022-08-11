package com.example.soccernews.ui.favorite

import androidx.lifecycle.*
import com.example.soccernews.data.model.News
import com.example.soccernews.domain.NewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel() : ViewModel(), LifecycleObserver {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    /*
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getNewsFavorites(){
        viewModelScope.launch {
            newsUseCase.getNewsListFavorite()
                .flowOn(Dispatchers.Main)
                .onStart {
                    _state.value = State.Loading
                }
                .catch {
                    _state.value = State.Error(it.toString())
                }
                .collect {
                    _state.value = State.Success(it)
                }
        }
    }
    */

    sealed class State{
        object Loading: State()
        data class Success(val value: List<News>): State()
        data class Error(val error: String): State()
    }

}