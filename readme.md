[![Build Status](https://travis-ci.org/bowserf/development-mode.svg?branch=master)](https://travis-ci.org/bowserf/development-mode)
[ ![Download](https://api.bintray.com/packages/bowserf/DevelopmentMode/fr.bowser.dev.development-mode/images/download.svg) ](https://bintray.com/bowserf/DevelopmentMode/fr.bowser.dev.development-mode/_latestVersion)

# Development Mode library

Library to build custom behaviors inside your debug application.

## Goal

<img
    src="https://raw.githubusercontent.com/bowserf/development-mode/master/resources/develop_mode_screen_all_types.png"
    align="left"
    width="270"
    height="555"
    title="Development mode screen with all customizations">

In "debug" build, the library add a "development mode" activity whose UI is auto generated based on the customization you defined. This activity can be start with a dedicated icon launcher.

Based on a system of "key" / "value", you can get back values you set in the "development mode" activity to customize your application behavior.

Example: If your application has an onboarding, you can choose to skip it based on a boolean value. Inside the "development mode" activity, you can check/uncheck a CheckBox to change the value of this boolean and adapt the app experience.

You can also link actions to a click in the "development mode" activity.

Example: If your application has an onboarding which is only displayed at the first app launch, you can reset your logic which keep the information that you already seen your onboarding in order to display it the next time you start your app.

## How to

### Import the library

```
debugImplementation("fr.bowser.dev:develoment-mode-debug:0.0.1")
releaseImplementation("fr.bowser.dev:develoment-mode:0.0.1")
```

The debug library version provides the "development mode" activity and a new icon launcher.

The release library version doesn't have this activity and the icon so you have nothing to disable before you release a new version of your app.

### Library initialization

You have to initialize the library in your `Application`.

To do this, call:

```kotlin
val developmentMode = DevelopmentModeModule.initialize(context, developmentModeConfiguration)
```

### Define what will be customizable

The library provides a custom DSL to easily define what customization you need for your application.

To do this, you must build a `DevelopmentModeConfiguration`:

```kotlin
developmentModeConfiguration {
	...
}
```

#### Key / value customization

For each customization, you have to define inside `developmentModeConfiguration`:
- its type
- the associated key
- a default value.

Each type will be represented by a dedicated view:

- `booleanValue` -> CheckBox
- `intValue` -> EditText with input type `number`
- `longValue` -> EditText with input type `number`
- `floatValue` -> EditText with input type `decimal number`
- `stringValue` -> EditText with input type `text`.

```kotlin
developmentModeConfiguration {
    booleanValue {
        key = "enable_onboarding"
        defaultValue = true
    }
    intValue {
        key = "default_user_age"
        defaultValue = 21
    }
    longValue {
        key = "onboarding_animation_duration"
        defaultValue = 3_000L
    }
    floatValue {
        key = "user_profile_completion"
        defaultValue = 0.5f
    }
    stringValue {
        key = "default_user_name"
        defaultValue = "Bob"
    }
    ...
}
```

###### Get selected value and be notified of changes

You can get customized value by passing the associated key to one of the following method:

- `fun getInt(key: String): Int`
- `fun getLong(key: String): Long`
- `fun getFloat(key: String): Float`
- `fun getString(key: String): String`
- `fun getBoolean(key: String): Boolean`

You can also be notified of a value change by attaching a `Development.ValueLisener`. Methods have default empty implementations so you only need to override methods you want to listen:
```kotlin
developmentMode.addValueChangeListener(createValueChangeListener())

private fun createValueChangeListener() = object : DevelopmentMode.ValueChangeListener {
    override fun onIntValueChanged(key: String, value: Int) {}
    override fun onLongValueChanged(key: String, value: Long) {}
    override fun onFloatValueChanged(key: String, value: Float) {}
    override fun onStringValueChanged(key: String, value: String) {}
    override fun onBooleanValueChanged(key: String, value: Boolean) {}
}
```

#### Actions

An "action" type only contains a "key" value.

Visualy, it's represented by a button in the "development mode" activity.

```kotlin
developmentModeConfiguration {
    action {
        key = "reset_onboarding"
    }
    ...
}
```

###### Be notified of action button clicks

You can be notified of a click and trigger some actions in your application.

Attach an `DevelopmentMode.ActionListener` to be notified:

```kotlin
developmentMode.addActionListener(createActionListener())

private fun createActionListener() = object : DevelopmentMode.ActionListener {
    override fun onActionTriggered(key: String) {
        if (key == "reset_onboarding") {
        	// reset the onboarding
        }
    }
}
```

### Default activity

Because the library defines a new activity with intent filter:
```xml
<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```

You must define your firt app activity as the default one to start it when you launch your app from Android Studio:
```xml
<activity android:name=".YourFirstActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

		<category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```