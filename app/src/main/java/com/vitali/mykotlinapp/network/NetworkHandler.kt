package com.vitali.mykotlinapp.network

import com.vitali.mykotlinapp.models.WikiResult
import retrofit2.Call

object NetworkHandler {

    private val wikiApi by lazy {
        WikiApi.create()
    }



    fun getSearch(searchString: String, skip: Int, pilimit : Int = 1, take:Int) : Call<WikiResult>
    {
        return wikiApi.getSearch(searchString, skip, pilimit, take)
    }

    fun getRandom(take:Int) : Call<WikiResult>
    {
        return wikiApi.getRandom(take)
    }


    /*
    val controlers = mutableMapOf<String, BaseController>()

    fun <T : BaseController> getController(iControllerClass: Class<T>): T {
        var controller: T? = controlers[iControllerClass.simpleName] as? T

        if(controller == null) {
            controller = iControllerClass.newInstance()
            controlers[iControllerClass.simpleName] = controller
        }

        return controller!!
    }*/
}