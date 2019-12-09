package com.sdk.getid.model.app.form

import java.util.*

/**
 * Created by Pavlo Kuchirka on 15-Oct-19.
 */

data class FormField(
    var title: String = "Title",
    var valueType: FormValueType = FormValueType.TEXT,
    var value: String = ""
) {
    var id = UUID.randomUUID().toString()
}
