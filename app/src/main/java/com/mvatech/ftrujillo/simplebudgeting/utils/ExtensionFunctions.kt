package com.mvatech.ftrujillo.simplebudgeting.utils

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
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

fun ViewGroup.inflate(@LayoutRes resource: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(resource, this, attachToRoot)
}