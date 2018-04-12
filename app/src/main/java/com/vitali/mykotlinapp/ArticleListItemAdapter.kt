package com.vitali.mykotlinapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ArticleListItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //private var data: IRecyclerViewItemData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_article, parent, false)
        return ArticleListItemViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return 15 //temporary
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {

        return 0
    }


    class ArticleListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val articleCardIV: ImageView = itemView.findViewById<ImageView>(R.id.article_iv)
        private val articleCardTV: TextView = itemView.findViewById<TextView>(R.id.article_tv)

    }

}