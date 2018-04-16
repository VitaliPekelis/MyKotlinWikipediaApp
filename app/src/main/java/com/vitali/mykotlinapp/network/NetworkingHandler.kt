package com.vitali.mykotlinapp.network


object NetworkingHandler {

    val controlers = mutableMapOf<String, BaseController>()

    @Throws(IllegalAccessException::class, InstantiationException::class)
    fun <T : BaseController> getController(iControllerClass: Class<T>): T {
        val controler: T? = controlers.get(iControllerClass.simpleName) as? T

        val result = controler?: iControllerClass.newInstance(); result
        controlers.put(iControllerClass.simpleName, result)

        return result
    }


}
