package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserData(
    @PrimaryKey val id: Int,
    val userName: String,
    val number: Int
)