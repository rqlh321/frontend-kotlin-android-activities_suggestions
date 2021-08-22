package ru.gubatenko.core_android.android

import android.view.View

fun View.onClick(callback: () -> Unit) = setOnClickListener { callback.invoke() }