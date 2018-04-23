package com.vitali.mykotlinapp.global

import android.app.Application

class MyApp : Application()
{

    companion object
    {
        lateinit var app: MyApp
    }

    override fun onCreate()
    {
        super.onCreate()
        app = this
    }
}