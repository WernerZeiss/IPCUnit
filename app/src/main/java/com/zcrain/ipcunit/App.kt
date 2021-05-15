package com.zcrain.ipcunit

import android.app.Application
import android.util.Log
import com.zcrain.ipcunit.utils.SysUtil

/**
 * Author：CWQ
 * Date：2021/5/15
 * Desc:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val currentProcessName = SysUtil.getCurrentProcessName(applicationContext)
        Log.d("App", "onCreate,processName:$currentProcessName")
    }
}