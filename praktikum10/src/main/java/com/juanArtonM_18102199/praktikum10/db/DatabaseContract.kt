package com.juanArtonM_18102199.praktikum10.db

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class QuoteColumns : BaseColumns {
        companion object {
            const val TABLE_QUOTE = "quote"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val CATEGORY = "category"
            const val DATE = "date"
            const val OLEH = "oleh"
            const val RATING = "rating"
        }
    }
}