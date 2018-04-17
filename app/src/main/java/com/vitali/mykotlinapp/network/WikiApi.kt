package com.vitali.mykotlinapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


interface  WikiApi{

    companion object{



        fun create() : WikiApi {

            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(20000, TimeUnit.MILLISECONDS)
                    /*ADD MORE settings*/
                    .build()





            val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            return retrofit.create(WikiApi::class.java)
        }
    }

    /*val controlers = mutableMapOf<String, BaseController>()

    fun <T : BaseController> getController(iControllerClass: Class<T>): T {
        var controller: T? = controlers[iControllerClass.simpleName] as? T

        if(controller == null) {
            controller = iControllerClass.newInstance()
            controlers[iControllerClass.simpleName] = controller
        }

        return controller!!
    }*/


}
