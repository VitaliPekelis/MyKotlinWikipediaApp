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

            val daoFavorites = WikiDatabase.getInstance(context)?.favorites()

            return if (code== CODE_SENTENCE_DIR)
            {
                daoFavorites?.selectAll()
            }
            else /*CODE_SENTENCE_ITEM*/
            {
                daoFavorites?.selectById(ContentUris.parseId(uri))
            }

        }
        else
            run { throw IllegalArgumentException("Unknown URI: $uri") }


    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri?): String
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}