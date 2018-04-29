package com.vitali.mykotlinapp.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.ContentValues

@Entity(tableName = FavoritesEntity.TABLE_NAME)
data class FavoritesEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(index= true, name = FavoritesEntity.COLUMN_ID)
        var id: Long? = null,

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

            if(contentValues.containsKey(HistoryEntity.COLUMN_ID))
                favoritesEntity.id = contentValues.getAsLong(FavoritesEntity.COLUMN_ID)

            if(contentValues.containsKey(HistoryEntity.COLUMN_TITLE))
                favoritesEntity.title = contentValues.getAsString(FavoritesEntity.COLUMN_TITLE)

            if(contentValues.containsKey(HistoryEntity.COLUMN_URL))
                favoritesEntity.url = contentValues.getAsString(FavoritesEntity.COLUMN_URL)

            if(contentValues.containsKey(HistoryEntity.COLUMN_THUMBNAIL))
                favoritesEntity.thumbnail = contentValues.getAsString(FavoritesEntity.COLUMN_THUMBNAIL)

            return favoritesEntity
        }

    }


}