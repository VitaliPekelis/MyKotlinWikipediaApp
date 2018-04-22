package com.vitali.mykotlinapp.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vitali.mykotlinapp.IRecyclerViewItemData
import com.vitali.mykotlinapp.R

class ArticleCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //private var data: IRecyclerViewItemData

    var currentData: ArrayList<out IRecyclerViewItemData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.item_card_article, parent, false)
        return ArticleCardViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return 15 //temporary
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {

        return 0
    }


    class ArticleCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val articleCardIV: ImageView = itemView.findViewById<ImageView>(R.id.card_article_iv)
        private val articleCardTV: TextView = itemView.findViewById<TextView>(R.id.card_article_tv)

    }

}