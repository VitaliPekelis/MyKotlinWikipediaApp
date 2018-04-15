package com.vitali.mykotlinapp.network

import android.net.Uri
import com.vitali.mykotlinapp.BuildConfig
import com.vitali.mykotlinapp.Logger

object RequestStringBuilder {

    private const val schema = "https"
    private const val host = "en.wikipedia.org/w/api.php"


    private const val ACTION = "action"
    private const val QUERY = "query"
    private const val FORMAT_VERSION = "formatversion"
    private const val FORMAT_VERSION_VALUE = "2"

    private const val GENERATOR = "generator"
    private const val PREFIXSEARCH = "prefixsearch"
    private const val GPS_SEARCH = "gpssearch"
    private const val GPS_LIMIT = "gpslimit"
    private const val GPS_OFFSET = "gpsoffset"

    private const val PROP = "PROP"
    private const val PROP_VALUE = "pageimages|info"

    private const val PI_PROP = "piprop"
    private const val PI_PROP_VALUE = "thumbnail|url"


    fun getSearchUrl(term:String, skip: Int, take:Int) :String{
        val builder = Uri.Builder()
        builder.scheme(schema).encodedAuthority(host)

        builder.appendQueryParameter(ACTION, QUERY)
        builder.appendQueryParameter(FORMAT_VERSION, FORMAT_VERSION_VALUE)
        builder.appendQueryParameter(GENERATOR, PREFIXSEARCH)
        builder.appendQueryParameter(GPS_SEARCH, term)


        builder.appendQueryParameter(GPS_LIMIT, take.toString())
        builder.appendQueryParameter(GPS_OFFSET, skip.toString())

        builder.appendQueryParameter(PROP, PROP_VALUE)
        builder.appendQueryParameter(PI_PROP, PI_PROP_VALUE)


        val url = builder.toString()
        if(BuildConfig.DEBUG) Logger.logDebug("DEBUG", url)
        return url
    }
}