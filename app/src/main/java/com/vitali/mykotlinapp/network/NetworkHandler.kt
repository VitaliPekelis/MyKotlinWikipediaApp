package com.vitali.mykotlinapp.network

import com.vitali.mykotlinapp.models.WikiResult
import retrofit2.Call

object NetworkHandler {

    private val wikiApi by lazy {
        WikiApi.create()
    }



    fun nameFun(searchString: String, skip: Int, pilimit : Int, take:Int) : Call<WikiResult>
    {
        return wikiApi.getSearch(searchString, skip, pilimit, take)
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