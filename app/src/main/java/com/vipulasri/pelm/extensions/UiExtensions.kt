package com.vipulasri.pelm.extensions

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