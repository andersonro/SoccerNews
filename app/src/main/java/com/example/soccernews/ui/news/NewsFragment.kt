package com.example.soccernews.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.databinding.FragmentNewsBinding
import com.example.soccernews.presentation.NewsViewModel
import com.example.soccernews.ui.adapters.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModel()
    private val adapter by lazy { NewsAdapter(context!!, emptyList()) }

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

        viewModel.getNewsService()
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                NewsViewModel.State.Loading -> {
                    Log.e("FRAGMENT", "Carregando...")
                }
                is NewsViewModel.State.Error -> {
                    Log.e("FRAGMENT_ERROR", it.error.toString())
                }
                is NewsViewModel.State.Success -> {
                    Log.e("FRAGMENT_SUCCESS", it.toString())
                    //recyclerView.adapter = NewsAdapter(context!!, it.list)
                    adapter
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}