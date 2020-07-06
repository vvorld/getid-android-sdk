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