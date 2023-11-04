package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NumberReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        val userName = intent?.getStringExtra("userName")
        val number = intent?.getIntExtra("number", 0)

        Log.d("NumberReceiver", "Received - User: $userName, Number: $number")
    }
}