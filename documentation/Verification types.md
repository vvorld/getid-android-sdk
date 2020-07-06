### Verification types

You may also set desirable types of verification. In order to do that pass a non-empty array of `verificationTypes` objects to `ConfigurationPreset`. The possible values are `FACE_MATCHING`, `DATA_EXTRACTION` and `WATCHLISTS`. The default value are list of items `FACE_MATCHING` and `DATA_EXTRACTION` .

```kotlin
val configPreset = ConfigurationPreset(
    flowItems = listOf(SCREEN_CONSENT, SCREEN_SELFIE, SCREEN_DOCUMENT, SCREEN_THANKS),
    formFields = listOf(FormField("Birth place", FormValueType.COUNTRY))
    thankYouConfig = ThankYouConfig("Title", "Description", "Button title")
    verificationTypes = arrayListOf(
        VerificationTypesEnum.FACE_MATCHING,
        VerificationTypesEnum.DATA_EXTRACTION
    )
)

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset)
```

Note: if you set `verificationTypes`, then make sure that `flowItems` contains required steps, otherwise, you'll get an error. For example, `DATA_EXTRACTION` requires `SCREEN_DOCUMENT` step in `flowItems`.
