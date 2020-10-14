@file:JvmName("Utils")
@file:JvmMultifileClass

package com.sandeep.digitalmanager.extension

import android.util.Patterns

fun String?.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidMobileNumber(): Boolean {
    return this.length == 10
}

fun String.isValidPinCode(): Boolean {
    return this.length == 6
}
