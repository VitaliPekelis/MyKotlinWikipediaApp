package com.vitali.mykotlinapp.articledetails

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.db.DataBaseWorkingThread
import com.vitali.mykotlinapp.db.WikiDatabase
import com.vitali.mykotlinapp.global.AppConstants
import com.vitali.mykotlinapp.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity()
{
    private var mPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        getArguments()

        super.onCreate(savedInstanceState)
        mPage ?: finish()

        setContentView(R.layout.activity_article_detail)
        initUi()

        mPage?.let {

            DataBaseWorkingThread(object: DataBaseWorkingThread.IExecutor<Long>
            {
                override fun doInBackground(): Long
                {
                    return WikiDatabase.getInstance(this@ArticleDetailActivity).addHistory(mPage!!)
                }

                override fun onPostExecute(response: Long)
                {
                    //do nothing
                }
            }).execute()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            android.R.id.home ->
            {
                finish()
            }

            R.id.action_favorite ->
            {
                onActionFavoriteClick()
                return true
            }
        }

        return true
    }

    private fun onActionFavoriteClick()
    {
        try
        {
            val db = WikiDatabase.getInstance(this)

            DataBaseWorkingThread(object :DataBaseWorkingThread.IExecutor<Boolean>{
                override fun doInBackground(): Boolean
                {
                    return db.isFavorite(mPage!!.pageid!!)
                }

                override fun onPostExecute(response: Boolean)
                {
                    if(response)
                        removeFavorite(db)
                    else
                    {
                        addFavorite(db)
                    }
                }
            }).execute()
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            Toast.makeText(this,"Unable to update this Article", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addFavorite(db: WikiDatabase)
    {
        DataBaseWorkingThread(object :DataBaseWorkingThread.IExecutor<Boolean>
        {
            override fun doInBackground(): Boolean
            {
                return db.addFavorite(mPage!!)
            }

            override fun onPostExecute(response: Boolean)
            {
                if(response)
                    Toast.makeText(this@ArticleDetailActivity,"Article added to favorites", Toast.LENGTH_SHORT).show()
            }

        }).execute()
    }

    private fun removeFavorite(db: WikiDatabase)
    {
        DataBaseWorkingThread(object :DataBaseWorkingThread.IExecutor<Boolean>{
            override fun doInBackground(): Boolean
            {
                return db.removeFavorite(mPage!!.pageid!!)
            }

            override fun onPostExecute(response: Boolean)
            {
                if(response)
                    Toast.makeText(this@ArticleDetailActivity,"Article removed from favorites", Toast.LENGTH_SHORT).show()
            }

        }).execute()
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
        supportActionBar!!.title = mPage?.title
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

        article_detail_web_v.loadUrl(mPage?.fullurl)
    }

    private fun getArguments()
    {
        mPage = intent.getParcelableExtra(AppConstants.PAGE_EXTRA) as? WikiPage
    }
}
