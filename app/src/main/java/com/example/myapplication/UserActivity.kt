package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.room.Room

class UserActivity : ComponentActivity() {
    private val numberReceiver = NumberReceiver();
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activty)

        val username = intent.getStringExtra("username")

        val usernameTextView = findViewById<TextView>(R.id.usernameTextView)
        usernameTextView.text = "Witaj, $username!"

        val startServiceButton: Button = findViewById(R.id.button_start)
        val stopServiceButton: Button = findViewById(R.id.button_end)
        val userButton: Button = findViewById(R.id.button_display)

        db = AppDatabase.getInstance(applicationContext);

        userButton.setOnClickListener {
            val userDao = db.userDao()
            val userDataList = userDao.getAllUserData()

            Log.d("UserActivity", "Number of rows in the database: ${userDataList.size}")

            for (userData in userDataList) {
                Log.d("UserActivity", "User: ${userData.userName}, Number: ${userData.number}")
            }
        }

        startServiceButton.setOnClickListener {
            val serviceIntent = Intent(this, MyService::class.java)
            startService(serviceIntent)
        }

        stopServiceButton.setOnClickListener {
            val serviceIntent = Intent(this, MyService::class.java)
            stopService(serviceIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("your.custom.action")
        registerReceiver(numberReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(numberReceiver)
    }
}
