package com.vipulasri.pelm.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.vipulasri.pelm.R
import com.vipulasri.pelm.databinding.FragmentGalleryBinding
import com.vipulasri.pelm.extensions.showOrHide
import com.vipulasri.pelm.ui.base.BaseFragment
import com.vipulasri.pelm.utils.nonNull
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Vipul Asri on 29/01/22.
 */

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGalleryBinding
        get() = FragmentGalleryBinding::inflate

    private val viewModel: PhotoGalleryVM by viewModels()
    private var photoAdapter: PhotoAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoAdapter = PhotoAdapter(Glide.with(this)).apply {
            onPhotoClick = { photo ->
                PhotoFullScreenActivity.launch(requireActivity(), photo.url)
            }
        }

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = photoAdapter
        }
    }

    private fun setupObservers() {
        viewModel.viewState.nonNull().observe(viewLifecycleOwner) { state ->
            when (state) {
                is PhotoViewState.Loading -> {
                    binding.progress.isVisible = true
                    binding.recyclerView.isVisible = false
                    binding.viewError.showOrHide(false)
                }
                is PhotoViewState.Gallery -> {
                    binding.progress.isVisible = false
                    binding.viewError.showOrHide(false)
                    binding.recyclerView.isVisible = true
                    photoAdapter?.submitList(state.data)
                }
                is PhotoViewState.Error -> {
                    binding.progress.isVisible = true
                    binding.recyclerView.isVisible = false
                    binding.viewError.showOrHide(
                        show = true,
                        message = getString(R.string.error_fetching_photos),
                        onRetry = {
                            viewModel.loadPhotos()
                        }
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        photoAdapter = null
        super.onDestroyView()
    }
}