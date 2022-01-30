package com.vipulasri.pelm.validator.base

/**
 * Created by Vipul Asri on 30/01/22.
 */

interface ValidationRule {

    val errorMessageRes: Int
    fun validate(value: String): Validation

    fun prepareValidationResult(condition: Boolean): Validation {
        return if (condition) Validation.Success else Validation.Error(errorMessageRes)
    }
}