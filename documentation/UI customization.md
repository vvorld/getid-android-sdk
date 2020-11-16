## UI customization

You can customize some colors, logo used in the SDK. Create an instance of the `DesignColorSchema` class, change its properties and pass it to the property `design` of the `ConfigurationPreset` class.

- `backgroundColorHex` - The background color of all screens
- `primaryColorHex` - The primary color
- `primaryDarkColorHex` - The dark primary color
- `textColorHex` - The primary text color.
- `logoDrawableRes` - The image shown on the consent screen.
- `accentOpacityColorHex` - The color of graphic elements such as guides on the camera screen and other.
- `isLightTheme` - Set true for set custom design.


**Kotlin**

```kotlin
val design = DesignColorSchema(
    backgroundColorHex = "#2072E1",
    primaryColorHex = "#1A5BB4",
    primaryDarkColorHex = "#FFFFFF",
    textColorHex = "#002F5F",
    logoDrawableRes = R.drawable.ic_logo_sdk,
    isLightTheme = true
)
```

**Java**

```java
DesignColorSchema design = new DesignColorSchema(
    "#2072E1",
    "#1A5BB4",
    "#FFFFFF",
    "#002F5F",
    null,
    R.drawable.ic_logo_sdk,
    true);
```
