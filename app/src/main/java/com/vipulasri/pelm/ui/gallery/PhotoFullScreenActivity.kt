package com.vipulasri.pelm.ui.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.github.piasy.biv.loader.ImageLoader
import com.vipulasri.pelm.R
import com.vipulasri.pelm.databinding.ActivityPhotoFullScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

/**
 * Created by Vipul Asri on 30/01/22.
 */

@AndroidEntryPoint
class PhotoFullScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotoFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        setupOnClickListeners()
    }

    private fun getIntentData() {
        intent?.getStringExtra(EXTRA_PHOTO_URL)?.let { url ->
            loadPhoto(url)
        } ?: kotlin.run(
            Toast.makeText(
                this,
                getString(R.string.error_loading_photo),
                Toast.LENGTH_SHORT
            )::show
        )
    }

    private fun setupOnClickListeners() {
        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    private fun loadPhoto(url: String) {
        binding.bigImageView.showImage(Uri.parse(url))
        binding.bigImageView.setImageLoaderCallback(callback)
    }

    private val callback = object : ImageLoader.Callback {
        override fun onCacheHit(imageType: Int, image: File?) {
        }

        override fun onCacheMiss(imageType: Int, image: File?) {
        }

        override fun onStart() {
            binding.progress.isVisible = true
        }

        override fun onProgress(progress: Int) {
            binding.progress.isVisible = true
        }

        override fun onFinish() {
            binding.progress.isVisible = false
        }

        override fun onSuccess(image: File?) {
            binding.bigImageView.ssiv?.run {
                setMinimumDpi(80)
                isZoomEnabled = true
                setDoubleTapZoomDpi(80)
                setDoubleTapZoomDuration(200)
                setDoubleTapZoomStyle(SubsamplingScaleImageView.ZOOM_FOCUS_FIXED)
                isQuickScaleEnabled = true
                isPanEnabled = true
            }
        }

        override fun onFail(error: Exception?) {
            binding.progress.isVisible = false
        }
    }

    companion object {
        private const val EXTRA_PHOTO_URL = "EXTRA_PHOTO_URL"

        fun launch(startingActivity: Activity, url: String) {
            startingActivity.startActivity(getIntent(startingActivity, url))
        }

        private fun getIntent(startingActivity: Activity, url: String): Intent {
            return Intent(startingActivity, PhotoFullScreenActivity::class.java).apply {
                putExtra(EXTRA_PHOTO_URL, url)
            }
        }
    }
}