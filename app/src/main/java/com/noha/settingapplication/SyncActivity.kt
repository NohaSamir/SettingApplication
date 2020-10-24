package com.noha.settingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.preference.PreferenceManager

class SyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sync)

        //ToDo 11: Read preference value
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val signature = preference.getString("signature", "")

        val signatureTextView = findViewById<TextView>(R.id.signatureTextView)
        signatureTextView.text = signature
    }
}