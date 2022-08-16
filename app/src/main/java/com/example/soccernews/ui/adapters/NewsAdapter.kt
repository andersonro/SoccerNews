package com.example.soccernews.ui.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccernews.databinding.NewsItemBinding
import com.example.soccernews.data.model.News

class NewsAdapter(private val context: Context) :
    ListAdapter<News, NewsAdapter.ViewHolder>(DiffCallback()) {

    var listenerFavorite: (News) -> Unit = {}

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

            val favoriteColor = if(news.favorite) android.R.color.holo_red_dark else android.R.color.darker_gray

            binding.imgNewsFavorite.setColorFilter(context.resources.getColor(favoriteColor))


            binding.imgNewsFavorite.setOnClickListener {
                listenerFavorite(news)
            }

            Glide.with(context)
                .load(news.image)
                .centerCrop()
                .into(binding.imgNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class DiffCallback: DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem.id == newItem.id

}