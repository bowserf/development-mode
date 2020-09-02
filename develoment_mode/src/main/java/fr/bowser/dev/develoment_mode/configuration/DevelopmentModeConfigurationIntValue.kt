package fr.bowser.dev.develoment_mode.configuration

@DevelopmentModeDsl
data class DevelopmentModeConfigurationIntValue(val key: String, val defaultValue: Int) {

    @DevelopmentModeDsl
    class Builder {

        var key = ""
        var defaultValue = 0

        fun build() =
            DevelopmentModeConfigurationIntValue(
                key,
                defaultValue
            )
    }
}