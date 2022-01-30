package com.vipulasri.pelm.validator.rules

import android.telephony.PhoneNumberUtils
import com.vipulasri.pelm.R
import com.vipulasri.pelm.validator.base.Validation
import com.vipulasri.pelm.validator.base.ValidationRule


/**
 * Created by Vipul Asri on 30/01/22.
 */

class EmptyPhoneValidationRule : EmptyFieldValidationRule(R.string.error_message_empty_phone)

class PhoneLengthValidationRule : ValidationRule {

    private val minLength = 5
    private val maxLength = 15

    override val errorMessageRes: Int
        get() = R.string.error_message_phone_length

    override fun validate(value: String): Validation {
        return prepareValidationResult(value.length in minLength..maxLength)
    }

}

class PhoneValidationRule : ValidationRule {

    override val errorMessageRes: Int
        get() = R.string.error_message_valid_phone

    override fun validate(value: String): Validation {
        return prepareValidationResult(PhoneNumberUtils.isGlobalPhoneNumber(value))
    }

}