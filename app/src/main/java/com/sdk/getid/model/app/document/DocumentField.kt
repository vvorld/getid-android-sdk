package com.sdk.getid.model.app.document

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DocumentField(
    var title: String = "",
    var valueType: DocumentType? = null,
    var isActive: Boolean = false,
    var value: String = ""
) : Parcelable
