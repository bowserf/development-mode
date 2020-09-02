package fr.bowser.dev.develoment_mode.configuration

@DevelopmentModeDsl
data class DevelopmentModeConfigurationBooleanValue(val key: String, val defaultValue: Boolean) {

    @DevelopmentModeDsl
    class Builder {

        var key = ""
        var defaultValue = false

        fun build() =
            DevelopmentModeConfigurationBooleanValue(
                key,
                defaultValue
            )
    }
}