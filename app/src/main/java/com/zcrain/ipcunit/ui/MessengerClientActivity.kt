package com.zcrain.ipcunit.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.R
import com.zcrain.ipcunit.service.MessengerService

/**
 * Author：CWQ
 * Date：2021/5/16
 * Desc:
 */
class MessengerClientActivity : AppCompatActivity() {
    private val TAG = "MessengerClient"

    private var mService: Messenger? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger_client)

        bindMessengerService()

        findViewById<TextView>(R.id.tv_bind_messenger_service).setOnClickListener {
            val message = Message.obtain(null, 100)
            val bundle = Bundle().apply {
                putString("msg", "hello,i'm client")
            }
            message.data = bundle
            //接收服务端回答
            message.replyTo = mGetReplyMessenger
            mService?.send(message)
        }
    }

    private fun bindMessengerService() {
        val intent = Intent(this, MessengerService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(TAG, "服务已绑定")
            Toast.makeText(this@MessengerClientActivity, "服务已绑定", Toast.LENGTH_SHORT).show()
            mService = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "服务已断开")
        }
    }

    private var mGetReplyMessenger = Messenger(MessengerHandler())

    private class MessengerHandler : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                200 -> {
                    val reply = msg.data.getString("msg")
                    Log.i("MessengerClientActivity", "接收到服务端消息：${reply}")
                }
                else -> super.handleMessage(msg)
            }

        }
    }


    override fun onDestroy() {
        if (mService != null) {
            unbindService(mConnection)
        }
        super.onDestroy()
    }
}