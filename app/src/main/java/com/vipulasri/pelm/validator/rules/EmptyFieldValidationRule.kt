package com.vipulasri.pelm.validator.rules

import com.vipulasri.pelm.R
import com.vipulasri.pelm.validator.base.Validation
import com.vipulasri.pelm.validator.base.ValidationRule

/**
 * Created by Vipul Asri on 30/01/22.
 */

open class EmptyFieldValidationRule(
    private val errorMessage: Int = R.string.error_message_empty_field
) : ValidationRule {

    override val errorMessageRes: Int
        get() = errorMessage

    override fun validate(value: String): Validation {
        return prepareValidationResult(value.trim().isNotEmpty())
    }

}