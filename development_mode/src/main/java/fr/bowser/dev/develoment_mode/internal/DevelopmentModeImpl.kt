package fr.bowser.dev.develoment_mode.internal

import fr.bowser.dev.develoment_mode.DevelopmentMode
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfiguration

internal class DevelopmentModeImpl(
    private val developmentModeRepository: DevelopmentModeRepository,
    private val configuration: DevelopmentModeConfiguration
) : DevelopmentMode {

    private val actionListeners = mutableListOf<DevelopmentMode.ActionListener>()
    private val valueChangeListeners = mutableListOf<DevelopmentMode.ValueChangeListener>()

    override fun getInt(key: String): Int {
        return developmentModeRepository.getInt(key)
    }

    override fun getLong(key: String): Long {
        return developmentModeRepository.getLong(key)
    }

    override fun getFloat(key: String): Float {
        return developmentModeRepository.getFloat(key)
    }

    override fun getString(key: String): String {
        return developmentModeRepository.getString(key)
    }

    override fun getBoolean(key: String): Boolean {
        return developmentModeRepository.getBoolean(key)
    }

    override fun addActionListener(listener: DevelopmentMode.ActionListener) {
        if (actionListeners.contains(listener)) {
            return
        }
        actionListeners.add(listener)
    }

    override fun removeActionListener(listener: DevelopmentMode.ActionListener) {
        actionListeners.remove(listener)
    }

    override fun addValueChangeListener(listener: DevelopmentMode.ValueChangeListener) {
        if (valueChangeListeners.contains(listener)) {
            return
        }
        valueChangeListeners.add(listener)
    }

    override fun removeValueChangeListener(listener: DevelopmentMode.ValueChangeListener) {
        valueChangeListeners.add(listener)
    }

    fun setInt(key: String, value: Int) {
        developmentModeRepository.save(key, value)
        notifyValueIntChanged(key, value)
    }

    fun setLong(key: String, value: Long) {
        developmentModeRepository.save(key, value)
        notifyValueLongChanged(key, value)
    }

    fun setFloat(key: String, value: Float) {
        developmentModeRepository.save(key, value)
        notifyValueFloatChanged(key, value)
    }

    fun setString(key: String, value: String) {
        developmentModeRepository.save(key, value)
        notifyValueStringChanged(key, value)
    }

    fun setBoolean(key: String, value: Boolean) {
        developmentModeRepository.save(key, value)
        notifyValueBooleanChanged(key, value)
    }

    fun actionTriggered(key: String) {
        notifyActionTriggered(key)
    }

    fun getConfiguration(): DevelopmentModeConfiguration {
        return configuration
    }

    private fun notifyActionTriggered(key: String) {
        actionListeners.forEach {
            it.onActionTriggered(key)
        }
    }

    private fun notifyValueIntChanged(key: String, value: Int) {
        valueChangeListeners.forEach {
            it.onIntValueChanged(key, value)
        }
    }

    private fun notifyValueLongChanged(key: String, value: Long) {
        valueChangeListeners.forEach {
            it.onLongValueChanged(key, value)
        }
    }

    private fun notifyValueFloatChanged(key: String, value: Float) {
        valueChangeListeners.forEach {
            it.onFloatValueChanged(key, value)
        }
    }

    private fun notifyValueStringChanged(key: String, value: String) {
        valueChangeListeners.forEach {
            it.onStringValueChanged(key, value)
        }
    }

    private fun notifyValueBooleanChanged(key: String, value: Boolean) {
        valueChangeListeners.forEach {
            it.onBooleanValueChanged(key, value)
        }
    }
}