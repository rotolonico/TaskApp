package com.github.rotolonico.taskapp

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue




fun Float.toDp(context: Context): Int {
    val resources = context.getResources()
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this, resources.getDisplayMetrics()
    ).toInt()
}