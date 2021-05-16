package com.zcrain.ipcunit.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.TextView
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

        findViewById<TextView>(R.id.tv_bind_messenger_service).setOnClickListener {
            bindMessengerService()
        }
    }

    private fun bindMessengerService() {
        val intent = Intent(this, MessengerService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "service connected")
            mService = Messenger(service)
            val message = Message.obtain(null, 100)
            val bundle = Bundle().apply {
                putString("msg", "hello,i'm client")
            }
            message.data = bundle
            mService?.send(message)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "service disconnected")
        }
    }

    override fun onDestroy() {
        if (mService != null) {
            unbindService(mConnection)
        }
        super.onDestroy()
    }
}