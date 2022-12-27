package com.example.baseproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val id: String,
    val title: String,
    val completed: Boolean,

    @PrimaryKey(autoGenerate = true)
    val userId: Long
)
