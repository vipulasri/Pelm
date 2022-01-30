package com.vipulasri.pelm

import android.app.Application
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Vipul Asri on 29/01/22.
 */

@HiltAndroidApp
class PelmApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initBigImageViewer()
    }

    private fun initBigImageViewer() {
        BigImageViewer.initialize(GlideImageLoader.with(this))

        if (BigImageViewer.imageLoader() == null) {
            Timber.d("BigImageViewer initialization unsuccessful")
        } else {
            Timber.d("BigImageViewer initialization successful")
        }
    }
}