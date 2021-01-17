package com.juanArtonM_18102199.praktikum10.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quote(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var category: String? = null,
    var date: String? = null,
    var oleh: String? = null,
    var rating: String? = null
) : Parcelable