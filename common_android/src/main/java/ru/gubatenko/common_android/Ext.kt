package ru.gubatenko.common_android

import android.view.View

fun View.onClick(callback: () -> Unit) = setOnClickListener { callback.invoke() }