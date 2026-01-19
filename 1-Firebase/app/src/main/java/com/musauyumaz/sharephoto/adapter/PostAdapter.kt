package com.musauyumaz.sharephoto.adapter

import android.R.attr.text
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.musauyumaz.sharephoto.databinding.RecyclerRowBinding
import com.musauyumaz.sharephoto.model.Post
import com.squareup.picasso.Picasso

class PostAdapter(private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostHolder>(){

    class PostHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){}


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder,position: Int) {
        holder.binding.txtEmail.text = postList[position].email
        holder.binding.txtComment.text = postList[position].comment
        Picasso.get().load(postList[position].downloadUrl).into(holder.binding.recyclerImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}