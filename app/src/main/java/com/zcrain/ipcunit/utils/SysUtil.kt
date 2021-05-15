package com.zcrain.ipcunit.utils

import android.app.ActivityManager
import android.content.Context

/**
 * Author：CWQ
 * Date：2021/5/15
 * Desc:
 */
object SysUtil {

    fun getCurrentProcessName(context: Context): String {
        val pid = android.os.Process.myPid()
        val mActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        mActivityManager.runningAppProcesses.forEach {
            if (it.pid == pid) {
                return it.processName
            }
        }

        return ""
    }
}