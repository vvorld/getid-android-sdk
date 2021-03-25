# GetID Android SDK

## Table of contents
*   [Overview](#overview)
*   [Getting started](#getting-started)
    *   [Requirements](#requirements)
    *   [Obtaining an SDK key](#obtaining-an-sdk-key)
    *   [Camera usage description](#camera-usage-description)
*   [Installation](#installation)
*   [Usage](#usage)
    *   [Starting the flow](#starting-the-flow)
    *   [Profile data](#profile-data)
    *   [Metadata](#metadata)
    *   [Locale](#locale)
    *   [Handling callbacks](#handling-callbacks)
    *   [Possible errors](#possible-errors)
*   [Localisation](#localisation)

## Overview
The SDK provides a set of screens for capturing identity documents, face photos, profile data, and for performing the liveness check. After capturing the data the SDK uploads it to the GetID server.

The SDK does not provide methods for obtaining verification results. Use GetID API on your backend to get ones.

This document describes how to use the version `2.0.0` or newer. The documentation for older versions is [here](documentation/README-v1.md).

## Getting started
### Requirements
- Android studio 3.5+
- Android sdk version 21+

### Obtaining an SDK key
In order to start using GetID SDK, you will need an `SDK KEY` and `API URL`.
Both can be found and modified either through your GetID Dashboard or via contacting our
[integration support](mailto:support@getid.ee?subject=[GitHub]%20Obtaining%20GetID%20credentials).

Note: In your GetID Dashboard, you can get and set `API KEY` and `SDK KEY`. `API KEY` grants you access to public API calls and SDK API calls. `SDK KEY` grants you access to SDK API calls only. For security reasons, strongly recommended using the `SDK KEY` in your SDK.

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
    implementation "ee.getid:getidlib:2.0.0"
}
```

And add to AndroidManifest.xml lines:

```
<activity
    android:name="com.sdk.getidlib.ui.activity.GetIdActivity"
    android:screenOrientation="portrait" />
```

## Usage
Before you start please go to GetID Admin Panel and create a flow (Flows > Add new flow).

### Starting the flow
There are two ways to start the verification flow: using the `SDK KEY` or using a `JWT`. We recommend using JWT in the production environment. But during the development, you can use `SDK KEY`, because it's a little bit more convenient.

Then call `GetIDSDK().startVerificationFlow()` method from the place in your code that responds to starting the verification flow. For example, it can be a handler of a button touch event.

**Kotlin**

```kotlin
GetIDSDK().startVerificationFlow(
    context = applicationContext,
    apiUrl = "API_URL",
    auth = Key("SDK_KEY"),
    flowName = "FLOW_NAME"
)
```
or

**Java**

```java
new GetIDSDK().startVerificationFlow(
                getApplicationContext(),
                "API_URL",
                new Key("SDK_KEY", null),
                "FLOW_NAME",
                null,
                null,
                null,
                null
        );
```

To start the verification flow using a `JWT`, your app should obtain the token from your backend.
Your backend should have the `SDK KEY` to request the token from GetID server. Don't store `SDK KEY` inside the app in the production environment.

To test starting the flow using a `JWT`, you can obtain one. To obtain a `JWT` make a POST request on your `API URL` with `SDK KEY` in the header:
```bash
$ curl -H "Content-Type: application/json" -H "x-sdk-key: SDK_KEY" -X POST API_URL/sdk/v2/token
```

Then pass the received token to `GetIDSDK().startVerificationFlow()` method:

**Kotlin**

```kotlin
GetIDSDK().startVerificationFlow(
    context = applicationContext,
    apiUrl = "API_URL",
    auth = Token("JWT"),
    flowName = "FLOW_NAME"
)
```
or

**Java**

```java
new GetIDSDK().startVerificationFlow(
                getApplicationContext(),
                "API_URL",
                new Token("JWT"),
                "FLOW_NAME",
                null,
                null,
                null,
                null
        );
```
### Profile data
If you have some information about the user before the verification flow started, you can pass it to the SDK as `profileData`.

The SDK can use this data to prefill the form if the flow contains the `Profile Data` step. The user can edit this information while filling out the form.

If the form does not contain a `profileData` field (or there is no `Profile Data` step in the flow) then this field will be sent to the GetID server without the user's edit.

**Kotlin**

```kotlin
GetIDSDK().startVerificationFlow(
    context = applicationContext,
    apiUrl = "API_URL",
    auth = Token("JWT"),
    flowName = "FLOW_NAME",
    profileData = mapOf("first-name" to "John", "gender" to "male")
)
```
or

**Java**

```java
Map<String,String> profileData = new HashMap<>();
     profileData.put("first-name", "John");
     profileData.put("gender", "male");

new GetIDSDK().startVerificationFlow(
                getApplicationContext(),
                "API_URL",
                new Token("JWT"),
                "FLOW_NAME",
                null,
                null,
                profileData,
                null
        );
```
You can find more details on field names and their format in [this document](documentation/ProfileData.md).

### Metadata
You can attach some metadata to a verification.

**Kotlin**

```kotlin
GetIDSDK().startVerificationFlow(
    context = applicationContext,
    apiUrl = "API_URL",
    auth = Token("JWT"),
    flowName = "FLOW_NAME",
    metadata = Metadata(labels = mapOf("department" to "EST"))
)
```
or

**Java**

```java
Map<String,String> metadata = new HashMap<>();
profileData.put("department", "EST");

new GetIDSDK().startVerificationFlow(
                getApplicationContext(),
                "API_URL",
                new Token("JWT"),
                "FLOW_NAME",
                new Metadata(null, metadata),
                null,
                null,
                null
        );
```

### Locale
By default, the SDK gets the list of device's preferred languages and chooses the best match from the list of [supported languages](#localisation). So you don't have to set up the language of the verification flow's UI.

But if you want to override the default behavior by some reason, then pass `locale` to `GetIDSDK().startVerificationFlow()` method.

**Kotlin**

```kotlin
GetIDSDK().startVerificationFlow(
    context = applicationContext,
    apiUrl = "API_URL",
    auth = Token("JWT"),
    flowName = "FLOW_NAME",
    locale = "et"
)
```

or

**Java**

```java
new GetIDSDK().startVerificationFlow(
                getApplicationContext(),
                "API_URL",
                new Token("JWT"),
                "FLOW_NAME",
                null,
                "et",
                null,
                null
        );
```
### Handling callbacks
If you want to handle the verification process completion then pass `eventListener` to `GetIDSDK().startVerificationFlow()` method.

| Callback | Description |
| ----- | ----- |
| `verificationFlowStart()` | Tells the callback that the verification flow has been successfully started. |
| `verificationFlowComplete(...)` | Tells the callback that the user has completed the verification flow. The `applicationId` property of `application` parameter can be used for getting the verification status. |
| `verificationFlowCancel()` | Tells the callback that the user has cancelled the verification flow. |
| `verificationFlowFail(...)` | Tells the callback that the verification flow has been failed. |

Here is an example of handling GetID SDK callbacks:

**Kotlin**

```kotlin
GetIDSDK().startVerificationFlow(
    context = applicationContext,
    apiUrl = "API_URL",
    auth = Token("JWT"),
    flowName = "FLOW_NAME",
    locale = "et"
)

private fun initEventListeners() = object : BroadcastReceiverListener {
        override fun verificationFlowStart() {
            Log.d("GetIdTag", "GetID flow has been started.")
        }

        override fun verificationFlowCancel() {
            Log.d("GetIdTag", "GetID flow has been cancelled.")
        }

        override fun verificationFlowFail(error: GetIDError) {
            Log.d("GetIdTag", "GetID flow has been completed with error:" + error.name)
        }

        override fun verificationFlowComplete(application: GetIDApplication) {
            Log.d("GetIdTag", "GetID flow has been completed, applicationId:" + application.applicationId)
        }
    }
```

### Possible errors
Errors that can occur in GetID SDK are GetIDError enum, see the list of all possible cases in the tables below.

| `GetIDError` | Description |
| ----- | ----- |
| `INVALID_KEY` | Invalid `SDK_KEY`. |
| `INVALID_TOKEN` | Invalid `JWT`. Maybe, it has been expired. |
| `FLOW_NOT_FOUND` | There is no flow with the name you passed as `flowName`. See all the possible names in GetID Dashboard, at the `Flows` tab. |
| `UNSUPPORTED_SCHEMA_VERSION` | It means that the SDK version is outdated. Note: `schemaVersion != sdkVersion`. |
| `CUSTOMER_ID_ALREADY_EXIST` | An application with this `customerId` already exists. |
| `TOKEN_EXPIRED` | The token has been expired. |
| `DENY_PERMISSION` | Permission has been rejected. |
| `UNSUPPORTED_LIVENESS_VERSION` | It means that the SDK version is outdated. |
| `INVALID_LIVENESS_TOKEN` | Invalid token for liveness. |
| `FAILED_TO_SEND_APPLICATION` | The SDK failed to send the captured data to the server (because of an network error, for example). |
| `UNEXPECTED_ERROR` | Other errors. |

## Localisation
GetID Android SDK loads translations from the server at launch. The SDK also gets the user's preferred languages list from the operating system. Then the SDK compares this list with available translations and picks the best match.

It works automatically and doesn't require any additional configuration.

The list of supported languages:
- English (`en`)
- German (`de`)
- French (`fr`)
- Spanish (`es_ES`)
- Russian (`ru`)
- Portuguese (`pt_PT`)
- Brazilian Portuguese (`pt_BR`)
- Italian (`it`)
- Polish (`pl`)
- Dutch (`nl`)
- Greek (`el`)
- Bulgarian (`bg`)
- Romanian (`ro`)
- Hungarian (`hu`)
- Slovenian (`sl`)
- Bosnian (`bs`)
- Albanian (`sq`)
- Estonian (`et`)
- Lithuanian (`lt`)
- Latvian (`lv`)
