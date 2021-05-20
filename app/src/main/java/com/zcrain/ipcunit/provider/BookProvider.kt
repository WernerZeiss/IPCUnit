package com.zcrain.ipcunit.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.util.Log

/**
 * Author：CWQ
 * Date：2021/5/20
 * Desc:
 */
class BookProvider : ContentProvider() {

    companion object {
        private const val TAG = "BookProvider"
        private const val AUTHORITY = "com.zcrain.ipcunit.provider"

        private const val URI_INSERT_CODE = 0
        private const val URI_DELETE_CODE = 1
        private const val URI_UPDATE_CODE = 2
        private const val URI_QUERY_CODE = 3

        private val mUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    init {
        mUriMatcher.addURI(AUTHORITY, "insert", URI_INSERT_CODE)
        mUriMatcher.addURI(AUTHORITY, "delete", URI_DELETE_CODE)
        mUriMatcher.addURI(AUTHORITY, "update", URI_UPDATE_CODE)
        mUriMatcher.addURI(AUTHORITY, "query", URI_QUERY_CODE)
    }

    private lateinit var mOpenHelper: SQLiteOpenHelper

    override fun onCreate(): Boolean {
        mOpenHelper = BookDbOpenHelper(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.i(TAG, "query")
        val match = mUriMatcher.match(uri)
        if (match != URI_QUERY_CODE) {
            throw IllegalArgumentException("query 不支持的Uri:$uri")
        }
        val mDb = mOpenHelper.readableDatabase
        return mDb.query(
            BookDbOpenHelper.BOOK_TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder,
            null
        )
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.i(TAG, "insert")

        val match = mUriMatcher.match(uri)
        if (match != URI_INSERT_CODE) {
            throw IllegalArgumentException("insert 不支持的Uri:$uri")
        }
        val mDb = mOpenHelper.writableDatabase
        val insert = mDb.insert(BookDbOpenHelper.BOOK_TABLE_NAME, null, values)
        mDb.close()
        context?.contentResolver?.notifyChange(uri, null)
        return Uri.parse(insert.toString())
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.i(TAG, "delete")
        val match = mUriMatcher.match(uri)
        if (match != URI_DELETE_CODE) {
            throw IllegalArgumentException("delete 不支持的Uri:$uri")
        }
        val mDb = mOpenHelper.writableDatabase
        val count = mDb.delete(BookDbOpenHelper.BOOK_TABLE_NAME, selection, selectionArgs)
        mDb.close()
        if (count > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.i(TAG, "update")
        val match = mUriMatcher.match(uri)
        if (match != URI_UPDATE_CODE) {
            throw IllegalArgumentException("update 不支持的Uri:$uri")
        }
        val mDb = mOpenHelper.writableDatabase
        val count = mDb.update(BookDbOpenHelper.BOOK_TABLE_NAME, values, selection, selectionArgs)
        mDb.close()
        if (count > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return count
    }
}