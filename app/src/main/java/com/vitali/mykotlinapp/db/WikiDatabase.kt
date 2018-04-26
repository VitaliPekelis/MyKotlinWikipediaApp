package com.vitali.mykotlinapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [FavoritesEntity::class, HistoryEntity::class], version = 1, exportSchema = false)
abstract class WikiDatabase : RoomDatabase()
{

    /**
     * @return The DAO for the SearchSentence table.
     */
    @SuppressWarnings("WeakerAccess")
    abstract fun favorites(): FavoritesDao

    companion object {
        private var INSTANCE: WikiDatabase? = null

        /*internal val MIGRATION_1_2: Migration = object : Migration(1, 2)
        {
            override fun migrate(database: SupportSQLiteDatabase)
            {
                database.execSQL("DELETE FROM " + SearchSentence.TABLE_NAME)

                database.execSQL("ALTER TABLE " + SearchSentence.TABLE_NAME + " ADD COLUMN " + SearchSentence.COLUMN_VERTICAL + " TEXT")

                delete old table
                //database.execSQL("DROP TABLE " + SearchSentence.TABLE_NAME);

                //sInstance.sentences().clearTable();

                CREATE new one
                //database.execSQL("CREATE TABLE IF NOT EXISTS "+SearchSentence.TABLE_NAME+" ("+SearchSentence.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SearchSentence.COLUMN_SENTENCE+" TEXT, "+SearchSentence.COLUMN_VERTICAL+" TEXT)");

                Create trigger on Insert when count of rows = 30
                database.execSQL("CREATE TRIGGER delete_till_30 INSERT ON " + SearchSentence.TABLE_NAME + " WHEN (select count(*) from " + SearchSentence.TABLE_NAME + ")>29\n" +
                        "BEGIN\n" +
                        "    DELETE FROM " + SearchSentence.TABLE_NAME + " WHERE " + SearchSentence.COLUMN_ID + " IN  (SELECT " + SearchSentence.COLUMN_ID + " FROM " + SearchSentence.TABLE_NAME + " ORDER BY " + SearchSentence.COLUMN_ID + " limit (select count(*) -29 from " + SearchSentence.TABLE_NAME + " ));\n" +
                        "END;")
            }
        }*/

        fun getInstance(context: Context): WikiDatabase?
        {
            if (INSTANCE == null)
            {
                synchronized(WikiDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WikiDatabase::class.java, "mykotlinapp.db")

                            /*.addCallback(object: RoomDatabase.Callback(){

                                override fun onCreate(db: SupportSQLiteDatabase)
                                {
                                    super.onCreate(db)
                                }

                                override fun onOpen(db: SupportSQLiteDatabase)
                                {
                                    super.onOpen(db)
                                }
                            })*/

                            /*.addMigrations(MIGRATION_1_2)*/

                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}