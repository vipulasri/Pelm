package com.vipulasri.pelm.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.vipulasri.pelm.databinding.ItemPhotoGalleryBinding
import com.vipulasri.pelm.domain.model.Photo

/**
 * Created by Vipul Asri on 30/01/22.
 */

class PhotoAdapter(private val requestManager: RequestManager) :
    ListAdapter<Photo, PhotoAdapter.PhotoViewHolder>(DIFF_CALLBACK) {

    var onPhotoClick: ((photo: Photo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
        holder.itemView.setOnClickListener {
            onPhotoClick?.invoke(photo)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            requestManager.load(photo.thumbnail).into(binding.imagePhoto)
        }
    }

}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}