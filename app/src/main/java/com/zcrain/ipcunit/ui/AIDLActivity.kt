package com.zcrain.ipcunit.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.IBookManager
import com.zcrain.ipcunit.R
import com.zcrain.ipcunit.domain.Book
import com.zcrain.ipcunit.service.BookManagerService

/**
 * Author：CWQ
 * Date：2021/5/19
 * Desc:
 */
class AIDLActivity : AppCompatActivity() {

    private val TAG = "AIDLActivity"

    private var mBookManager: IBookManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)

        val intent = Intent(this,BookManagerService::class.java)
        bindService(intent,mConnection,Context.BIND_AUTO_CREATE)

        findViewById<TextView>(R.id.tv_aidl_add).setOnClickListener {
            mBookManager?.addBook(Book(3, "海底两万里"))
        }

        findViewById<TextView>(R.id.tv_aidl_getlist).setOnClickListener {
            val bookList = mBookManager?.bookList
            Log.i(TAG, "查询远程书籍列表：$bookList")
        }
    }

    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(TAG, "远程连接成功")
            mBookManager = IBookManager.Stub.asInterface(service)

            val bookList = mBookManager?.bookList
            Log.i(TAG, "连接初始，远程书籍列表：$bookList")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "远程连接断开")
        }
    }

    override fun onDestroy() {
        unbindService(mConnection)
        super.onDestroy()
    }
}