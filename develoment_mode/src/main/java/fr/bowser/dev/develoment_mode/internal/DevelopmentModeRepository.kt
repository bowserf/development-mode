package fr.bowser.dev.develoment_mode.internal

internal interface DevelopmentModeRepository {

    fun getInt(key: String): Int

    fun save(key: String, value: Int)

    fun getLong(key: String): Long

    fun save(key: String, value: Long)

    fun getFloat(key: String): Float

    fun save(key: String, value: Float)

    fun getString(key: String): String

    fun save(key: String, value: String)

    fun getBoolean(key: String): Boolean

    fun save(key: String, value: Boolean)
}
