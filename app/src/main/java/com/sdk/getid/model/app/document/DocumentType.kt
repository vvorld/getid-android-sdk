package com.sdk.getid.model.app.document

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Pavlo Kuchirka on 22-Oct-19.
 */

@Parcelize
enum class DocumentType : Parcelable {
    ID_CARD,
    DRIVING_LICENCE,
    PASSPORT,
    ASYLUM_RESIDENCE_PERMIT,
    RESIDENCE_PERMIT,
    VISA,
    HEALTH_INSURANCE,
    WORK_LICENSE,
    BORDER_CROSSING,
    PASSPORT_CARD
}
