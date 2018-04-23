package com.vitali.mykotlinapp.articledetails

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.global.AppConstants
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity()
{

    private lateinit var mUrl: String

    override fun onCreate(savedInstanceState: Bundle?)
    {

        getArguments()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        initUi()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {

        when (item?.itemId)
        {
            android.R.id.home ->
            {
                finish()
            }
        }


        return true
    }

    override fun onBackPressed()
    {
        if (article_detail_web_v.canGoBack())
        {
            article_detail_web_v.goBack()
        }
        else
        {
            super.onBackPressed()
        }
    }

    private fun initUi()
    {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initWebView()
    }

    private fun initWebView()
    {
        article_detail_web_v.webViewClient = object : WebViewClient()
        {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean
            {
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)
            {
                super.onPageStarted(view, url, favicon)
                web_v_pb.show()
            }

            override fun onPageFinished(view: WebView?, url: String?)
            {
                web_v_pb.hide()
                super.onPageFinished(view, url)
            }
        }

        article_detail_web_v.loadUrl(mUrl)
    }

    private fun getArguments()
    {
        mUrl = intent.getStringExtra(AppConstants.URL_EXTRA) ?: ""
    }
}
