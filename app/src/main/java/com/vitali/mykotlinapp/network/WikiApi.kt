package com.vitali.mykotlinapp.network

import com.vitali.mykotlinapp.models.WikiResult
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface  WikiApi{

    @GET("/w/api.php?action=query&formatversion=2&prop=pageimages|info&generator=prefixsearch&wbptterms=description&format=json&inprop=url")
    fun getSearch(
            @Query(WikiUrl.GPS_SEARCH) term:String,
            @Query(WikiUrl.GPS_OFFSET) skip: Int,
            @Query(WikiUrl.PI_LIMIT) pilimit: Int,
            @Query(WikiUrl.GPS_LIMIT) take:Int) : Call<WikiResult>

    @GET("/w/api.php?action=query&formatversion=2&generator=random&grnnamespace=0&prop=pageimages|info&prnlimit&inprop=url&pithumbsize=200")
    fun getRandom(@Query(WikiUrl.PRNLIMIT) tack: Int) : String



    companion object {
        fun create() : WikiApi {
            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(20000, TimeUnit.MILLISECONDS)
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
