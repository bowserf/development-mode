package fr.bowser.dev.development_mode.sample

import android.app.Application
import android.widget.Toast
import fr.bowser.dev.develoment_mode.DevelopmentMode
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfiguration
import fr.bowser.dev.develoment_mode.DevelopmentModeModule
import fr.bowser.dev.develoment_mode.configuration.developmentModeConfiguration

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val developmentModeConfiguration = createDevelopmentModeConfiguration()
        val developmentMode = DevelopmentModeModule.initialize(this, developmentModeConfiguration)
        developmentMode.addActionListener(createActionListener())
        developmentMode.addValueChangeListener(createValueChangeListener())
    }

    private fun createDevelopmentModeConfiguration(): DevelopmentModeConfiguration {
        return developmentModeConfiguration {
            action {
                key = "action_click"
            }
            booleanValue {
                key = "is_enable"
                defaultValue = false
            }
            intValue {
                key = "int_value"
                defaultValue = 42
            }
            longValue {
                key = "long_value"
                defaultValue = 1800L
            }
            floatValue {
                key = "float_value"
                defaultValue = Math.PI.toFloat()
            }
            stringValue {
                key = "string_value"
                defaultValue = "Default string value"
            }
        }
    }

    private fun createActionListener() = object : DevelopmentMode.ActionListener {
        override fun onActionTriggered(key: String) {
            Toast.makeText(
                this@SampleApp,
                "Action clicked for key: $key",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun createValueChangeListener() = object : DevelopmentMode.ValueChangeListener {
        override fun onIntValueChanged(key: String, value: Int) {
            showToastWhenValueChanged(key, value)
        }

        override fun onLongValueChanged(key: String, value: Long) {
            showToastWhenValueChanged(key, value)
        }

        override fun onFloatValueChanged(key: String, value: Float) {
            showToastWhenValueChanged(key, value)
        }

        override fun onStringValueChanged(key: String, value: String) {
            showToastWhenValueChanged(key, value)
        }

        override fun onBooleanValueChanged(key: String, value: Boolean) {
            showToastWhenValueChanged(key, value)
        }
    }

    private fun showToastWhenValueChanged(key: String, value: Any) {
        Toast.makeText(
            this@SampleApp,
            "Value changed for key: $key, value: $value",
            Toast.LENGTH_SHORT
        ).show()
    }
}