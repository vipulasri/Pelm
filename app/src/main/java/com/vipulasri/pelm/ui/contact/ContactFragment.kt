package com.vipulasri.pelm.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.vipulasri.pelm.R
import com.vipulasri.pelm.databinding.FragmentContactBinding
import com.vipulasri.pelm.extensions.hideKeyboard
import com.vipulasri.pelm.ui.HomeActivity
import com.vipulasri.pelm.ui.base.BaseFragment
import com.vipulasri.pelm.utils.nonNull

/**
 * Created by Vipul Asri on 29/01/22.
 */

class ContactFragment : BaseFragment<FragmentContactBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactBinding
        get() = FragmentContactBinding::inflate

    private val viewModel: ContactVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        binding.buttonSubmit.setOnClickListener {
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val phone = binding.editPhone.text.toString()

            clearInputErrors()
            viewModel.onSubmitClicked(name, email, phone)
        }
    }

    private fun setupObservers() {
        viewModel.viewState.nonNull().observe(viewLifecycleOwner) { state ->
            when (state) {
                is ContactViewState.Success -> {
                    showSuccessMessage()
                }
                is ContactViewState.Error -> {
                    showValidationError(state.name, state.email, state.phone)
                }
            }
        }
    }

    private fun showSuccessMessage() {
        requireActivity().hideKeyboard()
        clearEditTexts()

        view?.let {
            Snackbar.make(
                binding.container,
                getString(R.string.success_contact_form),
                Snackbar.LENGTH_SHORT
            )
                .setAnchorView(getAnchorView())
                .show()
        }
    }

    private fun showValidationError(
        @StringRes nameErrorRes: Int?,
        @StringRes emailErrorRes: Int?,
        @StringRes phoneErrorRes: Int?
    ) {
        binding.textInputName.showFieldError(nameErrorRes)
        binding.textInputEmail.showFieldError(emailErrorRes)
        binding.textInputPhone.showFieldError(phoneErrorRes)
    }

    private fun TextInputLayout.showFieldError(@StringRes errorRes: Int?) {
        errorRes?.let { res ->
            this.error = getString(res)
        }
    }

    private fun clearEditTexts() {
        binding.run {
            editName.setText("")
            editName.clearFocus()

            editEmail.setText("")
            editEmail.clearFocus()

            editPhone.setText("")
            editPhone.clearFocus()
        }
    }

    private fun clearInputErrors() {
        binding.run {
            textInputName.error = null
            textInputEmail.error = null
            textInputPhone.error = null
        }
    }

    private fun getAnchorView(): View? {
        return (requireActivity() as? HomeActivity)?.getSnackBarAnchorView()
    }
}