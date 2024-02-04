package com.khushibaby.assignment.api

import android.util.Log
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khushibaby.assignment.helper.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
class ApiClient {

    companion object {

        private const val BASE_URL = Constant.BASE_URL
//        private const val BASE_API = "${BASE_URL}api/"

        private var retrofit: Retrofit? = null
        private var httpClient: OkHttpClient? = null
        private var gson: Gson? = null

        fun getClient() : ApiInterface {

            if (httpClient == null)
                httpClient = initOkHttp()

            if (gson == null)
                gson = GsonBuilder()
                    .setLenient()
                    .create()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create(gson!!))
                .build()

            return retrofit!!.create(ApiInterface::class.java)
        }


        private fun getUnsafeOkHttpClient() :OkHttpClient.Builder {
            val okHttpClient = OkHttpClient.Builder().apply {
                connectTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
            }
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts:  Array<TrustManager> = arrayOf(object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<out java.security.cert.X509Certificate>?,
                        authType: String?
                    ) {}
                    override fun checkServerTrusted(
                        chain: Array<out java.security.cert.X509Certificate>?,
                        authType: String?
                    ) {}
                    override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? = arrayOf()
                })

                // Install the all-trusting trust manager
                val  sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                if (trustAllCerts.isNotEmpty() &&  trustAllCerts.first() is X509TrustManager) {
                    okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts.first() as X509TrustManager)
                    okHttpClient.hostnameVerifier(HostnameVerifier { hostname, session -> true })
                }

                val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

                okHttpClient.addInterceptor(interceptor)
                okHttpClient.addInterceptor {
                        val request = it.request().newBuilder().apply {
                            addHeader("Accept", "application/json")
                            addHeader("Content-Type", "application/json")
                        }.build()
                        it.proceed(request)
                }

                return okHttpClient
            } catch (e: Exception) {
                return okHttpClient
            }
        }

        private fun initOkHttp() : OkHttpClient {

            val httpClient = OkHttpClient().newBuilder().apply {
                connectTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
            }

            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            httpClient.addInterceptor(interceptor)
            httpClient.addInterceptor {
                    val request = it.request().newBuilder().apply {
                        addHeader("Accept", "application/json")
                        addHeader("Content-Type", "application/json")
                    }.build()
                    it.proceed(request)
            }

            return httpClient.build()
        }


    }
}