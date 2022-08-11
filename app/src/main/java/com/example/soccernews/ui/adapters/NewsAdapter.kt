package com.example.soccernews.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccernews.databinding.NewsItemBinding
import com.example.soccernews.data.model.News

class NewsAdapter(private val context: Context, val dataSet: List<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var listenerFavorite: (View) -> Unit = {}

    inner class ViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.tvNewsTitle.text = news.title
            binding.tvNewsDescription.text = news.description

            binding.btnNewsLink.setOnClickListener {
                val sendIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                context.startActivity(sendIntent)
            }

            binding.imgNewsShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, news.url)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }

            binding.imgNewsFavorite.setOnClickListener {
                listenerFavorite(it)
            }

            Glide.with(context)
                .load(news.image)
                .centerCrop()
                .into(binding.imgNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflate, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}