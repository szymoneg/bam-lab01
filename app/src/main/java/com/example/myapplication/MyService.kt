package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MyService : Service() {

    private var job: Job? = null
    private var count = 0;


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job = CoroutineScope(Dispatchers.IO).launch {
            var counter = 0
            while (isActive) {
                Log.d("TimerService", "Counter: $counter")
                delay(1000)
                counter++
                count++
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        job?.cancel()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user-database"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()
        val userData = UserData(id = 1, userName = "username", number = count)
        userDao.insertUserData(userData)

        val intent = Intent("your.custom.action")
        intent.putExtra("userName", "username")
        intent.putExtra("number", count)
        sendBroadcast(intent)

        super.onDestroy()
    }
}