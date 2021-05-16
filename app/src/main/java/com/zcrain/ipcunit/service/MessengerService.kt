package com.zcrain.ipcunit.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

/**
 * Author：CWQ
 * Date：2021/5/16
 * Desc:
 */
class MessengerService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return mMessenger.binder
    }

    private val mMessenger = Messenger(MessengerHandler())

    class MessengerHandler : Handler(Looper.myLooper()!!) {

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                100 -> {
                    val str = msg.data.getString("msg")
                    Log.d("MessengerService", "receive message:$str")
                }
                else -> super.handleMessage(msg)
            }
        }
    }
}