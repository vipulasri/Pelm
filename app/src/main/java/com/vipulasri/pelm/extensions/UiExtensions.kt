package com.vipulasri.pelm.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.vipulasri.pelm.databinding.LayoutErrorBinding

fun LayoutErrorBinding.showOrHide(
    show: Boolean,
    message: String? = null,
    onRetry: (() -> Unit)? = null
) {
    viewError.isVisible = show
    if (show) {
        textErrorMessage.text = message
        buttonErrorRetry.setOnClickListener {
            onRetry?.invoke()
        }
    }
}

fun Activity.hideKeyboard() {
    this.currentFocus?.let { view ->
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}