package fr.bowser.dev.develoment_mode.configuration

@DevelopmentModeDsl
data class DevelopmentModeConfigurationAction(val key: String) {

    @DevelopmentModeDsl
    class Builder {

        var key = ""

        fun build() =
            DevelopmentModeConfigurationAction(
                key
            )
    }
}