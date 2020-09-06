package fr.bowser.dev.develoment_mode.configuration

@DevelopmentModeDsl
data class DevelopmentModeConfigurationStringValue(val key: String, val defaultValue: String) {

    @DevelopmentModeDsl
    class Builder {

        var key = ""
        var defaultValue = ""

        fun build() =
            DevelopmentModeConfigurationStringValue(
                key,
                defaultValue
            )
    }
}