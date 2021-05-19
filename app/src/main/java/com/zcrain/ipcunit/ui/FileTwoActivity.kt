package com.zcrain.ipcunit.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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
            Log.i("FileTwoActivity", "student:$student")
            Toast.makeText(this, "读取文件信息：$student", Toast.LENGTH_SHORT).show()
            objectInputStream.close()
        }
    }
}