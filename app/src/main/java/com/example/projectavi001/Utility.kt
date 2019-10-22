package com.example.projectavi001

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.lang.Exception
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by abhilashgupta on Sep, 2019
 */

fun <T> nonDeadlockLazy(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.PUBLICATION, initializer)

inline fun <T: Fragment> T.withArgs(
    argsBuilder: Bundle.() -> Unit): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }

fun getCurrentDate(): String {
    val date = Calendar.getInstance().time
    val dateFormatter = DateFormat.getDateInstance()
    return dateFormatter.format(date)
}

fun formatDate(timeStamp: Long): String {
    val dateFormatter = DateFormat.getDateInstance()
    val date = Date(timeStamp)
    return dateFormatter.format(date)
}

fun formDate(year: Int, month: Int, dayOfMonth: Int): String {
    val calender = Calendar.getInstance()
    calender.set(year, month, dayOfMonth)
    return formatDate(calender.timeInMillis)
}

fun getCurrentDateInMillis(date: String): Long{
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
    return formatter.parse(date).time
}

fun hideKeyboard(activity: Activity?, context: Context?) {
    val mInputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    mInputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
}