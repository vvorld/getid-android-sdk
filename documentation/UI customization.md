## UI customization

You can customize some colors, logo used in the SDK. Create an instance of the `DesignColorSchema` class, change its properties and pass it to the property `design` of the `ConfigurationPreset` class.

- `backgroundColorHex` - The background color of all screens
- `accentColorHex` - The color of graphic elements such as guides on the camera screen and other.
- `placeholdeColorHex` - The color of the placeholders on the form screen.
- `textColorHex` - The primary text color.
- `infoTextColorHex` - The text color of info messages.
- `buttonBackgroundColorHex` - The background color of the main action button at the bottom of the screen.
- `buttonTextColorHex` - The text color of the main action button at the bottom of the screen.
- `tickColorHex` - The color of the tick symbol in checkmark views.
- `toolbarColorHex` - The color of the view items in toolbar.
- `toolbarTextColorHex` - The text color in toolbar.
- `logoDrawableRes` - The image shown on the consent screen.

**Kotlin**

```kotlin
val design = DesignColorSchema(
    backgroundColorHex = "#2072E1",
    accentColorHex = "#1A5BB4",
    placeholdeColorHex = "#FFFFFF",
    textColorHex = "#002F5F",
    logoDrawableRes = R.drawable.ic_logo_sdk
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
    null,
    null,
    null,
    null,
    R.drawable.ic_logo_sdk);
```
