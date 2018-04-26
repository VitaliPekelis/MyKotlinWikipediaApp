package com.vitali.mykotlinapp.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.articledetails.ArticleDetailActivity
import com.vitali.mykotlinapp.global.AppConstants
import com.vitali.mykotlinapp.main.IAdapterListener
import com.vitali.mykotlinapp.main.SearchArticlesAdapter
import com.vitali.mykotlinapp.models.WikiResult
import com.vitali.mykotlinapp.network.NetworkHandler
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(), IAdapterListener
{

    private val adapter: SearchArticlesAdapter by lazy {
        SearchArticlesAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {

        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu!!.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {

            override fun onQueryTextSubmit(query: String): Boolean
            {
                getSearchArticles(query)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }


    private fun initUi()
    {
        setSupportActionBar(search_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        search_rv.layoutManager = LinearLayoutManager(this)
        search_rv.adapter = adapter

    }

    private fun getSearchArticles(query: String)
    {
        NetworkHandler.getSearch(query, 0, 1, 15).enqueue(object : Callback<WikiResult>
        {
            override fun onResponse(call: Call<WikiResult>?, response: Response<WikiResult>?)
            {

                response?.body()?.query?.pages?.let {
                    adapter.currentData.clear()
                    adapter.currentData = it
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<WikiResult>?, t: Throwable?)
            {
                Snackbar.make(search_rv, "An error occurred", Snackbar.LENGTH_SHORT).show()
            }

        })
    }

    //----------------------------------------------------------------------------------------------
    // IAdapterListener - implementation
    //----------------------------------------------------------------------------------------------
    override fun clickOnItem(url: String)
    {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra(AppConstants.URL_EXTRA, url)
        startActivity(intent)
    }
}
