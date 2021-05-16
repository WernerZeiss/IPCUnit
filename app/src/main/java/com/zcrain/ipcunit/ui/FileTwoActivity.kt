package com.zcrain.ipcunit.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.R
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

/**
 * Author：CWQ
 * Date：2021/5/16
 * Desc:
 */
class FileTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_two)

        findViewById<TextView>(R.id.tv_read_file).setOnClickListener {
            readObjInFile()
        }
    }

    private fun readObjInFile() {
        val cacheFile = File(cacheDir.path, "student")
        if (cacheFile.exists()) {
            val objectInputStream = ObjectInputStream(FileInputStream(cacheFile))
            val student = objectInputStream.readObject()
            Log.d("FileTwoActivity", "student:$student")
            objectInputStream.close()
        }
    }
}