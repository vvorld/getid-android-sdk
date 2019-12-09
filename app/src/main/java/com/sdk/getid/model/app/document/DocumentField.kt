package com.sdk.getid.model.app.document

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Pavlo Kuchirka on 22-Oct-19.
 */

@Parcelize
data class DocumentField(
    var title: String = "",
    var valueType: DocumentType? = null,
    var isActive: Boolean = false,
    var value: String = ""
) : Parcelable
