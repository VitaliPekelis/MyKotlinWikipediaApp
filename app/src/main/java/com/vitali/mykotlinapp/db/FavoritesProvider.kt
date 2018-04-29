package com.vitali.mykotlinapp.db

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class FavoritesProvider: ContentProvider()
{
    companion object
    {
        val AUTHORITY = "com.vitali.mykotlinapp.db"
        val URI_FAVORITES = Uri.parse(
                "content://" + AUTHORITY + "/" + FavoritesEntity.TABLE_NAME)

        /** The match code for some items in the Favorites table.  */
        private val CODE_SENTENCE_DIR = 1

        /** The match code for an item in the Favorites table.  */
        private val CODE_SENTENCE_ITEM = 2

        /** The URI matcher.  */
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)
    }

    init
    {
        MATCHER.addURI(AUTHORITY, FavoritesEntity.TABLE_NAME, CODE_SENTENCE_DIR)
        MATCHER.addURI(AUTHORITY, FavoritesEntity.TABLE_NAME + "/*", CODE_SENTENCE_ITEM)
    }

    override fun onCreate(): Boolean
    {
        return true
    }

    override fun query(
            uri: Uri,
            projection: Array<out String>?,
            selection: String?,
            selectionArgs: Array<out String>?,
            sortOrder: String?): Cursor?
    {
        val code = MATCHER.match(uri)
        if(code == CODE_SENTENCE_ITEM || code == CODE_SENTENCE_DIR)
        {
            val context = context ?: return null

            val daoFavorites = WikiDatabase.getInstance(context).favorites()

            return if (code== CODE_SENTENCE_DIR)
            {
                daoFavorites.selectAll()
            }
            else /*CODE_SENTENCE_ITEM*/
            {
                daoFavorites.selectById(ContentUris.parseId(uri))
            }

        }
        else
            run { throw IllegalArgumentException("Unknown URI: $uri") }


    }

    override fun insert(uri: Uri?, contentValues: ContentValues): Uri?
    {
        when (MATCHER.match(uri))
        {
            CODE_SENTENCE_DIR -> {

                context?: return null

                val id = WikiDatabase.getInstance(context).favorites().insert(FavoritesEntity.fromContentValues(contentValues))
                context.contentResolver.notifyChange(uri, null)

                return  ContentUris.withAppendedId(uri, id)
            }

            CODE_SENTENCE_ITEM -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri)

            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    override fun update(uri: Uri?, contentValues: ContentValues, selection: String?, selectionArgs: Array<out String>?): Int
    {
        when(MATCHER.match(uri))
        {
            CODE_SENTENCE_DIR -> throw IllegalArgumentException("Invalid URI, cannot update without ID" + uri)

            CODE_SENTENCE_ITEM -> {
                context?: return 0

                val favoritesEntity =  FavoritesEntity.fromContentValues(contentValues)
                favoritesEntity.id = ContentUris.parseId(uri)

                val count = WikiDatabase.getInstance(context).favorites().update(favoritesEntity)

                context.contentResolver.notifyChange(uri, null)

                return count
            }

            else -> throw IllegalArgumentException("Unknown URI: "+uri)
        }

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int
    {
        when (MATCHER.match(uri))
        {
            CODE_SENTENCE_DIR -> throw IllegalArgumentException("Invalid URI, cannot update without ID" + uri)

            CODE_SENTENCE_ITEM ->  {
                context ?: return 0
                val count = WikiDatabase.getInstance(context).favorites().deleteById(ContentUris.parseId(uri))
                context.contentResolver.notifyChange(uri, null)

                return count
            }

            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    override fun getType(uri: Uri): String?
    {
        return when (MATCHER.match(uri))
        {
            CODE_SENTENCE_DIR -> "vnd.android.cursor.dir/" + AUTHORITY + "." + FavoritesEntity.TABLE_NAME

            CODE_SENTENCE_ITEM -> "vnd.android.cursor.item/" + AUTHORITY + "." + FavoritesEntity.TABLE_NAME

            else -> throw IllegalArgumentException("Unknowen URI: "+ uri)
        }

    }

}