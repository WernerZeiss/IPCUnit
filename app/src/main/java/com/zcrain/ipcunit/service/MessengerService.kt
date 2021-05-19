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
                    Log.i("MessengerService", "服务端收到消息:$str")

                    val client:Messenger = msg.replyTo
                    val replyMsg = Message.obtain(null, 200)
                    val bundle = Bundle().apply {
                        putString("msg", "hello,i'm service")
                    }
                    replyMsg.data = bundle
                    client.send(replyMsg)
                }
                else -> super.handleMessage(msg)
            }
        }
    }
}