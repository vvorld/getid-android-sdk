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
GetIDFactory.setup("YOUR_API_KEY", "YOUR_URL", ConfigurationPreset())
```

**Java**

```java
GetIDFactory.setup("YOUR_API_KEY", "YOUR_URL", new ConfigurationPreset());
```

## Customization

### Flow

You can customize the SDK flow. Create an instance of the `Configuration` class, change its properties and pass it to the function `newInstance` of `AddDocumentActivity`class.

- `flowItems` -  specifies the screens to be displayed and their order. 
- `formFields` -  specifies the fields to be displayed on the form screen.
- `acceptableCountries` -  specifies a list of countries whose documents are accepted for verification. 
- `acceptableDocumentTypes` -  specifies a list of document types accepted for verification. 

**Kotlin**

```kotlin
val configPreset = ConfigurationPreset(
    flowItems = listOf(SCREEN_CONSENT, SCREEN_SELFIE, SCREEN_DOCUMENT, SCREEN_THANKS),
    formFields = listOf(FormField("Birth place", FormValueType.COUNTRY))
)

GetIDFactory.setup("YOUR_API_KEY", "YOUR_URL", configPreset)
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

GetIDFactory.setup("YOUR_API_KEY", "YOUR_URL", configPreset);
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

### Acceptable countries setup

You can limit the list of issuing countries, also you need to add list of ISO 3166-1 alpha-2 codes to `acceptableCountries` property of `ConfigurationPreset`.

### Acceptable documents setup

Also you can limit the list of acceptable documents. In order to set acceptable document types one should passing there an list of `DocumentType` objects to `acceptableDocumentTypes` property of `ConfigurationPreset`. 

Supported document types are:

-  ID_CARD
- DRIVING_LICENCE
- PASSPORT
- RESIDENCE_PERMIT

## Localization

GetID Android SDK contains translations for the following locales:

- English
- Russian

It depend on phone language. If you need translations for some other locales, but we don't provide yet, please contact us through [support@getid.ee](mailto:support@getid.ee).