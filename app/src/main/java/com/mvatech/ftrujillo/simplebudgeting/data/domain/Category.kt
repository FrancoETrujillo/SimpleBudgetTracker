package com.mvatech.ftrujillo.simplebudgeting.data.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table", indices = [Index( value = ["color"], unique = true)])
data class Category(
    @PrimaryKey(autoGenerate = false )
    val color: Int,
    val name:String,
    val enabled: Boolean = true
)

