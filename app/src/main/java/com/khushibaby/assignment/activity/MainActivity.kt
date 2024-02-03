package com.khushibaby.assignment.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.khushibaby.assignment.R
import com.khushibaby.assignment.adapter.MovieListAdapter
import com.khushibaby.assignment.databinding.ActivityMainBinding
import com.khushibaby.assignment.listner.MovieItemClickListner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context
    private lateinit var layoutManager: LinearLayoutManager
    private var movieListAdapter: MovieListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        layoutManager = LinearLayoutManager(mContext)
        movieListAdapter = MovieListAdapter(mContext,movieItemClickListner)
        binding.recyclerMovieList.layoutManager = layoutManager
        binding.recyclerMovieList.addOnScrollListener(mScrollListener)
        binding.recyclerMovieList.adapter = movieListAdapter

        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext,R.color.colorPrimary))

        binding.swipeRefreshLayout.setOnRefreshListener(onRefreshListener)

        binding.shimmerEffect.shimmerLayout.visibility = View.GONE
    }

    private val mScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

//            if (dy <= 0 || loadMore || notificationList.isEmpty()) {
//                Log.e("DY","-----------$dy")
//                Log.e("DY","-----------$loadMore")
//                return
//            }
//
//            val firstItem = layoutManager.findFirstVisibleItemPosition()
//            Log.e("firstItem","-----------$firstItem")
//            Log.e("child count","-----------"+layoutManager.childCount)
//            if (firstItem + layoutManager.childCount == layoutManager.itemCount) {
//                loadMore = true
//
//                notificationList.add(NotificationListData(-1, -1, -1, "", "", "","","","",""))
//                notificationAdapter!!.notifyItemInserted(notificationList.size)
//
//                callNotificationListApi(false)
//            }

            if (dy > 0) {
                val firstVisibleItem: Int = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
//                    if (!loadMore) {
//                        loadMore = true
//                        notificationList.add(NotificationListData(-1, -1, -1, "", "", "","","","",""))
//                        recyclerView.post { notificationAdapter!!.notifyItemInserted(notificationList.size - 1) }
//                        callNotificationListApi(false)
//                    }
                }
            }

        }
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
//        page = 0
//        loadMore = false
//        notificationList.clear()
//        notificationAdapter!!.notifyDataSetChanged()
//        binding.lytBottom.visibility = View.INVISIBLE
//        callNotificationListApi(false)
    }

    private val movieItemClickListner = object : MovieItemClickListner{
        override fun onItemClick(a: Int) {
//            val bundle = Bundle()
//            bundle.putString("loanId", notificationList[a].loanId.toString())
//            bundle.putString("loanAmount", notificationList[a].repaymentAmount)
//            bundle.putString("repayDate", commonUtils.changeDateFormat(notificationList[a].repaymentDate!!,"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"))
//            bundle.putString("upiId", upiId)
//            bundle.putString("upiName", upiName)
//            val fragPayNow = FragPayNow()
//            fragPayNow.arguments = bundle
//            (mContext as HomeActivity).navigateTo(fragPayNow)
            val intent = Intent(mContext, MovieDetailActivity::class.java)
            startActivity(intent)
        }

    }
}