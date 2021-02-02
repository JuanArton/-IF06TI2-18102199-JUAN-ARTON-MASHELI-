package com.juanArtonM_18102199.praktikum9

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SettingModel (
    var name: String? = null,
    var email: String? = null,
    var age: Int = 0,
    var phoneNumber: String? = null,
    var isDarkTheme: Boolean = false,
    var univ: String? = null,
    var slogan: String? = null
): Parcelable