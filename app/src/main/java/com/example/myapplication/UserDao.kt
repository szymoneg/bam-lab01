package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {
    @Insert
    fun insertUserData(userData: UserData)

    @Query("SELECT * FROM UserData")
    fun getAllUserData(): List<UserData>
}