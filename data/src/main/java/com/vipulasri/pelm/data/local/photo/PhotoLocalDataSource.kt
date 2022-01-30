package com.vipulasri.pelm.data.local.photo

import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.Photo

/**
 * Created by Vipul Asri on 30/01/22.
 */

interface PhotoLocalDataSource {

    fun getPhotos(): SafeResult<List<Photo>>

}