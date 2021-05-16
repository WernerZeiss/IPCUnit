package com.zcrain.ipcunit.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.R
import com.zcrain.ipcunit.domain.Student
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

/**
 * Author：CWQ
 * Date：2021/5/16
 * Desc:
 */
class FileOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_one)


        findViewById<TextView>(R.id.tv_write_file).setOnClickListener {
            writeObj2File()
        }
    }

    private fun writeObj2File(){
        val student = Student("李四",20)
        val cacheFile = File(cacheDir.path,"student")
        Log.d("FileOneActivity","cacheFilePath:${cacheFile.path}")

        val objectOutputStream = ObjectOutputStream(FileOutputStream(cacheFile))
        objectOutputStream.writeObject(student)
        Log.d("FileOneActivity","write student")

        objectOutputStream.close()
    }
}