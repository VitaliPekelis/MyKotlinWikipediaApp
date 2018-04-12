package com.vitali.mykotlinapp.articledetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.vitali.mykotlinapp.R
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        initUi()
    }

    private fun initUi() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId)
        {
            android.R.id.home -> {
                finish()
            }
        }


        return true
    }
}
