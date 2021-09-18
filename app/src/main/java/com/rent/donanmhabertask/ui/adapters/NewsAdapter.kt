package com.rent.donanmhabertask.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rent.donanmhabertask.R
import com.rent.donanmhabertask.data.model.Data
import com.rent.donanmhabertask.util.ToastHelper

class NewsAdapter(
    private var news: ArrayList<Data>,
    private val newsClickListener: NewsClickListener,
    private val isFavourite: Boolean
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    lateinit var context: Context

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.news_image)
        val titleText: TextView = view.findViewById(R.id.news_title)
        val contentLayout: ConstraintLayout = view.findViewById(R.id.content_layout)
        val contentText: TextView = view.findViewById(R.id.news_content)
        val totalReadText: TextView = view.findViewById(R.id.tv_total_read)
        val totalCommentText: TextView = view.findViewById(R.id.tv_total_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var data = news[position]

        holder.itemView.setOnClickListener {
            newsClickListener.onNewsClickListener(data)
        }

        holder.itemView.setOnLongClickListener {
            if (isFavourite)
                ToastHelper.makeToast(context,context.resources.getString(R.string.news_removed))
            else
                ToastHelper.makeToast(context, context.resources.getString(R.string.news_added))
            newsClickListener.onNewsLongClickListener(data)
        }
        holder.titleText.text = data.title
        holder.contentText.text = data.shortContent
        holder.totalCommentText.text = data.totalComment.toString()
        holder.totalReadText.text = data.totalRead.toString()

        holder.titleText.setTextColor(Color.parseColor(data.textColor))
        holder.contentText.setTextColor(Color.parseColor(data.subTextColor))
        holder.totalCommentText.setTextColor(Color.parseColor(data.textColor))
        holder.totalReadText.setTextColor(Color.parseColor(data.textColor))
        holder.contentLayout.setBackgroundColor(Color.parseColor(data.colorAvarage))

        Glide.with(context)
            .load(data.image.value)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun addData(listItems: ArrayList<Data>) {
        var size = news.size
        news.addAll(listItems)
        var newSize = news.size
        notifyItemRangeChanged(size, newSize)
    }

    fun changeData(listItems: ArrayList<Data>) {
        news = listItems
        notifyDataSetChanged()
    }


    interface NewsClickListener {
        fun onNewsClickListener(data: Data)
        fun onNewsLongClickListener(data: Data): Boolean
    }
}