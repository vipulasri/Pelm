package com.vipulasri.pelm.validator.rules

import android.util.Patterns
import com.vipulasri.pelm.R
import com.vipulasri.pelm.validator.base.Validation
import com.vipulasri.pelm.validator.base.ValidationRule

/**
 * Created by Vipul Asri on 30/01/22.
 */

class EmptyEmailValidationRule : EmptyFieldValidationRule(R.string.error_message_empty_email)

class EmailValidationRule : ValidationRule {

    private val pattern = Patterns.EMAIL_ADDRESS

    override val errorMessageRes: Int
        get() = R.string.error_message_valid_email

    override fun validate(value: String): Validation {
        return prepareValidationResult(pattern.matcher(value.trim()).matches())
    }

}