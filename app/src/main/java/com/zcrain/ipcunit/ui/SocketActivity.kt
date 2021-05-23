package com.zcrain.ipcunit.ui

import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.R
import com.zcrain.ipcunit.service.SocketService
import java.io.*
import java.lang.Exception
import java.net.Socket
import kotlin.concurrent.thread

/**
 * Author：CWQ
 * Date：2021/5/21
 * Desc:
 */
class SocketActivity : AppCompatActivity() {
    private val TAG = "SocketActivity"
    private val MESSAGE_SOCKET_CONNECTED = 1
    private val MESSAGE_RECEIVE_MSG = 2
    private val MESSAGE_SEND_MSG = 3

    private lateinit var tvMsg: TextView

    var mSocket: Socket? = null
    var mPrintWriter: PrintWriter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)
        tvMsg = findViewById<TextView>(R.id.tv_socket_send)
        tvMsg.setOnClickListener {
            thread {
                mPrintWriter?.println("你好啊")
                mHandler.obtainMessage(MESSAGE_SEND_MSG, "你好啊").sendToTarget()
            }
        }

        startService(Intent(this, SocketService::class.java))
        thread {
            connectSocketService()
        }
    }


    private val mHandler = object : Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_SOCKET_CONNECTED -> {
                    Log.i(TAG, "socket 连接成功")
                    tvMsg.text = "服务连接成功！"
                }
                MESSAGE_SEND_MSG -> {
                    Log.i(TAG, "发送消息：${msg.obj}")
                    addMsg2Show("发送了消息：${msg.obj}")
                }
                MESSAGE_RECEIVE_MSG -> {
                    Log.i(TAG, "接收到服务端消息：${msg.obj}")
                    addMsg2Show("接收到回复：${msg.obj}")
                }
            }
        }
    }


    private fun addMsg2Show(msg: String) {
        val currentMsg = tvMsg.text.toString()
        tvMsg.text = currentMsg + "\n" + msg
    }


    private fun connectSocketService() {
        var tempSocket: Socket? = null
        while (tempSocket == null) {
            try {
                tempSocket = Socket("localhost", 8688)
                mSocket = tempSocket
                mPrintWriter =
                    PrintWriter(
                        BufferedWriter(OutputStreamWriter(mSocket?.getOutputStream())),
                        true
                    )
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED)
            } catch (e: Exception) {
                Log.e(TAG, "连接失败，1s后重连")
                SystemClock.sleep(1000)
                e.printStackTrace()
            }
        }

        try {
            val bufferReader = BufferedReader(InputStreamReader(mSocket?.getInputStream()))
            while (!this.isFinishing) {
                val msg = bufferReader.readLine()
                Log.i(TAG, "读取到服务器消息：$msg")
                if (msg != null) {
                    mHandler.obtainMessage(MESSAGE_RECEIVE_MSG, msg).sendToTarget()
                }
            }
            bufferReader.close()
            mPrintWriter?.close()
            mSocket?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        mSocket?.shutdownInput()
        mSocket?.close()
        super.onDestroy()
    }
}