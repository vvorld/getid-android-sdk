### Video recording config setup

You can change time (in seconds) for video recording. By default value is 10 seconds. Also you can change text for recognition.

```kotlin
val configPreset = ConfigurationPreset(
    flowItems = listOf(SCREEN_CONSENT, SCREEN_SELFIE, SCREEN_DOCUMENT,
 SCREEN_VIDEO_RECORDING, SCREEN_THANKS),
    formFields = listOf(FormField("Birth place", FormValueType.COUNTRY))
    videoRecordingConfig = VideoRecordingConfig("Text", 20)
)

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset)
```