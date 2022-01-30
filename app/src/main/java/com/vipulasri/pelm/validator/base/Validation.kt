package com.vipulasri.pelm.validator.base

import androidx.annotation.StringRes

/**
 * Created by Vipul Asri on 30/01/22.
 */

sealed class Validation {
    object Success : Validation()
    class Error(@StringRes val errorRes: Int) : Validation()
}

fun Validation.isSuccess() = this is Validation.Success