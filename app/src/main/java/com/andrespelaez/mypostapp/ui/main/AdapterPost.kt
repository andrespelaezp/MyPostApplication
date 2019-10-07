package com.andrespelaez.mypostapp.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrespelaez.mypostapp.data.Post
import android.view.LayoutInflater
import com.andrespelaez.mypostapp.R


class AdapterPost(
    var posts : List<Post>?
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(v)
    }

    override fun getItemCount(): Int {
        return if (posts != null) posts!!.size else 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindItems(posts?.get(position))
    }

    fun setData(newData: List<Post>) {
        posts = newData
        notifyDataSetChanged()
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(post: Post?) {
        val title = itemView.findViewById<TextView>(R.id.txt_title)
    }
}