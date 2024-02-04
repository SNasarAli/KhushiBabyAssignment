package com.khushibaby.assignment.helper

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.khushibaby.assignment.R
import java.text.SimpleDateFormat

object CommonUtils {
    fun isConnectingToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

    fun changeDateFormat(date: String, inputFormat: String, outputFormat: String): String {
        val parser = SimpleDateFormat(inputFormat)
        val formatter = SimpleDateFormat(outputFormat)
        var output = formatter.format(parser.parse(date)!!)
        if (outputFormat == "dd MMM yy" && output.length == 9) {
            val str1 = output.substring(0, 7)
            val str2 = output.substring(7, 9)
            output = "$str1'$str2"
        }
        return output
    }

    fun getPercent(double: Double): Int {
            var percentage = 0
            percentage = ((double * 100/ 10).toInt());
        return percentage
    }

    fun showSnackbarWithoutView(string: String?, con: Context, color: Int) {
        (con as Activity).runOnUiThread {
            val snackbar = Snackbar.make(
                con.findViewById(android.R.id.content),
                string!!,
                Snackbar.LENGTH_LONG
            )
            val snakeBarView = snackbar.view
            snackbar.setTextMaxLines(4)
            when (color) {
                0 -> {
                    //-----color orange
                    snackbar.view.setBackgroundColor(Color.parseColor("#ffb93221"))
                    snackbar.setTextColor(ContextCompat.getColor(con, android.R.color.white))
                }
                1 -> {
                    //-----color green
                    snakeBarView.setBackgroundColor(Color.parseColor("#6FA063"))
                    snackbar.setTextColor(ContextCompat.getColor(con, android.R.color.white))
                }
                2 -> {
                    //-----color yellow
                    snakeBarView.setBackgroundColor(Color.parseColor("#ffd000"))
                    snackbar.setTextColor(ContextCompat.getColor(con, android.R.color.white))
                }
            }
            snackbar.show()
        }
    }

    fun loadImageWithGlide(imageView: ImageView?, url: String?, context: Context?) {
        val circularProgressDrawable = CircularProgressDrawable(context!!)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorFilter(
            ContextCompat.getColor(
                context,
                R.color.colorPrimary
            ), PorterDuff.Mode.SRC_IN
        )
        circularProgressDrawable.start()
        val sharedOptions: RequestOptions = RequestOptions()
            .placeholder(circularProgressDrawable)
            .error(R.mipmap.img_not_found)
        Glide.with(context)
            .load(url)
            .apply(sharedOptions)
            .into(imageView!!)
    }

    fun getMovieLength(runtime: Int?): String {
        var hrs = runtime?.div(60)
        var mins  = runtime?.rem(60)
        var time = ""
        if (mins != null) {
            if (mins <= 0){
                time = hrs.toString()+"Hr"+mins.toString()+"Min"
            }else{
                time = hrs.toString()+"Hr"
            }
        }
        return time
    }

}