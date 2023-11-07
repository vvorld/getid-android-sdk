package com.sdk.getid.model.app.form

import java.util.UUID


data class FormField(
    var title: String = "Title",
    var valueType: FormValueType = FormValueType.TEXT,
    var value: String = ""
) {
    var id = UUID.randomUUID().toString()
}
