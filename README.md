# GetID Android SDK

## Overview

The SDK provides a set of screens to allow capturing of identity documents and face photos. In addition, the SDK provides a possibility to add a customizable screen for users to enter text information about themselves. Finally, it uploads the captured data to GetID server.

 Use GetID API on your backend to get ones.

## Getting started

### Requirements

- Android studio 3.5+
- Android sdk version 21+

### Obtaining an API key

In order to start using GetID SDK, you will need an API key. Use a `sandbox` key to test the integration. Use a `live` key in the production. You can get both keys inside your GetID Dashboard.

### Camera usage description

The SDK uses the camera for capturing photos during verification. The app is responsible for describing the reason for using the camera.

## Installation

Download library from [github](https://github.com/vvorld/getid-android-sdk)

Add the compiled AAR in Android Studio:

1. Click **File > New > New Module**.
2. Click **Import .JAR/.AAR Package** then click **Next**.
3. Enter the location of the compiled AAR or JAR file then click **Finish**.

Then put to root of your project. Also you need add library to **settings.gradle** file:

`include ':app', ':getidlib'`

and add line to app **build.gradle** dependencies:

`implementation project(':getidlib')`

## Usage

Then initialize a `GetIDFactory` using the `apiKey`, `url`.

**Kotlin**

```kotlin
GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", ConfigurationPreset())
```

**Java**

```java
GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", new ConfigurationPreset());
```

## Customization

### Flow

You can customize the SDK flow. Create an instance of the `Configuration` class, change its properties and pass it to the function `newInstance` of `AddDocumentActivity`class.

- `flowItems` -  specifies the screens to be displayed and their order. 
- `formFields` -  specifies the fields to be displayed on the form screen.
- `selfieConfig` -  specifies the fields to change time for video recording (optional).
- `acceptableCountries` -  specifies a list of countries whose documents are accepted for verification. 
- `acceptableDocumentTypes` -  specifies a list of document types accepted for verification. 

**Kotlin**

```kotlin
val configPreset = ConfigurationPreset(
    flowItems = listOf(SCREEN_CONSENT, SCREEN_SELFIE, SCREEN_DOCUMENT, SCREEN_THANKS),
    formFields = listOf(FormField("Birth place", FormValueType.COUNTRY))
)

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset)
```

**Java**

```java
List<String> flowItems = new ArrayList<>();
flowItems.add(SCREEN_CONSENT);
flowItems.add(SCREEN_SELFIE);
flowItems.add(SCREEN_DOCUMENT);

List<FormField> formFields = new ArrayList<>();
formFields.add(new FormField("Birth place", FormValueType.COUNTRY));

ConfigurationPreset configPreset = new ConfigurationPreset(
    flowItems,        
    formFields,         
    null,         
    null);

GetIDFactory.setup(appContext, "YOUR_API_KEY", "YOUR_URL", configPreset);
```

### Changing flow content

You can specify which screens should be displayed in the flow and the order of them. In order to do that assign a non-empty list of `FlowScreens` objects to `flowItems` property of `ConfigurationPreset`. All duplicate items in `flowItems` list are ignored. The possible values are:

- SCREEN_CONSENT
- SCREEN_FORM
- SCREEN_DOCUMENT

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

### Selfie config setup

You can change time (in seconds) for video recording. By default value is 3 seconds. Video recording starts at the moment when selfie screen activated. Video recording stops at the moment when selfie took. The latest N seconds of the recorded video will be saved within application.

### Acceptable countries setup

You can limit the list of issuing countries, also you need to add list of ISO 3166-1 alpha-2 codes to `acceptableCountries` property of `ConfigurationPreset`.

### Acceptable documents setup

Also you can limit the list of acceptable documents. In order to set acceptable document types one should passing there an list of `DocumentType` objects to `acceptableDocumentTypes` property of `ConfigurationPreset`. 

Supported document types are:

-  ID_CARD
- DRIVING_LICENCE
- PASSPORT
- RESIDENCE_PERMIT

## Permissions

You can request additional permissions:

-  Video-recording feature (not enabled by default)
-  Liveness

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

## SDK Events

You can catch and handle event from SDK. Create an listener of the `BroadcastReceiverListener` and pass it to `GetIDFactory.setup()` method.

```kotlin
val eventListener = object : BroadcastReceiverListener {
        override fun onReceive(event: Event) {
            when (event.eventType) {
                EventType.EVENT_COMMON -> handleEventCommon(event.eventCommon)
                EventType.EVENT_SCREEN -> handleEventScreen(event.eventScreen)
            }
        }

GetIDFactory().setup(appContext, "YOUR_API_KEY", "YOUR_URL", configuration, eventListener)
```

There are two types of events `EventType.EVENT_COMMON`, `EventType.EVENT_SCREEN` 

`EVENT_COMMON` has a `EventCommon` class model witch contains `event` field.

`event` types are:

-  `EventCommonType.ON_STOP`
-  `EventCommonType.EA_ID_ALREADY_EXISTS`

`EVENT_SCREEN` has a `EventScreen` class model witch contains of two fields: `name`, `stepPhase`

`name` types are:

-  `EventScreenType.CONSENT`
-  `EventScreenType.FORM`
-  `EventScreenType.DOCUMENT_TYPE`
-  `EventScreenType.FRONT`
-  `EventScreenType.BACK`
-  `EventScreenType.SINGLE`
-  `EventScreenType.SELFIE`
-  `EventScreenType.LIVENESS`
-  `EventScreenType.LOADING`
-  `EventScreenType.THANK_YOU`

`stepPhase` types are:

-  `EventStepPhase.STARTED`
-  `EventStepPhase.COMPLETED`
-  `EventStepPhase.GO_BACK`

## Localization

GetID Android SDK contains translations for the following locales:

- English
- Russian

It depend on phone language. If you need translations for some other locales, but we don't provide yet, please contact us through [support@getid.ee](mailto:support@getid.ee).
