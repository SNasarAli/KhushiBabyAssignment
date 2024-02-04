package com.khushibaby.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.khushibaby.assignment.R
import com.khushibaby.assignment.listner.MovieItemClickListner
import com.khushibaby.assignment.databinding.ItemMovieListBinding
import com.khushibaby.assignment.helper.CommonUtils
import com.khushibaby.assignment.helper.Constant
import com.khushibaby.assignment.model.MoviesListDataModel

class MovieListAdapter(private val ctx: Context,
                       private var data: MutableList<MoviesListDataModel>,
                       private val listener: MovieItemClickListner
) : RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemMovieListBinding.inflate(
            LayoutInflater.from(ctx), parent, false))
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieInfoData = data[position]
        var percent = CommonUtils.getPercent(movieInfoData.voteAverage ?: 0.0)
        holder.binding.tvMovieName.text = movieInfoData.originalTitle
        holder.binding.tvReleaseDate.text = "${ctx.resources.getString(R.string.release_date)}${movieInfoData.releaseDate}"
        holder.binding.tvLang.text = "${ctx.resources.getString(R.string.lang)}${movieInfoData.originalLanguage}"
        holder.binding.tvVotes.text = "${ctx.resources.getString(R.string.up_vote)}${movieInfoData.voteCount}"
        holder.binding.tvVotesPercent.text = "${percent}%"
        holder.binding.voteProgressBar.progress = percent
        if (movieInfoData.adult == true){
            holder.binding.imgForViewers.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.adult_logo))
        }else{
            holder.binding.imgForViewers.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.ua_logo))
        }
        CommonUtils.loadImageWithGlide(holder.binding.imgBackground,Constant.IMAGE_URL+movieInfoData.backdropPath,ctx)
        holder.binding.itemView.setOnClickListener{
            listener.onItemClick(movieInfoData.id!!)
        }
    }

}