

package com.example.androidtask.android.extentions


import android.util.Log
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
fun <T : Any> Any.getTClass(): Class<T> {
    val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
    return type as Class<T>
}

fun Any?.showLog(tag: String? = "SAG") = Log.e(tag,this.toString())
