package fr.bowser.dev.develoment_mode.internal

import fr.bowser.dev.develoment_mode.DevelopmentMode
import fr.bowser.dev.develoment_mode.configuration.DevelopmentModeConfiguration
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DevelopmentModeImplTest {

    @MockK
    private lateinit var developmentModeConfiguration: DevelopmentModeConfiguration

    @MockK
    private lateinit var developmentModeRepository: DevelopmentModeRepository

    @MockK
    private lateinit var actionListener: DevelopmentMode.ActionListener

    @MockK
    private lateinit var valueChangeListener: DevelopmentMode.ValueChangeListener

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun beNotifiedOfTriggeredAction() {
        // Given
        val developmentMode = DevelopmentModeImpl(
            developmentModeRepository,
            developmentModeConfiguration
        )
        developmentMode.addActionListener(actionListener)
        val key = "my_action"

        // When
        developmentMode.actionTriggered(key)

        // Then
        verify { actionListener.onActionTriggered(key) }
    }

    @Test
    fun beNotifiedOfIntValueChange() {
        // Given
        val developmentMode = DevelopmentModeImpl(
            developmentModeRepository,
            developmentModeConfiguration
        )
        developmentMode.addValueChangeListener(valueChangeListener)
        val key = "my_int"

        // When
        developmentMode.setInt(key, 10)

        // Then
        verify { valueChangeListener.onIntValueChanged(key, 10) }
    }
}