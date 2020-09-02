package fr.bowser.dev.develoment_mode

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import fr.bowser.dev.develoment_mode.internal.DevelopmentModeImpl

class DevelopmentModeActivity : AppCompatActivity() {

    private val inputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    private lateinit var rootContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.development_mode_activity)

        rootContainer = findViewById(R.id.debug_mode_root_container)

        val developmentMode = DevelopmentModeModule.getDevelopmentMode() as DevelopmentModeImpl
        createIntValueViews(developmentMode)
        createLongValueViews(developmentMode)
        createFloatValueViews(developmentMode)
        createStringValueViews(developmentMode)
        createBooleanValuesViews(developmentMode)
        createActionViews(developmentMode)
    }

    private fun createBooleanValuesViews(
        developmentMode: DevelopmentModeImpl,
    ) {
        developmentMode.getConfiguration().booleanValues.forEach { config ->
            val checkBox = CheckBox(this)

            checkBox.text = getTitle(config.key)
            checkBox.isChecked = developmentMode.getBoolean(config.key)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                developmentMode.setBoolean(config.key, isChecked)
            }

            checkBox.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            rootContainer.addView(checkBox)
        }
    }

    private fun createActionViews(
        developmentMode: DevelopmentModeImpl,
    ) {
        developmentMode.getConfiguration().actions.forEach { config ->
            val button = Button(this)

            button.text = getTitle(config.key)
            button.setOnClickListener {
                developmentMode.actionTriggered(config.key)
            }

            button.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            rootContainer.addView(button)
        }
    }

    private fun createIntValueViews(
        developmentMode: DevelopmentModeImpl,
    ) {
        developmentMode.getConfiguration().intValues.forEach { config ->
            val title = createTitle(config.key)
            val editText = createEditText()

            editText.setText(developmentMode.getInt(config.key).toString())
            editText.inputType = InputType.TYPE_CLASS_NUMBER

            editText.setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val value = editText.text.toString().toInt()
                    developmentMode.setInt(config.key, value)
                    hideKeyboard(view)
                    return@setOnEditorActionListener true
                }
                false
            }

            rootContainer.addView(title)
            rootContainer.addView(editText)
        }
    }

    private fun createLongValueViews(
        developmentMode: DevelopmentModeImpl,
    ) {
        developmentMode.getConfiguration().longValues.forEach { config ->
            val title = createTitle(config.key)
            val editText = createEditText()

            editText.setText(developmentMode.getLong(config.key).toString())
            editText.inputType = InputType.TYPE_CLASS_NUMBER

            editText.setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val value = editText.text.toString().toLong()
                    developmentMode.setLong(config.key, value)
                    hideKeyboard(view)
                    return@setOnEditorActionListener true
                }
                false
            }

            rootContainer.addView(title)
            rootContainer.addView(editText)
        }
    }

    private fun createStringValueViews(
        developmentMode: DevelopmentModeImpl,
    ) {
        developmentMode.getConfiguration().stringValues.forEach { config ->
            val title = createTitle(config.key)
            val editText = createEditText()

            editText.setText(developmentMode.getString(config.key))
            editText.inputType = InputType.TYPE_CLASS_TEXT

            editText.setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val value = editText.text.toString()
                    developmentMode.setString(config.key, value)
                    hideKeyboard(view)
                    return@setOnEditorActionListener true
                }
                false
            }

            rootContainer.addView(title)
            rootContainer.addView(editText)
        }
    }

    private fun createFloatValueViews(
        developmentMode: DevelopmentModeImpl,
    ) {
        developmentMode.getConfiguration().floatValues.forEach { config ->
            val title = createTitle(config.key)
            val editText = createEditText()

            editText.setText(developmentMode.getFloat(config.key).toString())
            editText.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL

            editText.setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val str = editText.text.toString()
                    try {
                        val value = str.toFloat()
                        developmentMode.setFloat(config.key, value)
                        hideKeyboard(view)
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            this,
                            "Invalid float format: \"$str\"",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@setOnEditorActionListener true
                }
                false
            }

            rootContainer.addView(title)
            rootContainer.addView(editText)
        }
    }

    private fun createEditText(): EditText {
        val editText = EditText(this)
        editText.imeOptions = EditorInfo.IME_ACTION_DONE
        editText.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return editText
    }

    private fun createTitle(key: String): TextView {
        val title = TextView(this)
        title.text = getTitle(key)
        title.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return title
    }

    private fun getTitle(key: String): String {
        return resources.getString(R.string.development_mode_key_title_format, key)
    }

    private fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}