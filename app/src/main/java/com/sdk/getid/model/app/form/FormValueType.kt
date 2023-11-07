package com.sdk.getid.model.app.form

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
enum class FormValueType : Parcelable {
    TEXT,
    DATE,
    SEX,
    COUNTRY
}
