### Thank you config setup

You can change text on thank you screen:

```kotlin
val configPreset = ConfigurationPreset(
    flowItems = listOf(SCREEN_CONSENT, SCREEN_SELFIE, SCREEN_DOCUMENT, SCREEN_THANKS),
    formFields = listOf(FormField("Birth place", FormValueType.COUNTRY))
    thankYouConfig = ThankYouConfig("Title", "Description", "Button title")
)

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset)
```

| Property      | Description                         |
| ------------- | ----------------------------------- |
| `title`       | The title of the screen.            |
| `detailsText` | The secondary text below the title. |
| `buttonTitle` | The button title.                   |
