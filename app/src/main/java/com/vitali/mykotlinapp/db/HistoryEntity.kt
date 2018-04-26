package com.vitali.mykotlinapp.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.ContentValues

@Entity(tableName = HistoryEntity.TABLE_NAME)
data class HistoryEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(index= true, name = HistoryEntity.COLUMN_ID)
        var id: Long? = null,

        @ColumnInfo(name = HistoryEntity.COLUMN_TITLE)
        var title:String? = null,

        @ColumnInfo(name = HistoryEntity.COLUMN_URL)
        var url:String? = null,

        @ColumnInfo(name = HistoryEntity.COLUMN_THUMBNAIL)
        var thumbnail:String? = null)
{

    companion object
    {
        /** The name of the History table. */
        const val TABLE_NAME = "historytable"

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
         * @param values A {@link ContentValues} that at least contain {@link #COLUMN_TITLE}.
         * @return A newly created {@link HistoryEntity} instance.
         */
        fun fromContentValues(values: ContentValues): HistoryEntity{

            val historyEntity = HistoryEntity()

            if(values.containsKey(COLUMN_ID))
                historyEntity.id = values.getAsLong(COLUMN_ID)

            if(values.containsKey(COLUMN_TITLE))
                historyEntity.title = values.getAsString(COLUMN_TITLE)

            if(values.containsKey(COLUMN_URL))
                historyEntity.url = values.getAsString(COLUMN_URL)

            if(values.containsKey(COLUMN_THUMBNAIL))
                historyEntity.thumbnail = values.getAsString(COLUMN_THUMBNAIL)

            return historyEntity
        }

    }
}