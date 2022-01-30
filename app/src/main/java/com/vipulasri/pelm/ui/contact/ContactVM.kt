package com.vipulasri.pelm.ui.contact

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vipulasri.pelm.ui.base.BaseVM
import com.vipulasri.pelm.validator.Validator
import com.vipulasri.pelm.validator.base.Validation
import com.vipulasri.pelm.validator.base.isSuccess

/**
 * Created by Vipul Asri on 30/01/22.
 */

class ContactVM : BaseVM() {

    val viewState: LiveData<ContactViewState> = MutableLiveData()

    fun onSubmitClicked(name: String, email: String, phone: String) {
        val nameValidation = Validator.validateName(name)
        val emailValidation = Validator.validateEmail(email)
        val phoneValidation = Validator.validatePhone(phone)

        if (nameValidation.isSuccess() && emailValidation.isSuccess() && phoneValidation.isSuccess()) {
            viewState.setValue(ContactViewState.Success)
        } else {
            val nameErrorMessage = if (nameValidation is Validation.Error) {
                nameValidation.errorRes
            } else null

            val emailErrorMessage = if (emailValidation is Validation.Error) {
                emailValidation.errorRes
            } else null

            val phoneErrorMessage = if (phoneValidation is Validation.Error) {
                phoneValidation.errorRes
            } else null

            viewState.setValue(
                ContactViewState.Error(
                    nameErrorMessage,
                    emailErrorMessage,
                    phoneErrorMessage
                )
            )
        }
    }

}

sealed class ContactViewState {
    object Success : ContactViewState()
    class Error(
        @StringRes val name: Int?,
        @StringRes val email: Int?,
        @StringRes val phone: Int?
    ) : ContactViewState()
}