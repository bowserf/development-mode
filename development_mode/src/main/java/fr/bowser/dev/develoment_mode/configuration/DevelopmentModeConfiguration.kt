package fr.bowser.dev.develoment_mode.configuration

@DslMarker
annotation class DevelopmentModeDsl

@DevelopmentModeDsl
data class DevelopmentModeConfiguration(
    val intValues: List<DevelopmentModeConfigurationIntValue>,
    val longValues: List<DevelopmentModeConfigurationLongValue>,
    val floatValues: List<DevelopmentModeConfigurationFloatValue>,
    val stringValues: List<DevelopmentModeConfigurationStringValue>,
    val booleanValues: List<DevelopmentModeConfigurationBooleanValue>,
    val actions: List<DevelopmentModeConfigurationAction>
) {

    @DevelopmentModeDsl
    class DevelopmentModeConfigurationBuilder {

        private val intValues = mutableListOf<DevelopmentModeConfigurationIntValue>()
        fun intValue(block: DevelopmentModeConfigurationIntValue.Builder.() -> Unit) {
            intValues.add(DevelopmentModeConfigurationIntValue.Builder().apply(block).build())
        }

        private val longValues = mutableListOf<DevelopmentModeConfigurationLongValue>()
        fun longValue(block: DevelopmentModeConfigurationLongValue.Builder.() -> Unit) {
            longValues.add(
                DevelopmentModeConfigurationLongValue.Builder()
                    .apply(block).build()
            )
        }

        private val floatValues = mutableListOf<DevelopmentModeConfigurationFloatValue>()
        fun floatValue(block: DevelopmentModeConfigurationFloatValue.Builder.() -> Unit) {
            floatValues.add(
                DevelopmentModeConfigurationFloatValue.Builder()
                    .apply(block).build()
            )
        }

        private val stringValues = mutableListOf<DevelopmentModeConfigurationStringValue>()
        fun stringValue(block: DevelopmentModeConfigurationStringValue.Builder.() -> Unit) {
            stringValues.add(
                DevelopmentModeConfigurationStringValue.Builder()
                    .apply(block).build()
            )
        }

        private val booleanValues = mutableListOf<DevelopmentModeConfigurationBooleanValue>()
        fun booleanValue(block: DevelopmentModeConfigurationBooleanValue.Builder.() -> Unit) {
            booleanValues.add(
                DevelopmentModeConfigurationBooleanValue.Builder()
                    .apply(block).build()
            )
        }

        private val actions = mutableListOf<DevelopmentModeConfigurationAction>()
        fun action(block: DevelopmentModeConfigurationAction.Builder.() -> Unit) {
            actions.add(
                DevelopmentModeConfigurationAction.Builder()
                    .apply(block).build()
            )
        }

        internal fun build() =
            DevelopmentModeConfiguration(
                intValues,
                longValues,
                floatValues,
                stringValues,
                booleanValues,
                actions
            )
    }
}

fun developmentModeConfiguration(block: DevelopmentModeConfiguration.DevelopmentModeConfigurationBuilder.() -> Unit): DevelopmentModeConfiguration =
    DevelopmentModeConfiguration.DevelopmentModeConfigurationBuilder()
        .apply(block).build()
