package com.vipulasri.pelm.ui.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vipulasri.pelm.databinding.FragmentContactBinding
import com.vipulasri.pelm.ui.base.BaseFragment

/**
 * Created by Vipul Asri on 29/01/22.
 */

class ContactFragment : BaseFragment<FragmentContactBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactBinding
        get() = FragmentContactBinding::inflate
}