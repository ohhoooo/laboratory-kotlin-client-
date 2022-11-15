package com.irlab.testappkotlin.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.irlab.testappkotlin.R
import com.irlab.testappkotlin.network.ItemModel

class RecyclerViewAdapter :
    PagingDataAdapter<ItemModel, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_crawling, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvCommunityTitle: TextView = view.findViewById(R.id.tv_communityTitle)
        private val tvTime: TextView = view.findViewById(R.id.tv_time)
        private val tvClassfication: TextView = view.findViewById(R.id.tv_classification)
        private val tvLike: TextView = view.findViewById(R.id.tv_like)
        private val tvComments: TextView = view.findViewById(R.id.tv_comments)

        fun bind(data: ItemModel) {
            tvTitle.text = data.title
            tvCommunityTitle.text = data.communityTitle
            tvTime.text = data.time
            tvClassfication.text = data.classification
            tvLike.text = data.like
            tvComments.text = data.comments

            Glide.with(imageView)
                .load(data.itemImageView).override(200, 200)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(imageView)

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.communityTitle == newItem.communityTitle
                    && oldItem.time == newItem.time
                    && oldItem.classification == newItem.classification
                    && oldItem.like == newItem.like
                    && oldItem.comments == newItem.comments
        }
    }
}