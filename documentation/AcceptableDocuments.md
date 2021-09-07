# Acceptable documents

It's possible to limit the list of acceptable documents and issuing countries. That's can be done in our Flow Configurator and at the SDK side as well. The list set in the Flow Configurator is applied to all SDKs and it's the preferable way.

But sometimes the list of acceptable documents depends on the end-user and can not be configured for all users of the flow. In this case, the list should be set at the client-side during the SDK initialization.

If a list of acceptable documents is set in both places - on the backend and in the SDK - then a document is considered as "acceptable" only if both the server and the SDK accept it.

To set the list of acceptable documents in the SDK one have to pass `acceptableDocuments` parameter to `GetIDSDK().startVerificationFlow()` method.

```kotlin
GetIDSDK().startVerificationFlow(
  context = applicationContext,
  apiUrl = "API_URL",
  auth = Token("JWT"),
  flowName = "FLOW_NAME",
  acceptableDocuments = mapOf("EST" to listOf(DocumentEnum.PASSPORT, DocumentEnum.ID_CARD), "default" to listOf(DocumentEnum.PASSPORT))
)
```

`"EST" to listOf(DocumentEnum.PASSPORT, DocumentEnum.ID_CARD)` means that Estonian passports and ID Cards are acceptable.
`"default" to listOf(DocumentEnum.PASSPORT)` means that for all countries not mentioned in the list only passports are acceptable. If you don't want to accept any documents from a specific country then pass an empty array of document types for this country. If you want to accept only documents from a definitive list of countries then don't add the `"default"` key.