package ru.gubatenko.mvi_android.android

import android.view.View

fun View.onClick(callback: () -> Unit) = setOnClickListener { callback.invoke() }