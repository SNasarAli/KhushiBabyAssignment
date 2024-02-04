package com.khushibaby.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khushibaby.assignment.databinding.ListItemProductionBinding
import com.khushibaby.assignment.helper.CommonUtils
import com.khushibaby.assignment.helper.Constant
import com.khushibaby.assignment.model.MovieProductionCompany
import com.khushibaby.assignment.model.MoviesListDataModel

class ProductionListAdapter(
    private val ctx: Context,
    private var data: MutableList<MovieProductionCompany?>,
) : RecyclerView.Adapter<ProductionListAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ListItemProductionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ListItemProductionBinding.inflate(
            LayoutInflater.from(ctx), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.binding.tvProductionName.text = data[position]?.name
       CommonUtils.loadImageWithGlide(holder.binding.imgProductionLogo,Constant.IMAGE_URL+data[position]?.logoPath,ctx)
    }
    override fun getItemCount() = data.size
}