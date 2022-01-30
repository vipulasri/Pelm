package com.vipulasri.pelm.domain.repository

import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.Photo

/**
 * Created by Vipul Asri on 30/01/22.
 */

interface PhotoRepository {

    suspend fun getPhotos(): SafeResult<List<Photo>>

}