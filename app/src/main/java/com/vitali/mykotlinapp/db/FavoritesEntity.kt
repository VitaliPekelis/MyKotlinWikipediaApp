package com.vitali.mykotlinapp.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.ContentValues
import com.vitali.mykotlinapp.models.WikiPage

@Entity(tableName = FavoritesEntity.TABLE_NAME)
data class FavoritesEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(index= true, name = FavoritesEntity.COLUMN_ID)
        var id: Long? = null,

        @ColumnInfo(name = FavoritesEntity.COLUMN_PAGE_ID)
        var pageId:Long? = null,

        @ColumnInfo(name = FavoritesEntity.COLUMN_TITLE)
        var title:String? = null,

        @ColumnInfo(name = FavoritesEntity.COLUMN_URL)
        var url:String? = null,

        @ColumnInfo(name = FavoritesEntity.COLUMN_THUMBNAIL)
        var thumbnail:String? = null)
{

    companion object
    {
        /** The name of the Favorites table. */
        const val TABLE_NAME = "favoritestable"

        /** The name of the ID column.  */
        const val COLUMN_ID = "id"
        /** The name of the PAGE ID column.  */
        const val COLUMN_PAGE_ID = "page_id"
        /**The name of the title column*/
        const val COLUMN_TITLE = "title"
        /**The name of the url column*/
        const val COLUMN_URL = "url"
        /**The name of the thumbnail column*/
        const val COLUMN_THUMBNAIL = "thumbnail"


        /**
         * Create a new {@link HistoryEntity} from the specified {@link ContentValues}.
         *
         * @param contentValues A {@link ContentValues} that at least contain {@link #COLUMN_TITLE}.
         * @return A newly created {@link HistoryEntity} instance.
         */
        fun fromContentValues(contentValues: ContentValues): FavoritesEntity{

            val favoritesEntity = FavoritesEntity()

            if(contentValues.containsKey(FavoritesEntity.COLUMN_ID))
                favoritesEntity.id = contentValues.getAsLong(FavoritesEntity.COLUMN_ID)

            if(contentValues.containsKey(FavoritesEntity.COLUMN_PAGE_ID))
                favoritesEntity.pageId = contentValues.getAsLong(FavoritesEntity.COLUMN_PAGE_ID)

            if(contentValues.containsKey(FavoritesEntity.COLUMN_TITLE))
                favoritesEntity.title = contentValues.getAsString(FavoritesEntity.COLUMN_TITLE)

            if(contentValues.containsKey(FavoritesEntity.COLUMN_URL))
                favoritesEntity.url = contentValues.getAsString(FavoritesEntity.COLUMN_URL)

            if(contentValues.containsKey(FavoritesEntity.COLUMN_THUMBNAIL))
                favoritesEntity.thumbnail = contentValues.getAsString(FavoritesEntity.COLUMN_THUMBNAIL)

            return favoritesEntity
        }

        fun fromWikiPage(page: WikiPage): FavoritesEntity
        {
            val favoritesEntity = FavoritesEntity()

            page.pageid?.let{ favoritesEntity.pageId = page.pageid}
            page.title?.let{ favoritesEntity.title = page.title}
            page.fullurl?.let{ favoritesEntity.url = page.fullurl}
            page.thumbnail?.source.let{  favoritesEntity.thumbnail = page.thumbnail?.source}

            return favoritesEntity
        }
    }
}