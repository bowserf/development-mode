package fr.bowser.dev.develoment_mode

import android.content.Context
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfiguration
import fr.bowser.dev.develoment_mode.internal.DevelopmentModeImpl
import fr.bowser.dev.develoment_mode.internal.DevelopmentModeRepository
import fr.bowser.dev.develoment_mode.internal.DevelopmentModeRepositoryDisk

object DevelopmentModeModule {

    private lateinit var developmentMode: DevelopmentMode

    fun initialize(
        context: Context,
        developmentModeConfiguration: DevelopmentModeConfiguration
    ): DevelopmentMode {
        if (DevelopmentModeModule::developmentMode.isInitialized) {
            return developmentMode
        }
        val developmentModeRepository = createDevelopmentModeRepository(
            context,
            developmentModeConfiguration
        )
        developmentMode = DevelopmentModeImpl(
            developmentModeRepository,
            developmentModeConfiguration
        )
        return developmentMode
    }

    fun getDevelopmentMode(): DevelopmentMode {
        if (!::developmentMode.isInitialized) {
            throw IllegalStateException("You must call \"initialize(Context, DevelopmentModeConfiguration)\" before to call this method.")
        }
        return developmentMode
    }

    private fun createDevelopmentModeRepository(
        context: Context,
        developmentModeConfiguration: DevelopmentModeConfiguration
    ): DevelopmentModeRepository {
        val sharedPreferences = context.getSharedPreferences(
            DevelopmentModeRepositoryDisk.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        return DevelopmentModeRepositoryDisk(
            sharedPreferences,
            developmentModeConfiguration
        )
    }
}