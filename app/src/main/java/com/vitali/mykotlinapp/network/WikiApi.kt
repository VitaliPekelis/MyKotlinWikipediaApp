package com.vitali.mykotlinapp.network

import com.vitali.mykotlinapp.models.WikiResult
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface  WikiApi{

    @GET("/w/api.php?format=json&action=query&formatversion=2&generator=prefixsearch&prop=pageimages|info&wbptterms=description&inprop=url&pithumbsize=200")
    fun getSearch(
            @Query(WikiUrl.GPS_SEARCH) term:String,
            @Query(WikiUrl.GPS_OFFSET) skip: Int,
            @Query(WikiUrl.PI_LIMIT) pilimit: Int,
            @Query(WikiUrl.GPS_LIMIT) take:Int) : Call<WikiResult>

    @GET("/w/api.php?format=json&action=query&formatversion=2&grnnamespace=0&generator=random&prop=pageimages|info&inprop=url&pithumbsize=200")
    fun getRandom(@Query(WikiUrl.GRNLIMIT) take:Int) : Call<WikiResult>



    companion object {
        fun create() : WikiApi {

            /*header to all requests*/
            val requestInterceptor = Interceptor { chain ->
                val request = chain.request()?.newBuilder()!!.addHeader("User-Agent", "VitaliKotlinWikipedia")?.build()
                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(20000, TimeUnit.MILLISECONDS)
                    .addInterceptor(LogJsonInterceptor())
                    .addInterceptor(requestInterceptor)
                    /*ADD MORE settings*/
                    .build()


             val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(WikiUrl.getBaseUrl())
                    .build()

            return retrofit.create(WikiApi::class.java)
        }
    }
}
