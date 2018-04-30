package com.vitali.mykotlinapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.database.Cursor

@Dao //Data Access Object
interface FavoritesDao
{

    /**
     * Counts the number of favorites in the table.
     *
     * @return The number of sentences.
     */
    @Query("SELECT COUNT(*) FROM " + FavoritesEntity.TABLE_NAME)
    fun count(): Int

    /**
     * Inserts a favorite into the table.
     *
     * @param favorite A new Favorite.
     * @return The row ID of the newly inserted favorite.
     */
    @Insert
    fun insert(favorite: FavoritesEntity): Long


    /**
     * Inserts multiple favorites into the database
     *
     * @param favorites An array of new favorites.
     * @return The row IDs of the newly inserted sentences.
     */
    @Insert
    fun insertAll(favorites: Array<FavoritesEntity>): LongArray

    /**
     * Select all favorites.
     *
     * @return A [Cursor] of all the favorites in the table.
     */
    @Query("SELECT * FROM " + FavoritesEntity.TABLE_NAME + " ORDER BY " + FavoritesEntity.COLUMN_ID + " DESC")
    fun selectAll(): Cursor

    /**
     * Select all favorites.
     *
     * @return A [List<FavoritesEntity>] of all the favorites in the table.
     */
    @Query("SELECT * FROM " + FavoritesEntity.TABLE_NAME + " ORDER BY " + FavoritesEntity.COLUMN_ID)
    fun selectAllFavorites(): List<FavoritesEntity>


    /**
     * Delete a favorite by the ID.
     *
     * @param id The row ID.
     * @return A number of favorite deleted. This should always be `1`.
     */
    @Query("DELETE FROM " + FavoritesEntity.TABLE_NAME + " WHERE " + FavoritesEntity.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int

    /**
     * Delete a favorite by the ID.
     *
     * @param pageid The row PAGE_ID.
     * @return A number of favorite deleted. This should always be `1`.
     */
    @Query("DELETE FROM " + FavoritesEntity.TABLE_NAME + " WHERE " + FavoritesEntity.COLUMN_PAGE_ID +" = :pageid")
    fun deleteByPageId(pageid: Long): Int

    /**
     * Select a favorite by the ID.
     *
     * @param id The row ID.
     * @return A [Cursor] of the selected favorite.
     */
    @Query("SELECT * FROM " + FavoritesEntity.TABLE_NAME + " WHERE " + FavoritesEntity.COLUMN_ID + " = :id")
    fun selectById(id: Long): Cursor


    /**
     * Update the favorite. The favorite is identified by the row ID.
     *
     * @param favorite The favorite to update.
     * @return A number of sentences updated. This should always be `1`.
     */
    @Update
    fun update(favorite: FavoritesEntity): Int

    @Query("DELETE FROM " + FavoritesEntity.TABLE_NAME)
    fun cleanTable()
}