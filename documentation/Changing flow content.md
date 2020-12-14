### Changing flow content

You can specify which screens should be displayed in the flow and the order of them. In order to do that assign a non-empty list of `FlowScreens` objects to `flowItems` property of `ConfigurationPreset`. All duplicate items in `flowItems` list are ignored. The possible values are:

- SCREEN_CONSENT
- SCREEN_FORM
- SCREEN_DOCUMENT
- SCREEN_LIVENESS

- SCREEN_SELFIE

- SCREEN_THANKS

The default value are list of items SCREEN_CONSENT, SCREEN_FORM, SCREEN_THANKS .

### Form screen setup

The SDK provides a customizable form screen. You need to add list of `FormField` objects to `formFields` property of `ConfigurationPreset`.

To create a `FormField` object one should pass the field title and its value type to the constructor:

```kotlin
FormField("Birth place", FormValueType.COUNTRY)
```

Supported value types:

- TEXT - Plain `String`
- DATE - Choice date in `date picker`
- SEX - Choice default value `"male"` or `"female"`
- COUNTRY - `String` in `ISO 3166-1 alpha-2` format
- CUSTOM - Choice custom values in picker

To create a `FormField` object one should pass custom values in constructor:

```kotlin
FormField(title = "Profession", valueType = FormValueType.CUSTOM, customValues = listOf("Developer", "Designer", "QA"))
```

Also, `FormField` object has `isOptional` and `isHidden` flags (false by default).

To create a `FormField` object one should pass the field flag to the constructor:

```kotlin
FormField(valueType = FormValueType.TEXT, isOptional = true)
or
FormField(value = "Birth place", valueType = FormValueType.TEXT, isHidden = true)
```
