package com.vipulasri.pelm.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vipulasri.pelm.databinding.FragmentGalleryBinding
import com.vipulasri.pelm.databinding.FragmentWeatherBinding
import com.vipulasri.pelm.ui.base.BaseFragment

/**
 * Created by Vipul Asri on 29/01/22.
 */

class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGalleryBinding
        get() = FragmentGalleryBinding::inflate
}