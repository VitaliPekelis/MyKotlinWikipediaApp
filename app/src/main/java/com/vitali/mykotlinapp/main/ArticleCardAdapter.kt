package com.vitali.mykotlinapp.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.vitali.mykotlinapp.IRecyclerViewItemData
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.global.AppConstants
import com.vitali.mykotlinapp.models.WikiPage

class ArticleCardAdapter(val listener: IAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    //private var data: IRecyclerViewItemData

    var currentData: ArrayList<out IRecyclerViewItemData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {

        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType)
        {
            AppConstants.ARTICLE_ITEM ->
            {
                ArticleCardViewHolder(layoutInflater.inflate(R.layout.item_card_article, parent, false), listener)
            }

        /*1 -> {

        }*/

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun getItemCount(): Int
    {
        return currentData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {

        when (holder)
        {
            is ArticleCardViewHolder ->
            {
                val item = currentData[holder.adapterPosition] as? WikiPage
                onBindArticleCardViewHolder(holder, item)
            }
        }

    }

    private fun onBindArticleCardViewHolder(h: ArticleCardViewHolder, item: WikiPage?)
    {
        item?.let {
            h.articleCardTV.text = item.title

            if (item.thumbnail != null)
            {
                Picasso.with(h.itemView.context).load(item.thumbnail.source).into(h.articleCardIV)
            }
            else
            {
                h.articleCardIV.setImageResource(R.drawable.ic_dashboard_black_24dp)
            }

            h.updatePage(it)
        }
    }

    override fun getItemViewType(position: Int): Int
    {

        return currentData[position].getItemType()
    }


    class ArticleCardViewHolder(itemView: View, listener: IAdapterListener) : RecyclerView.ViewHolder(itemView)
    {
        val articleCardIV: ImageView = itemView.findViewById<ImageView>(R.id.card_article_iv)
        val articleCardTV: TextView = itemView.findViewById<TextView>(R.id.card_article_tv)
        private var currentPage: WikiPage? = null

        init
        {
            itemView.setOnClickListener { _ ->
                currentPage?.let {
                    listener.clickOnItem(it)
                }
            }
        }

        fun updatePage(page: WikiPage?)
        {
            currentPage = page
        }
    }

}

interface IAdapterListener
{
    fun clickOnItem(page: WikiPage)
}
