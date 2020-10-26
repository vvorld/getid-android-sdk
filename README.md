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
}

dependencies {
    implementation 'ee.getid:getidlib:1.5.1'
}
```

And add to AndroidManifest.xml lines:

```
<activity
    android:name="com.sdk.getidlib.ui.activity.GetIdActivity"
    android:screenOrientation="portrait" />
```

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

-[Flow](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Flow.md)

-[Changing flow content](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Changing%20flow%20content.md)

-[Selfie config setup](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Selfie%20config%20setup.md)

-[Acceptable documents setup](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Acceptable%20documents%20setup.md)

-[Thank you config setup](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Thank%20you%20config%20setup.md)

-[Verification types](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Verification%20types.md)

-[Permissions](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Permissions.md)

-[UI customization](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/UI%20customization.md)

-[SDK Events](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/SDK%20Events.md)

-[Localization](https://github.com/vvorld/getid-android-sdk/blob/master/documentation/Localization.md)
