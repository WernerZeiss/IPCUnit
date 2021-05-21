package com.zcrain.ipcunit.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.text.TextUtils
import android.util.Log
import java.io.*
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

/**
 * Author：CWQ
 * Date：2021/5/21
 * Desc:
 */
class SocketService : Service() {

    private var mIsDestoryed = false

    override fun onCreate() {
        Thread(TcpServer()).start()
        Log.i("SocketService", "onCreate")
        super.onCreate()
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.i("SocketService","服务端销毁")
        mIsDestoryed = true
        super.onDestroy()
    }


    private inner class TcpServer : Runnable {

        override fun run() {
            var serverSocket: ServerSocket? = null
            try {
                serverSocket = ServerSocket(8688)
                Log.i("SocketService", "serverSocket 创建")
            } catch (e: Exception) {
                Log.e("SocketService", "serverSocket生成失败")
                e.printStackTrace()
                return
            }

            while (!mIsDestoryed) {
                try {
                    val client = serverSocket.accept()
                    Log.i("SocketService", "创建client:$client")
                    thread {
                        responseClient(client)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


    private fun responseClient(client: Socket) {
        val ins = BufferedReader(InputStreamReader(client.getInputStream()))
        val ous = PrintWriter(BufferedWriter(OutputStreamWriter(client.getOutputStream())),true)
        ous.println("欢迎来到服务器~")
        Log.i("SocketService", "发送欢迎语")
        while (!mIsDestoryed) {
            Log.i("SocketService", "进入循环读取")
            val readStr = ins.readLine()
            Log.i("SocketService", "读取到客户端消息:$readStr")
            if (readStr == null) {
                break
            }
            ous.println("$readStr 哈哈！")
            Log.i("SocketService", "给客户端发送回复:$readStr 哈哈！")
            ins.close()
            ous.close()
        }
    }
}