package com.vitali.mykotlinapp.models

import android.os.Parcel
import android.os.Parcelable

data class WikiThumbnail(val source: String? = null,
                         val width: Int = 0,
                         val height: Int = 0)
    : Parcelable
{
    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(source)
        writeInt(width)
        writeInt(height)
    }

    companion object
    {
        @JvmField
        val CREATOR: Parcelable.Creator<WikiThumbnail> = object : Parcelable.Creator<WikiThumbnail>
        {
            override fun createFromParcel(source: Parcel): WikiThumbnail = WikiThumbnail(source)
            override fun newArray(size: Int): Array<WikiThumbnail?> = arrayOfNulls(size)
        }
    }
}