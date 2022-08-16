package com.example.soccernews.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.data.model.News
import com.example.soccernews.databinding.FragmentFavoriteBinding
import com.example.soccernews.presentation.FavoriteViewModel
import com.example.soccernews.ui.adapters.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val adapter by lazy { NewsAdapter(context!!) }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.rvNewsFavorite
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        listenerNewsFavorite()
        listenerNewsFavoriteObserve()
        listenerNewsFavoriteRefresh()

        adapter.listenerFavorite = { it ->
            val n = News(id = it.id, title = it.title, description = it.description, image = it.image, url = it.url, favorite = !it.favorite)
            favoriteViewModel.saveNews(n)

        }

        return root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listenerNewsFavorite(){
        favoriteViewModel.getNewsFavorite()
    }

    private fun listenerNewsFavoriteRefresh(){
        binding.srplNewsFavorite.setOnRefreshListener(this::listenerNewsFavorite)
    }

    private fun listenerNewsFavoriteObserve(){
        favoriteViewModel.state.observe(viewLifecycleOwner){
            when(it){
                FavoriteViewModel.StateFavorite.Loading -> {
                    binding.srplNewsFavorite.isRefreshing = true
                }
                is FavoriteViewModel.StateFavorite.Error -> {
                    binding.srplNewsFavorite.isRefreshing = false
                }
                is FavoriteViewModel.StateFavorite.Success -> {
                    binding.srplNewsFavorite.isRefreshing = false
                    adapter.submitList(it.list)
                }
                is FavoriteViewModel.StateFavorite.Save -> {
                    binding.srplNewsFavorite.isRefreshing = false
                    listenerNewsFavorite()
                }
            }
        }

    }


}