package com.mvatech.ftrujillo.simplebudgeting.utils

import android.content.Context
import android.util.SparseArray
import android.widget.Toast
import androidx.core.util.forEach

fun Any.toast(context: Context?, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}

fun <T> SparseArray<T>.getList():List<T>{
    val list = mutableListOf<T>()
    forEach { _, value ->
        list.add(value)
    }
    return list
}
