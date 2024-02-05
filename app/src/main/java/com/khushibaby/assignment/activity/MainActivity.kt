package com.khushibaby.assignment.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.khushibaby.assignment.R
import com.khushibaby.assignment.adapter.MovieListAdapter
import com.khushibaby.assignment.api.ApiClient
import com.khushibaby.assignment.databinding.ActivityMainBinding
import com.khushibaby.assignment.helper.CommonUtils
import com.khushibaby.assignment.helper.Constant
import com.khushibaby.assignment.listner.MovieItemClickListner
import com.khushibaby.assignment.localdb.MyAppDataBase
import com.khushibaby.assignment.model.MovieDataClass
import com.khushibaby.assignment.model.MoviesListModel
import com.khushibaby.assignment.repository.MyRepository
import com.khushibaby.assignment.viewmodel.MyViewModel
import com.khushibaby.assignment.viewmodel.MyViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context
    private lateinit var layoutManager: LinearLayoutManager
    private var movieListAdapter: MovieListAdapter? = null
    private val movieList = ArrayList<MovieDataClass>()
    private var page = 0
    private var loadMore = false
//    private val movieDataClass = ArrayList<MovieDataClass>()
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setup()
        callMovieListApi(true)
    }

    private fun callMovieListApi(showLoader: Boolean) {
        if (CommonUtils.isConnectingToInternet(mContext)) {
            if (showLoader){
                binding.shimmerEffect.shimmerLayout.visibility = View.VISIBLE
            }
            val getMovieList = ApiClient.getClient().getPopularMovies(Constant.LANG, ++page,Constant.API_KEY)
            getMovieList.enqueue(object : Callback<MoviesListModel> {
                override fun onResponse(
                    call: Call<MoviesListModel>,
                    response: Response<MoviesListModel>
                ) {
                    binding.swipeRefreshLayout.isRefreshing = false
                    if (response.code() == 200) {
                        binding.shimmerEffect.shimmerLayout.visibility = View.GONE
                        if (response.body()?.results != null){
                            updateData(response.body()?.results!!)
                        }
                    } else {
                        CommonUtils.showSnackbarWithoutView(
                            resources.getString(R.string.something_went_wrong),
                            mContext,
                            0
                        )
                    }
                }

                override fun onFailure(call: Call<MoviesListModel>, t: Throwable) {
                    binding.swipeRefreshLayout.isRefreshing = false
                    CommonUtils.showSnackbarWithoutView(
                        resources.getString(R.string.something_went_wrong),
                        mContext,
                        0
                    )
                    Log.e("Api Fail","----${t.message}")
                }
            })
        } else {
            binding.swipeRefreshLayout.isRefreshing = false
            CommonUtils.showSnackbarWithoutView(
                resources.getString(R.string.no_network_error),
                mContext,
                0
            )
            getLocalData()
        }
    }

    private fun getLocalData() {
            movieList.addAll(viewModel.getItems())
            movieListAdapter?.notifyDataSetChanged()
    }

    private fun updateData(results: List<MovieDataClass>) {
        if (movieList.size > 0 && movieList.last().id == -1){
            loadMore = false
            movieList.removeAt(movieList.size - 1)
            movieListAdapter!!.notifyItemRemoved(movieList.size)
        }
        movieList.addAll(results)
        movieListAdapter?.notifyItemRangeInserted(movieList.size - results.size, results.size)
        saveListItemToDB()
    }

    private fun saveListItemToDB() {
        viewModel.insertItems(movieList)
    }

    private fun setup() {
        val repository = MyRepository(MyAppDataBase.getDatabase(applicationContext).myDao())
        val viewModelFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MyViewModel::class.java]

        layoutManager = LinearLayoutManager(mContext)
        movieListAdapter = MovieListAdapter(mContext,movieList,movieItemClickListner)
        binding.recyclerMovieList.layoutManager = layoutManager
        binding.recyclerMovieList.addOnScrollListener(mScrollListener)
        binding.recyclerMovieList.adapter = movieListAdapter

        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext,R.color.colorPrimary))

        binding.swipeRefreshLayout.setOnRefreshListener(onRefreshListener)

    }

    private val mScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0) {
                val firstVisibleItem: Int = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    if (!loadMore) {
                        if (CommonUtils.isConnectingToInternet(mContext)){
                            loadMore = true
                            movieList.add(MovieDataClass(false, "",null, "", "", "", 0.0,"","","",false, 0.0, 0))
                            recyclerView.post { movieListAdapter!!.notifyItemInserted(movieList.size - 1) }
                            callMovieListApi(false)
                        }
                    }
                }
            }

        }
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        page = 1
        loadMore = false
        movieList.clear()
        movieListAdapter!!.notifyDataSetChanged()
        callMovieListApi(false)
    }

    private val movieItemClickListner = object : MovieItemClickListner{
        override fun onItemClick(dataObject: MovieDataClass) {
//            val bundle = Bundle()
//            bundle.putInt("movieId", id)
//            bundle.putInt("movieId", id)
//            intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
//            intent.putExtras(bundle)
//            startActivity(intent)
            val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
            intent.putExtra("EXTRA_PEOPLE", dataObject)
            startActivity(intent)
        }

    }
}