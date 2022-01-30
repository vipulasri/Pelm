package com.vipulasri.pelm.data.repository

import com.vipulasri.pelm.data.local.photo.PhotoLocalDataSource
import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.Photo
import com.vipulasri.pelm.domain.repository.PhotoRepository

/**
 * Created by Vipul Asri on 30/01/22.
 */

class PhotoRepositoryImpl(
    private val localDataSource: PhotoLocalDataSource
) : PhotoRepository {

    override suspend fun getPhotos(): SafeResult<List<Photo>> {
        return localDataSource.getPhotos()
    }

}