package com.vitali.mykotlinapp.models

import android.os.Parcel
import android.os.Parcelable
import com.vitali.mykotlinapp.IRecyclerViewItemData
import com.vitali.mykotlinapp.global.AppConstants

data class WikiPage(val pageid: Int? = null,
                    val title: String? = null,
                    val fullurl: String? = null,
                    val thumbnail: WikiThumbnail? = null)
    : IRecyclerViewItemData, Parcelable
{
    constructor(source: Parcel) : this(
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readString(),
            source.readParcelable<WikiThumbnail>(WikiThumbnail::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(pageid)
        writeString(title)
        writeString(fullurl)
        writeParcelable(thumbnail, 0)
    }

    companion object
    {
        @JvmField
        val CREATOR: Parcelable.Creator<WikiPage> = object : Parcelable.Creator<WikiPage>
        {
            override fun createFromParcel(source: Parcel): WikiPage = WikiPage(source)
            override fun newArray(size: Int): Array<WikiPage?> = arrayOfNulls(size)
        }
    }

    //----------------------------------------------------------------------------------------------
    // IRecyclerViewItemData - implementation
    //----------------------------------------------------------------------------------------------
    override fun getItemType(): Int
    {
        return AppConstants.ARTICLE_ITEM
    }
}