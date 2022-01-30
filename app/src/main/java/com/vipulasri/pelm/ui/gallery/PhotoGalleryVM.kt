package com.vipulasri.pelm.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vipulasri.pelm.domain.handleResult
import com.vipulasri.pelm.domain.model.Photo
import com.vipulasri.pelm.domain.repository.PhotoRepository
import com.vipulasri.pelm.ui.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vipul Asri on 30/01/22.
 */

@HiltViewModel
class PhotoGalleryVM @Inject constructor(
    private val photoRepository: PhotoRepository
) : BaseVM() {

    val viewState: LiveData<PhotoViewState> = MutableLiveData()

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            viewState.setValue(PhotoViewState.Loading)
            val result = photoRepository.getPhotos()
            result.handleResult(
                onSuccess = { photos ->
                    viewState.setValue(PhotoViewState.Gallery(photos))
                },
                onError = { error ->
                    viewState.setValue(PhotoViewState.Error(error.message))
                }
            )
        }
    }

}

sealed class PhotoViewState {
    object Loading : PhotoViewState()
    class Gallery(val data: List<Photo>) : PhotoViewState()
    class Error(val message: String) : PhotoViewState()
}