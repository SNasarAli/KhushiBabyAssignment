package com.khushibaby.assignment.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.khushibaby.assignment.adapter.GenreListAdapter
import com.khushibaby.assignment.adapter.ProductionListAdapter
import com.khushibaby.assignment.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var mContext: Context
    private lateinit var layoutManager: LinearLayoutManager
    private var productionListAdapter: ProductionListAdapter? = null
    private var genreListAdapter: GenreListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = ActivityMovieDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)
        productionListAdapter = ProductionListAdapter(mContext)
        binding.layoutMovieDetail.recyclerProduction.layoutManager = layoutManager
        binding.layoutMovieDetail.recyclerProduction.adapter = productionListAdapter

        val flexboxLayoutManager = FlexboxLayoutManager(mContext)
        // Set flex direction.
        // Set flex direction.
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        // Set JustifyContent.
        // Set JustifyContent.
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

        genreListAdapter = GenreListAdapter(mContext)
        binding.layoutMovieDetail.recyclerGenre.layoutManager = flexboxLayoutManager
        binding.layoutMovieDetail.recyclerGenre.adapter = genreListAdapter


        binding.shimmerEffectDetail.shimmerDetailLayout.visibility = View.GONE
    }
}