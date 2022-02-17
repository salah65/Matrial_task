package com.example.fake_matrial.data.gateways

import android.content.Context
import android.content.SharedPreferences
import com.example.fake_matrial.app.MyApplication
import javax.inject.Inject


const val PREFERENCES_NAME = "PREFERENCES_NAME"

class PreferencesGateway @Inject constructor(val application: MyApplication) {

    inline fun <reified T : Any> save(key: String, value: T) {
        application
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .apply { putValue(key, value) }
            .apply()


    }

    inline fun <reified T : Any> SharedPreferences.Editor.putValue(
        key: String,
        value: T
    ) {
        when (T::class) {
            Boolean::class -> putBoolean(key, value as Boolean)
            Int::class -> putInt(key, value as Int)
            Long::class -> putLong(key, value as Long)
            Float::class -> putFloat(key, value as Float)
            String::class -> putString(key, value as String)
            else -> throw UnsupportedOperationException("not supported preferences type")
        }
    }

    inline fun <reified T : Any> load(key: String, defaultValue: T): T {
        return application
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .run { getValue(key, defaultValue) }

    }

    inline fun <reified T : Any> SharedPreferences.getValue(
        key: String,
        defaultValue: T
    ): T {
        return when (T::class) {
            Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
            Int::class -> getInt(key, defaultValue as Int) as T
            Long::class -> getLong(key, defaultValue as Long) as T
            Float::class -> getFloat(key, defaultValue as Float) as T
            String::class -> getString(key, defaultValue as String) as T
            else -> throw UnsupportedOperationException("not supported preferences type")
        }
    }

    fun isSaved(key: String): Boolean {
        return application
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .contains(key)
    }

    fun remove(key: String) {
        application
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(key)
            .apply()

    }
}




