package com.zcrain.ipcunit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.zcrain.ipcunit.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_intent).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_files).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_messenger).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_aidl).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_provider).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_socket).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_intent -> startActivity(Intent(this, IntentOneActivity::class.java))
            R.id.tv_files -> startActivity(Intent(this, FileOneActivity::class.java))
            R.id.tv_messenger -> startActivity(Intent(this, MessengerClientActivity::class.java))
            R.id.tv_aidl -> startActivity(Intent(this, AIDLActivity::class.java))
            R.id.tv_provider -> Unit
            R.id.tv_socket -> Unit
        }
    }
}