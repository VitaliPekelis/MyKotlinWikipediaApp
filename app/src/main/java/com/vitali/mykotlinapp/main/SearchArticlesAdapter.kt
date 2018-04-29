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

class SearchArticlesAdapter(val listener: IAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    var currentData: ArrayList<out IRecyclerViewItemData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {

        val layoutInflater = LayoutInflater.from(parent?.context)

        return when (viewType)
        {
            AppConstants.ARTICLE_ITEM ->
            {
                ArticleListItemViewHolder(layoutInflater.inflate(R.layout.item_list_article, parent, false),
                        listener)
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
            is ArticleListItemViewHolder ->
            {
                val item = currentData[holder.adapterPosition] as? WikiPage
                onBindArticleViewHolder(holder, item)
            }
        }
    }

    private fun onBindArticleViewHolder(holder: ArticleListItemViewHolder, item: WikiPage?)
    {
        item?.let {
            holder.articleCardTV.text = item.title

            if (item.thumbnail != null)
            {
                Picasso.with(holder.itemView.context).load(item.thumbnail.source).into(holder.articleCardIV)
            }
            else
            {
                holder.articleCardIV.setImageResource(R.drawable.ic_notifications_black_24dp)
            }

            holder.updateUrl(item)
        }
    }

    override fun getItemViewType(position: Int): Int
    {

        return currentData[position].getItemType()
    }


    class ArticleListItemViewHolder(itemView: View, listener: IAdapterListener) : RecyclerView.ViewHolder(itemView)
    {
        val articleCardIV: ImageView = itemView.findViewById<ImageView>(R.id.article_iv)
        val articleCardTV: TextView = itemView.findViewById<TextView>(R.id.article_tv)
        private var currentPage: WikiPage? = null

        init
        {
            itemView.setOnClickListener { _ ->
                currentPage?.let {
                    listener.clickOnItem(it)
                }
            }
        }
        fun updateUrl(page: WikiPage?)
        {
            currentPage = page
        }
    }

}