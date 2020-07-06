
### Flow

You can customize the SDK flow. Create an instance of the `Configuration` class, change its properties and pass it to the function `newInstance` of `AddDocumentActivity`class.

- `flowItems` -  specifies the screens to be displayed and their order.
- `formFields` -  specifies the fields to be displayed on the form screen.
- `selfieConfig` -  specifies the fields to change time for video recording (optional).
- `acceptableCountries` -  specifies a list of countries whose documents are accepted for verification.
- `acceptableDocumentTypes` -  specifies a list of document types accepted for verification.

**Kotlin**

```kotlin
val formFieldsMap = mutableMapOf<String, List<FormFields>>()
formFieldsMap["Form"] = listOf(FormField("Birth place", FormValueType.DATE))

val configPreset = ConfigurationPreset(
    flowItems = listOf(SCREEN_CONSENT, SCREEN_SELFIE, SCREEN_DOCUMENT, SCREEN_THANKS),
    formFields = formFieldsMap
)

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset)
```

**Java**

```java
List<String> flowItems = new ArrayList<>();
flowItems.add(SCREEN_CONSENT);
flowItems.add(SCREEN_SELFIE);
flowItems.add(SCREEN_DOCUMENT);

Map<String, ArrayList<FormFields>> formFields = new HashMap<String, ArrayList<FormFields>>();
List<FormField> fields = new ArrayList<>();
fields.add(new FormField("Birth place", FormValueType.DATE));
formFields.put("Form", fields)

ConfigurationPreset configPreset = new ConfigurationPreset(
    flowItems,
    formFields,
    null,
    null,
    null);

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset);
```



Also you can create multi-screen form step with Map <`form title`, `formFields`>, where`form title` is String and `formFields`is List of `FormFields` objects.

**Kotlin**

```kotlin
val formFieldsMap = mutableMapOf<String, List<FormFields>>()
formFieldsMap["FirstForm"] = listOf(FormField("Birth place", FormValueType.DATE))
formFieldsMap["SecondForm"] = listOf(FormField("First name", FormValueType.TEXT), FormField("Last name", FormValueType.TEXT))

val configPreset = ConfigurationPreset(
    flowItems = listOf(SCREEN_CONSENT, SCREEN_SELFIE, SCREEN_DOCUMENT, SCREEN_THANKS),
    formFields = formFieldsMap
)

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset)
```

**Java**

```java
List<String> flowItems = new ArrayList<>();
flowItems.add(SCREEN_CONSENT);
flowItems.add(SCREEN_SELFIE);
flowItems.add(SCREEN_DOCUMENT);

Map<String, ArrayList<FormFields>> formFields = new HashMap<String, ArrayList<FormFields>>();
List<FormField> firstFormFields = new ArrayList<>();
firstFormFields.add(new FormField("Birth place", FormValueType.DATE));

List<FormField> secondFormFields = new ArrayList<>();
secondFormFields.add(new FormField("First name", FormValueType.TEXT));
secondFormFields.add(new FormField("Last name", FormValueType.TEXT));

formFields.put("First form", firstFormFields)
formFields.put("Second form", secondFormFields)
  
ConfigurationPreset configPreset = new ConfigurationPreset(
    flowItems,
    formFields,
    null,
    null,
    null);

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset);
```
