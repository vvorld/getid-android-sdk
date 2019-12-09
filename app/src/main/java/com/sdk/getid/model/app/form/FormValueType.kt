package com.sdk.getid.model.app.form

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Pavlo Kuchirka on 15-Oct-19.
 */

@Parcelize
enum class FormValueType : Parcelable {
    TEXT,
    DATE,
    SEX,
    COUNTRY
}
