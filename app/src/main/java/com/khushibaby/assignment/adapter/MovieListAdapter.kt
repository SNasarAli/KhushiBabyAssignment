package com.khushibaby.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khushibaby.assignment.listner.MovieItemClickListner
import com.khushibaby.assignment.databinding.ItemMovieListBinding

class MovieListAdapter(private val ctx: Context,
                       private val listener: MovieItemClickListner
) : RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemMovieListBinding.inflate(
            LayoutInflater.from(ctx), parent, false))
    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.itemView.setOnClickListener{
            listener.onItemClick(holder.adapterPosition)
        }
    }

}