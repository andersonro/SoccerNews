package com.example.soccernews.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.data.model.News
import com.example.soccernews.databinding.FragmentNewsBinding
import com.example.soccernews.presentation.NewsViewModel
import com.example.soccernews.ui.adapters.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val newsViewModel: NewsViewModel by viewModel()
    private val adapter by lazy { NewsAdapter(context!!) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.rvNews
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        listenerNewsObserve()
        listenerNewsRefresh()

        adapter.listenerFavorite = { it ->
            val n = News(id = it.id, title = it.title, description = it.description, image = it.image, url = it.url, favorite = !it.favorite)
            newsViewModel.saveNews(n)

        }

        return root
    }

    override fun onStart() {
        super.onStart()
        listenerNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listenerNews(){
        newsViewModel.getNewsService()
    }

    private fun listenerNewsRefresh(){
        binding.srplMain.setOnRefreshListener(this::listenerNews    )
    }

    private fun listenerNewsObserve(){

        newsViewModel.state.observe(viewLifecycleOwner){
            when(it){
                NewsViewModel.State.Loading -> {
                    binding.srplMain.isRefreshing = true
                }
                is NewsViewModel.State.Error -> {
                    binding.srplMain.isRefreshing = false
                }
                is NewsViewModel.State.Success -> {
                    binding.srplMain.isRefreshing = false
                    adapter.submitList(it.list)
                }
                is NewsViewModel.State.Save -> {
                    binding.srplMain.isRefreshing = false
                }
            }
        }

    }
}