package com.zcrain.ipcunit.ui

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.R
import com.zcrain.ipcunit.domain.Book

/**
 * Author：CWQ
 * Date：2021/5/20
 * Desc:
 */
class ProviderActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "ProviderActivity"

    private var bookId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)

        findViewById<TextView>(R.id.tv_provider_insert).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_provider_delete).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_provider_update).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_provider_query).setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_provider_insert -> {
                val uri = Uri.parse("content://com.zcrain.ipcunit.provider/insert")
                val values = ContentValues().apply {
                    put("_id", bookId)
                    put("name", "小王子$bookId")
                }
                contentResolver.insert(uri, values)
                bookId++
            }
            R.id.tv_provider_delete -> Unit
            R.id.tv_provider_update -> Unit
            R.id.tv_provider_query -> {
                val uri = Uri.parse("content://com.zcrain.ipcunit.provider/query")
                val cursor = contentResolver.query(uri, arrayOf("_id", "name"), null, null, null)
                while (cursor!!.moveToNext()) {
                    val book = Book(cursor.getInt(0), cursor.getString(1))
                    Log.i(TAG, "query book:$book")
                }
            }
        }
    }
}