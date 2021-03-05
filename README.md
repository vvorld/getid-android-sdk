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

In the `build.gradle` of project you want to use this package:

```
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'ee.getid:getidlib:1.8.1'
}
```

And add to AndroidManifest.xml lines:

```
<activity
    android:name="com.sdk.getidlib.ui.activity.GetIdActivity"
    android:screenOrientation="portrait" />
```

## Usage

Then initialize a `GetIDFactory` using the `url` and`token`.

You have to get token from server. Please use request POST API_URL/sdk/v1/token and Headers: "Content-Type: application/json", "apikey: SDK_KEY".

**Kotlin**

```kotlin

val config = ConfigurationPreset().apply {
            flowItems = listOf(FlowScreens.SCREEN_CONSENT, FlowScreens.SCREEN_FORM, FlowScreens.SCREEN_DOCUMENT,
            FlowScreens.SCREEN_SELFIE, FlowScreens.SCREEN_LIVENESS)
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

ConfigurationPreset configPreset = new ConfigurationPreset(
                    flowItems,
                    null,
                    null,
                    null,
                    null,
                    null,
                    verificationTypes,
                    null,
                    null);

List<Locale> locale = new ArrayList<>();
locale.add(Locale.ENGLISH);

GetIDFactory getIDFactory = new GetIDFactory();
getIDFactory.setup(appContext, configPreset, "YOUR_TOKEN", "YOUR_URL", locale, null);
```

Note: you must disable "Don't keep activities" option in Developers options of device

## Customization

-[Flow](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Flow.md)

-[Changing flow content](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Changing%20flow%20content.md)

-[Selfie config setup](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Selfie%20config%20setup.md)

-[Acceptable documents setup](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Acceptable%20documents%20setup.md)

-[Metadata](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Metadata.md)

-[Video recording step](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Video%20recording%20config%20setup.md)

-[Thank you config setup](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Thank%20you%20config%20setup.md)

-[Verification types](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Verification%20types.md)

-[Permissions](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Permissions.md)

-[UI customization](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/UI%20customization.md)

-[SDK Events](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/SDK%20Events.md)

-[Localization](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Localization.md)
