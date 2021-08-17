package ru.gubatenko.feature_main_android

import android.view.View

fun View.onClick(callback: () -> Unit) = setOnClickListener { callback.invoke() }