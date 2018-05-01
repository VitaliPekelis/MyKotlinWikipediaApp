package com.vitali.mykotlinapp.global

import com.vitali.mykotlinapp.db.FavoritesEntity
import com.vitali.mykotlinapp.db.HistoryEntity
import com.vitali.mykotlinapp.models.WikiPage
import com.vitali.mykotlinapp.models.WikiThumbnail


fun List<FavoritesEntity>.favoritesEntityToWikiPages(): ArrayList<WikiPage>
{
    val result:ArrayList<WikiPage> = arrayListOf()

    forEach {
        result.add(WikiPage(it.pageId, it.title, it.url, WikiThumbnail(it.thumbnail)))
    }

    return result
}

fun List<HistoryEntity>.historiesEntityToWikiPages(): ArrayList<WikiPage>
{
    val result:ArrayList<WikiPage> = arrayListOf()

    forEach {
        result.add(WikiPage(it.pageId, it.title, it.url, WikiThumbnail(it.thumbnail)))
    }

    return result
}
