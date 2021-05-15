package com.zcrain.ipcunit.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zcrain.ipcunit.R
import com.zcrain.ipcunit.domain.Person

/**
 * Author：CWQ
 * Date：2021/5/15
 * Desc:
 */
class IntentOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_one)

        findViewById<TextView>(R.id.tv_intent_send).setOnClickListener {
            startActivity(Intent(this,IntentTwoActivity::class.java).apply {
                putExtra("int",123)
                putExtra("string","hello IPCUnit")
                putExtra("bean",Person("鹰眼",28))
            })
        }
    }
}