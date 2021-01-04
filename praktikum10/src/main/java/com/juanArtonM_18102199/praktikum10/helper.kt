package com.juanArtonM_18102199.praktikum10

import android.database.Cursor
import android.icu.text.SimpleDateFormat
import com.juanArtonM_18102199.praktikum10.data.Quote
import com.juanArtonM_18102199.praktikum10.db.DatabaseContract
import java.util.*
import kotlin.collections.ArrayList

object helper {
    var categoryList = arrayOf(
        "Motivasi",
        "Persahabatan",
        "Percintaan",
        "Keluarga",
        "Musik",
        "Film"
    )
    const val EXTRA_QUOTE = "extra_quote"
    const val EXTRA_POSITION = "extra_position"
    const val REQUEST_ADD = 100
    const val RESULT_ADD = 101
    const val REQUEST_UPDATE = 200
    const val RESULT_UPDATE = 201
    const val RESULT_DELETE = 301
    const val ALERT_DIALOG_CLOSE = 10
    const val ALERT_DIALOG_DELETE = 20
    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<Quote> {
        val quotesList = ArrayList<Quote>()
        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.QuoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.QuoteColumns.TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.QuoteColumns.DESCRIPTION))
                val category = getString(getColumnIndexOrThrow(DatabaseContract.QuoteColumns.CATEGORY))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.QuoteColumns.DATE))
                val oleh = getString(getColumnIndexOrThrow(DatabaseContract.QuoteColumns.OLEH))
                val rating = getString(getColumnIndexOrThrow(DatabaseContract.QuoteColumns.RATING))
                quotesList.add(Quote(id, title, description,category, date, oleh, rating))
            }
        }
        return quotesList
    }
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}