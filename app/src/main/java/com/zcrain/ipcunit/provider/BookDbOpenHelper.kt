package com.zcrain.ipcunit.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Author：CWQ
 * Date：2021/5/20
 * Desc:
 */
class BookDbOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val BOOK_TABLE_NAME = "book"
        private const val DB_NAME = "book_provider.db"
        private const val DB_VERSION = 1

        private const val CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS $BOOK_TABLE_NAME (_id INTEGER PRIMARY KEY,name TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_BOOK_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}