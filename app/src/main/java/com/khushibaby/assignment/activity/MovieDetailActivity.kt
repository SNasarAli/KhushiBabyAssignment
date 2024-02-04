package com.khushibaby.assignment.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.khushibaby.assignment.R
import com.khushibaby.assignment.adapter.GenreListAdapter
import com.khushibaby.assignment.adapter.ProductionListAdapter
import com.khushibaby.assignment.api.ApiClient
import com.khushibaby.assignment.databinding.ActivityMovieDetailBinding
import com.khushibaby.assignment.helper.CommonUtils
import com.khushibaby.assignment.helper.Constant
import com.khushibaby.assignment.model.MovieDetailGenreModel
import com.khushibaby.assignment.model.MovieDetailModel
import com.khushibaby.assignment.model.MovieProductionCompany
import com.khushibaby.assignment.model.MoviesListDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MovieDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var mContext: Context
    private lateinit var layoutManager: LinearLayoutManager
    private var productionListAdapter: ProductionListAdapter? = null
    private var genreListAdapter: GenreListAdapter? = null
    private var movieId = 0
    private val genreList = ArrayList<MovieDetailGenreModel?>()
    private val producersList = ArrayList<MovieProductionCompany?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = ActivityMovieDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getDataFromBundle()
        setup()
        callMovieDetailApi()
    }

    private fun callMovieDetailApi() {
        if (CommonUtils.isConnectingToInternet(mContext)) {
            binding.shimmerEffectDetail.shimmerDetailLayout.visibility = View.VISIBLE
            val getMovieList = ApiClient.getClient().getMovieDetail(movieId.toString(),Constant.LANG, Constant.API_KEY)
            getMovieList.enqueue(object : Callback<MovieDetailModel> {
                override fun onResponse(
                    call: Call<MovieDetailModel>,
                    response: Response<MovieDetailModel>
                ) {
                    if (response.code() == 200) {
                        binding.shimmerEffectDetail.shimmerDetailLayout.visibility = View.GONE
                        updateData(response.body())
                    }else if (response.code() == 404){
                        CommonUtils.showSnackbarWithoutView(
                            resources.getString(R.string.detail_not_found),
                            mContext,
                            0
                        )
                    } else {
                        CommonUtils.showSnackbarWithoutView(
                            resources.getString(R.string.something_went_wrong),
                            mContext,
                            0
                        )
                    }
                }

                override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
                    CommonUtils.showSnackbarWithoutView(
                        resources.getString(R.string.something_went_wrong),
                        mContext,
                        0
                    )
                    Log.e("Api Fail","----${t.message}")
                }
            })
        } else {
            CommonUtils.showSnackbarWithoutView(
                resources.getString(R.string.no_network_error),
                mContext,
                0
            )
        }
    }

    private fun updateData(body: MovieDetailModel?) {
        CommonUtils.loadImageWithGlide(binding.imgBanner,Constant.IMAGE_URL+body?.backdropPath,mContext)
        binding.layoutMovieDetail.tvTitle.text  = body?.originalTitle
        binding.layoutMovieDetail.tvTagline.text = body?.tagline
        CommonUtils.loadImageWithGlide(binding.layoutMovieDetail.imgPoster,Constant.IMAGE_URL+body?.posterPath,mContext)
        if (body?.adult == true){
            binding.layoutMovieDetail.imgForViewers.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.adult_logo))
        }else{
            binding.layoutMovieDetail.imgForViewers.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ua_logo))
        }
       binding.layoutMovieDetail.tvLang.text = body?.originalLanguage
       binding.layoutMovieDetail.tvMovieLength.text = CommonUtils.getMovieLength(body?.runtime)
        binding.layoutMovieDetail.tvReleaseDate.text = body?.releaseDate
        binding.layoutMovieDetail.tvBudget.text = "${mContext.resources.getString(R.string.budget)}${body?.budget}"
        binding.layoutMovieDetail.tvEarnings.text = "${mContext.resources.getString(R.string.earn)}${body?.revenue}"
        binding.layoutMovieDetail.tvVotes.text = "${mContext.resources.getString(R.string.up_vote)}${body?.voteCount.toString()}"
        binding.layoutMovieDetail.tvStoryLine.text = body?.overview

        if (body?.genres!=null){
            genreList.addAll(body.genres!!)
        }
        if (body?.productionCompanies!=null){
            producersList.addAll(body.productionCompanies!!)
        }
        genreListAdapter?.notifyDataSetChanged()
        productionListAdapter?.notifyDataSetChanged()
    }

    private fun getDataFromBundle() {
        val bundle = intent.extras
        movieId = bundle!!.getInt("movieId",0)
    }

    private fun setup() {
        binding.imgBack.setOnClickListener {
           finish()
        }
        layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)
        productionListAdapter = ProductionListAdapter(mContext,producersList)
        binding.layoutMovieDetail.recyclerProduction.layoutManager = layoutManager
        binding.layoutMovieDetail.recyclerProduction.adapter = productionListAdapter

        val flexboxLayoutManager = FlexboxLayoutManager(mContext)
        // Set flex direction.
        // Set flex direction.
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        // Set JustifyContent.
        // Set JustifyContent.
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

        genreListAdapter = GenreListAdapter(mContext,genreList)
        binding.layoutMovieDetail.recyclerGenre.layoutManager = flexboxLayoutManager
        binding.layoutMovieDetail.recyclerGenre.adapter = genreListAdapter
    }
}