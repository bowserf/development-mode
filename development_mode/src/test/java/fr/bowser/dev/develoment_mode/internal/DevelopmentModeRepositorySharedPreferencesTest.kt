package fr.bowser.dev.develoment_mode.internal

import android.content.SharedPreferences
import com.mwm.taozen.shared_preferences.HashMapSharedPreferences
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfiguration
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfigurationBooleanValue
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfigurationFloatValue
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfigurationIntValue
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfigurationLongValue
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfigurationStringValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DevelopmentModeRepositorySharedPreferencesTest {

    @MockK
    private lateinit var developmentModeConfiguration: DevelopmentModeConfiguration

    private lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        sharedPreferences = HashMapSharedPreferences()
    }

    @Test
    fun setBooleanValue() {
        // Given
        val sharedPreferencesKey = "my_boolean"
        every { developmentModeConfiguration.booleanValues } returns listOf(
            DevelopmentModeConfigurationBooleanValue(
                sharedPreferencesKey,
                false
            )
        )
        val developmentModeRepository = DevelopmentModeRepositorySharedPreferences(
            sharedPreferences,
            developmentModeConfiguration
        )

        // When
        developmentModeRepository.save(sharedPreferencesKey, true)

        // Then
        val result = developmentModeRepository.getBoolean(sharedPreferencesKey)
        Assert.assertEquals(true, result)
    }

    @Test
    fun setIntValue() {
        // Given
        val sharedPreferencesKey = "my_int"
        every { developmentModeConfiguration.intValues } returns listOf(
            DevelopmentModeConfigurationIntValue(
                sharedPreferencesKey,
                0
            )
        )
        val developmentModeRepository = DevelopmentModeRepositorySharedPreferences(
            sharedPreferences,
            developmentModeConfiguration
        )

        // When
        developmentModeRepository.save(sharedPreferencesKey, 10)

        // Then
        val result = developmentModeRepository.getInt(sharedPreferencesKey)
        Assert.assertEquals(10, result)
    }

    @Test
    fun setLongValue() {
        // Given
        val sharedPreferencesKey = "my_long"
        every { developmentModeConfiguration.longValues } returns listOf(
            DevelopmentModeConfigurationLongValue(
                sharedPreferencesKey,
                0L
            )
        )
        val developmentModeRepository = DevelopmentModeRepositorySharedPreferences(
            sharedPreferences,
            developmentModeConfiguration
        )

        // When
        developmentModeRepository.save(sharedPreferencesKey, 1_000_000L)

        // Then
        val result = developmentModeRepository.getLong(sharedPreferencesKey)
        Assert.assertEquals(1_000_000L, result)
    }

    @Test
    fun setFloatValue() {
        // Given
        val sharedPreferencesKey = "my_float"
        every { developmentModeConfiguration.floatValues } returns listOf(
            DevelopmentModeConfigurationFloatValue(
                sharedPreferencesKey,
                0f
            )
        )
        val developmentModeRepository = DevelopmentModeRepositorySharedPreferences(
            sharedPreferences,
            developmentModeConfiguration
        )

        // When
        developmentModeRepository.save(sharedPreferencesKey, 10f)

        // Then
        val result = developmentModeRepository.getFloat(sharedPreferencesKey)
        Assert.assertEquals(10f, result)
    }

    @Test
    fun setStringValue() {
        // Given
        val sharedPreferencesKey = "my_string"
        every { developmentModeConfiguration.stringValues } returns listOf(
            DevelopmentModeConfigurationStringValue(
                sharedPreferencesKey,
                ""
            )
        )
        val developmentModeRepository = DevelopmentModeRepositorySharedPreferences(
            sharedPreferences,
            developmentModeConfiguration
        )

        // When
        developmentModeRepository.save(sharedPreferencesKey, "my_value")

        // Then
        val result = developmentModeRepository.getString(sharedPreferencesKey)
        Assert.assertEquals("my_value", result)
    }
}
