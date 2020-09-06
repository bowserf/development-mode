package fr.bowser.dev.develoment_mode.configuration

@DevelopmentModeDsl
data class DevelopmentModeConfigurationFloatValue(val key: String, val defaultValue: Float) {

    @DevelopmentModeDsl
    class Builder {

        var key = ""
        var defaultValue = 0f

        fun build() =
            DevelopmentModeConfigurationFloatValue(
                key,
                defaultValue
            )
    }
}