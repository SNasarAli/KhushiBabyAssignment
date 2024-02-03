package com.khushibaby.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khushibaby.assignment.databinding.ListItemGenreBinding

class GenreListAdapter (
    private val ctx: Context,
) : RecyclerView.Adapter<GenreListAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ListItemGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ListItemGenreBinding.inflate(
            LayoutInflater.from(ctx), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }
    override fun getItemCount() = 5
}