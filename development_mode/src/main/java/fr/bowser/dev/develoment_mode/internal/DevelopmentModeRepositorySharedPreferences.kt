package fr.bowser.dev.develoment_mode.internal

import android.content.SharedPreferences
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfiguration

internal class DevelopmentModeRepositorySharedPreferences(
    private val sharedPreferences: SharedPreferences,
    private val configuration: DevelopmentModeConfiguration
) : DevelopmentModeRepository {

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, getDefaultValueInt(key))
    }

    override fun save(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, getDefaultValueLong(key))
    }

    override fun save(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, getDefaultValueFloat(key))
    }

    override fun save(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, getDefaultValueString(key))!!
    }

    override fun save(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, getDefaultValueBoolean(key))
    }

    override fun save(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    private fun getDefaultValueInt(key: String) =
        configuration.intValues.first { it.key == key }.defaultValue

    private fun getDefaultValueLong(key: String) =
        configuration.longValues.first { it.key == key }.defaultValue

    private fun getDefaultValueFloat(key: String) =
        configuration.floatValues.first { it.key == key }.defaultValue

    private fun getDefaultValueString(key: String) =
        configuration.stringValues.first { it.key == key }.defaultValue

    private fun getDefaultValueBoolean(key: String) =
        configuration.booleanValues.first { it.key == key }.defaultValue

    companion object {
        const val PREFERENCE_NAME = "development_mode"
    }
}
