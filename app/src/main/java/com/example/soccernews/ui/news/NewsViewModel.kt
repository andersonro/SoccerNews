package com.example.soccernews.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soccernews.domain.News

class NewsViewModel : ViewModel() {

    private val _news = MutableLiveData<List<News>>().apply {
        // TODO("REMOVER O MOCK")
        val list = ArrayList<News>()
        list.add(News("Primeira noticia", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"))
        list.add(News("Segunda noticia", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters"))
        value = list
    }

    val getNews: LiveData<List<News>> = _news

    private val _txt = MutableLiveData<String>().apply {
        value = "Anderson Roberto de Oliveira"
    }

    val getString: LiveData<String> = _txt
}