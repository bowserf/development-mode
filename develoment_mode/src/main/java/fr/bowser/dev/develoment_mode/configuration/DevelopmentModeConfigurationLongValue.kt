package fr.bowser.dev.develoment_mode.configuration

@DevelopmentModeDsl
data class DevelopmentModeConfigurationLongValue(val key: String, val defaultValue: Long) {

    @DevelopmentModeDsl
    class Builder {

        var key = ""
        var defaultValue = 0L

        fun build() =
            DevelopmentModeConfigurationLongValue(
                key,
                defaultValue
            )
    }
}