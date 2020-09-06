package fr.bowser.dev.develoment_mode

interface DevelopmentMode {

    fun getInt(key: String): Int

    fun getLong(key: String): Long

    fun getFloat(key: String): Float

    fun getString(key: String): String

    fun getBoolean(key: String): Boolean

    fun addActionListener(listener: ActionListener)

    fun removeActionListener(listener: ActionListener)

    fun addValueChangeListener(listener: ValueChangeListener)

    fun removeValueChangeListener(listener: ValueChangeListener)

    interface ActionListener {

        fun onActionTriggered(key: String)
    }

    interface ValueChangeListener {

        fun onIntValueChanged(key: String, value: Int) {}

        fun onLongValueChanged(key: String, value: Long) {}

        fun onFloatValueChanged(key: String, value: Float) {}

        fun onStringValueChanged(key: String, value: String) {}

        fun onBooleanValueChanged(key: String, value: Boolean) {}
    }
}