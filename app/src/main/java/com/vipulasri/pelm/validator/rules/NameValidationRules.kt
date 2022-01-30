package com.vipulasri.pelm.validator.rules

import androidx.core.text.trimmedLength
import com.vipulasri.pelm.R
import com.vipulasri.pelm.validator.base.Validation
import com.vipulasri.pelm.validator.base.ValidationRule

/**
 * Created by Vipul Asri on 30/01/22.
 */

class EmptyNameValidationRule : EmptyFieldValidationRule(R.string.error_message_empty_name)

class NameLengthValidationRule : ValidationRule {

    private val minCharacters = 4

    override val errorMessageRes: Int
        get() = R.string.error_message_name_length

    override fun validate(value: String): Validation {
        return prepareValidationResult(value.trimmedLength() >= minCharacters)
    }

}

class NameValidationRule : ValidationRule {

    override val errorMessageRes: Int
        get() = R.string.error_message_valid_name

    override fun validate(value: String): Validation {
        return prepareValidationResult(value.all { it.isLetter() || it == ' ' })
    }

}