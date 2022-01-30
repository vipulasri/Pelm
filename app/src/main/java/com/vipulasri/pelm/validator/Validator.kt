package com.vipulasri.pelm.validator

import com.vipulasri.pelm.validator.base.Validation
import com.vipulasri.pelm.validator.base.ValidationRule
import com.vipulasri.pelm.validator.rules.*

/**
 * Created by Vipul Asri on 30/01/22.
 */

object Validator {

    fun validateName(name: String): Validation {
        return validate(
            name,
            EmptyNameValidationRule(),
            NameLengthValidationRule(),
            NameValidationRule()
        )
    }

    fun validateEmail(email: String): Validation {
        return validate(
            email,
            EmptyEmailValidationRule(),
            EmailValidationRule()
        )
    }

    fun validatePhone(phone: String): Validation {
        return validate(
            phone,
            EmptyPhoneValidationRule(),
            PhoneLengthValidationRule(),
            PhoneValidationRule()
        )
    }

    private fun validate(value: String, vararg validations: ValidationRule): Validation {
        for (rule in validations) {
            val result = rule.validate(value)
            if (result is Validation.Error) {
                return result
            }
        }

        return Validation.Success
    }

}