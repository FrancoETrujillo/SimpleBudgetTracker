package com.mvatech.ftrujillo.simplebudgeting.data.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table", indices = [Index( value = ["color"], unique = true)])
data class Category(
    val name:String,
    @PrimaryKey(autoGenerate = false )
    val color: Int
)