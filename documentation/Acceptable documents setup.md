### Acceptable documents setup

You can limit the list of acceptable documents. In order to set acceptable document types one should passing there an list of `DocumentType` objects to `acceptableDocumentTypes` property of `ConfigurationPreset`.
Also, there is default key which allows to set acceptable documents for countries not listed in the dictionary.

```kotlin
CountryDocumentConfig(
  isShowGuideline = true,
  allowDocumentPhotosFromGallery = true,
  interactiveDocumentStep = true,
  acceptableDocuments = mapOf(
    listOf("cz", "dk", "es") to listOf(
      DocumentEnum.PASSPORT,
      DocumentEnum.ID_CARD,
      DocumentEnum.DRIVING_LICENCE),
    listOf("at", "be", "ua") to listOf(DocumentEnum.PASSPORT, DocumentEnum.ID_CARD)
    listOf("default") to listOf(DocumentEnum.PASSPORT, DocumentEnum.DRIVING_LICENCE)
    )
  )
```

Supported document types are:

- ID_CARD
- DRIVING_LICENCE
- PASSPORT
- RESIDENCE_PERMIT
- VOTER_CARD