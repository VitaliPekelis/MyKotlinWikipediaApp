package com.vitali.mykotlinapp.network

import android.net.Uri

object WikiUrl
{

    private const val schema = "https"
    private const val host = "en.wikipedia.org"


    private const val ACTION = "action"
    private const val QUERY = "query"
    private const val FORMAT_VERSION = "formatversion"
    private const val FORMAT_VERSION_VALUE = "2"

    private const val GENERATOR = "generator"
    private const val PREFIXSEARCH = "prefixsearch"
    private const val RANDOM = "random"
    const val GPS_SEARCH = "gpssearch"
    const val GPS_LIMIT = "gpslimit"
    const val GPS_OFFSET = "gpsoffset"

    private const val PROP = "prop"
    private const val PROP_VALUE = "pageimages|info"

    private const val PI_PROP = "piprop"
    private const val PI_PROP_VALUE = "thumbnail|url"

    private const val PI_THUMBSIZE = "pithumbsize"
    private const val PI_THUMBSIZE_VALUE = "200"

    const val PI_LIMIT = "pilimit"
    private const val WBPTTERMS = "wbptterms"
    private const val DESCRIPTION = "description"
    private const val FORMAT_ = "format"
    private const val JSON = "json"
    private const val INPROP = "inprop"
    private const val URL = "url"
    private const val GRNNAMESPACE = "grnnamespace"
    private const val GRNNAMESPACE_VALUE = "0"
    const val PRNLIMIT = "prnlimit"
    const val GRNLIMIT = "grnlimit"


    fun getBaseUrl(): String
    {
        return Uri.Builder().scheme(schema).encodedAuthority(host).toString()
    }


    /*fun getSearchUrl(term:String, skip: Int, take:Int) :String{
        val builder = Uri.Builder()
        builder.scheme(schema).encodedAuthority(host)

        builder.appendQueryParameter(ACTION, QUERY)
                .appendQueryParameter(FORMAT_VERSION, FORMAT_VERSION_VALUE)
                .appendQueryParameter(GENERATOR, PREFIXSEARCH)
                .appendQueryParameter(GPS_SEARCH, term)
                .appendQueryParameter(GPS_LIMIT, take.toString())
                .appendQueryParameter(GPS_OFFSET, skip.toString())
                .appendQueryParameter(PROP, PROP_VALUE)
                .appendQueryParameter(PI_PROP, PI_PROP_VALUE)
                .appendQueryParameter(PI_THUMBSIZE, PI_THUMBSIZE_VALUE)
                .appendQueryParameter(PI_LIMIT, take.toString())
                .appendQueryParameter(WBPTTERMS, DESCRIPTION)
                .appendQueryParameter(FORMAT_, JSON)
                .appendQueryParameter(INPROP, URL)


        val url = builder.toString()
        if(BuildConfig.DEBUG) Logger.logDebug("DEBUG", url)
        return url
    }*/

    /*fun getRandomUrl(tack: Int) : String{
        val builder = Uri.Builder()
        builder.scheme(schema).encodedAuthority(host)

        builder.appendQueryParameter(ACTION, QUERY)
                .appendQueryParameter(FORMAT_, JSON)
                .appendQueryParameter(FORMAT_VERSION, FORMAT_VERSION_VALUE)
                .appendQueryParameter(GENERATOR, RANDOM)
                .appendQueryParameter(GRNNAMESPACE, GRNNAMESPACE_VALUE)
                .appendQueryParameter(PROP, PROP_VALUE)
                .appendQueryParameter(PRNLIMIT, tack.toString())
                .appendQueryParameter(INPROP, URL)
                .appendQueryParameter(PI_THUMBSIZE, PI_THUMBSIZE_VALUE)


        val url = builder.toString()
        if(BuildConfig.DEBUG) Logger.logDebug("DEBUG", url)
        return url
    }*/
}