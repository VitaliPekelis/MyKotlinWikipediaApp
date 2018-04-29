package com.vitali.mykotlinapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.database.Cursor

@Dao
interface HistoriesDao
{
    /**
     * Counts the number of histories in the table.
     *
     * @return The number of histories.
     */
    @Query("SELECT COUNT(*) FROM " + HistoryEntity.TABLE_NAME)
    fun count(): Int

    /**
     * Inserts a histories into the table.
     *
     * @param history A new History.
     * @return The row ID of the newly inserted history.
     */
    @Insert
    fun insert(history: HistoryEntity): Long


    /**
     * Inserts multiple histories into the database
     *
     * @param histories An array of new histories.
     * @return The row IDs of the newly inserted histories.
     */
    @Insert
    fun insertAll(histories: Array<HistoryEntity>): LongArray

    /**
     * Select all favorites.
     *
     * @return A [Cursor] of all the histories in the table.
     */
    @Query("SELECT * FROM " + HistoryEntity.TABLE_NAME + " ORDER BY " + HistoryEntity.COLUMN_ID + " DESC")
    fun selectAll(): Cursor

    /**
     * Select all favorites.
     *
     * @return A [List<HistoryEntity>] of all the histories in the table.
     */
    @Query("SELECT * FROM " + HistoryEntity.TABLE_NAME + " ORDER BY " + HistoryEntity.COLUMN_ID)
    fun selectAllFavorites(): List<HistoryEntity>


    /**
     * Delete a favorite by the ID.
     *
     * @param id The row ID.
     * @return A number of histories deleted. This should always be `1`.
     */
    @Query("DELETE FROM " + HistoryEntity.TABLE_NAME + " WHERE " + HistoryEntity.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int


    /**
     * Select a favorite by the ID.
     *
     * @param id The row ID.
     * @return A [Cursor] of the selected histories.
     */
    @Query("SELECT * FROM " + HistoryEntity.TABLE_NAME + " WHERE " + HistoryEntity.COLUMN_ID + " = :id")
    fun selectById(id: Long): Cursor


    /**
     * Update the favorite. The histories is identified by the row ID.
     *
     * @param histories The histories to update.
     * @return A number of sentences updated. This should always be `1`.
     */
    @Update
    fun update(histories: HistoryEntity): Int

    @Query("DELETE FROM " + HistoryEntity.TABLE_NAME)
    fun cleanTable()
}