# External IDs

There are two different external IDs that can be used to link a verification with a user in your system: `customerId` and `externalId`.

`customerId` can be passed while obtaining a JWT at the backend:
```bash
curl -d '{"customerId":"a-customer-id"}' -H "Content-Type: application/json"  -H "x-sdk-key: SDK_KEY"  -X POST API_URL/sdk/v2/token
```
The received JWT can be used only for one verification.

`externalId` can be passed to the SDK while calling `startVerificationFlow` method:
```kotlin
GetIDSDK().startVerificationFlow(
    context = applicationContext,
    apiUrl = "API_URL",
    auth = Token("JWT"),
    flowName = "FLOW_NAME",
    metadata = Metadata(externalId = "an-external-id")
)
```
The same `externalId` can be used in multiple verifications. For example, if a user failed the first verification, you can use the same `externalId` in the next one.