package com.vitali.mykotlinapp.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        ExploreFragment.OnFragmentInteractionListener,
        FavoritesFragment.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener
{

    private val exploreFragment: ExploreFragment
    private val favoritesFragment: FavoritesFragment
    private val historyFragment: HistoryFragment

    init
    {
        exploreFragment = ExploreFragment.newInstance()
        favoritesFragment = FavoritesFragment.newInstance()
        historyFragment = HistoryFragment.newInstance()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (item.itemId)
        {

        /*R.id.explore_menu_item -> {
            //message_tv.setText(R.string.title_explore)
            return@OnNavigationItemSelectedListener true
        }
        R.id.favorite_menu_item -> {
            //message_tv.setText(R.string.title_favorites)
            return@OnNavigationItemSelectedListener true
        }
        R.id.history_menu_item -> {
            //message_tv.setText(R.string.title_history)
            return@OnNavigationItemSelectedListener true
        }*/


            R.id.explore_menu_item -> transaction.replace(R.id.fragment_container, exploreFragment)
            R.id.favorite_menu_item -> transaction.replace(R.id.fragment_container, favoritesFragment)
            R.id.history_menu_item -> transaction.replace(R.id.fragment_container, historyFragment)
        }

        transaction.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        //message_tv.setOnClickListener{ _ -> startActivity(Intent(this, ArticleDetailActivity::class.java))
        //   /*startActivity(Intent(this, SearchActivity::class.java))*/}

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, exploreFragment)
        transaction.commit()
    }

    //----------------------------------------------------------------------------------------------
    // HistoryFragment listener - implementation
    //----------------------------------------------------------------------------------------------
    override fun onHistoryFragmentInteraction()
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //----------------------------------------------------------------------------------------------
    // ExploreFragment listener - implementation
    //----------------------------------------------------------------------------------------------
    override fun onExploreFragmentInteraction()
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchClick()
    {
        val searchIntent = Intent(this@MainActivity, SearchActivity::class.java)
        startActivity(searchIntent)
    }

    //----------------------------------------------------------------------------------------------
    // FavoritesFragment listener - implementation
    //----------------------------------------------------------------------------------------------
    override fun onFavoritesFragmentInteraction()
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
