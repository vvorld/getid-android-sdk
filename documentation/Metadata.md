You can attach some metadata to a verification. In order to do that pass a non-empty `metadata` map to `ConfigurationPreset` object.

**Kotlin**

```kotlin

val config = ConfigurationPreset().apply {
            flowItems = listOf(FlowScreens.SCREEN_CONSENT, FlowScreens.SCREEN_FORM, FlowScreens.SCREEN_DOCUMENT,
            FlowScreens.SCREEN_SELFIE, FlowScreens.SCREEN_LIVENESS)
            metadata = hashMapOf("dapartment" to "EST")
            verificationTypes = arrayListOf(VerificationTypesEnum.FACE_MATCHING, VerificationTypesEnum.DATA_EXTRACTION)
        }

GetIDFactory.setup(appContext, config, "YOUR_TOKEN", "YOUR_URL", listOf(Locale.ENGLISH))
```

or

**Java**

```java
List<FlowScreens> flowItems = new ArrayList<>();
            flowItems.add(FlowScreens.SCREEN_CONSENT);
            flowItems.add(FlowScreens.SCREEN_FORM);
            flowItems.add(FlowScreens.SCREEN_DOCUMENT);
            flowItems.add(FlowScreens.SCREEN_SELFIE);
            flowItems.add(FlowScreens.SCREEN_LIVENESS);

ArrayList<VerificationTypesEnum> verificationTypes = new ArrayList<>();
verificationTypes.add(VerificationTypesEnum.FACE_MATCHING);
verificationTypes.add(VerificationTypesEnum.DATA_EXTRACTION);

Map<String,String> metadata = new HashMap<>();
metadata.put("dapartment","EST");

ConfigurationPreset configPreset = new ConfigurationPreset(
                    flowItems,
                    null,
                    null,
                    null,
                    null,
                    null,
                    verificationTypes,
                    metadata,
                    null);

List<Locale> locale = new ArrayList<>();
locale.add(Locale.ENGLISH);

GetIDFactory getIDFactory = new GetIDFactory();
getIDFactory..setup(appContext, configPreset, "YOUR_TOKEN", "YOUR_URL", locale, null);