package com.zcrain.ipcunit.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.domain.Person

/**
 * Author：CWQ
 * Date：2021/5/15
 * Desc:
 */
class IntentTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val person = intent.getParcelableExtra<Person>("bean")
        val student = intent.getSerializableExtra("student")

        person?.run {
            Toast.makeText(this@IntentTwoActivity, "接收到parcelable信息，$person", Toast.LENGTH_SHORT).show()
        }
        student?.run {
            Toast.makeText(this@IntentTwoActivity, "接收到serializable信息，$student", Toast.LENGTH_SHORT).show()
        }


    }
}