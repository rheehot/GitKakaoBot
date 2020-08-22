package com.sungbin.gitkakaobot.util

import android.content.Context
import androidx.core.content.edit

object DataUtils {

    fun save(context: Context, title: String, value: String) {
        context.getSharedPreferences("pref", Context.MODE_PRIVATE).edit {
            putString(title, value)
            apply()
        }
    }

    fun read(context: Context, title: String, _null: String) =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE).getString(title, _null)

}