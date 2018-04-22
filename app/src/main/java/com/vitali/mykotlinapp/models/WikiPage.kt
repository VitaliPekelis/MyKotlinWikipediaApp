package com.vitali.mykotlinapp.models

import com.vitali.mykotlinapp.IRecyclerViewItemData
import com.vitali.mykotlinapp.global.AppConstants

class WikiPage : IRecyclerViewItemData {
    val pageid: Int? =null
    val title:String? =null
    val fullurl:String? =null
    val thumbnail:WikiThumbnail? =null

    //----------------------------------------------------------------------------------------------
    // IRecyclerViewItemData - implementation
    //----------------------------------------------------------------------------------------------
    override fun getItemType(): Int {
        return AppConstants.ARTICLE_ITEM
    }
}