package com.zcrain.ipcunit.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.zcrain.ipcunit.IBookManager
import com.zcrain.ipcunit.domain.Book
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Author：CWQ
 * Date：2021/5/19
 * Desc:
 */
class BookManagerService : Service() {

    private val mBookList = CopyOnWriteArrayList<Book>()

    private val mBinder: Binder = object : IBookManager.Stub() {

        override fun getBookList(): MutableList<Book> {
            return mBookList
        }

        override fun addBook(book: Book?) {
            mBookList.add(book)
            Log.i("BookManagerService","服务端添加成功")
        }
    }

    override fun onCreate() {
        super.onCreate()
        mBookList.add(Book(1, "小王子"))
        mBookList.add(Book(2, "一千零一夜"))
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }
}