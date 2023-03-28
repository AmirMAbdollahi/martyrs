package com.example.martyrs.services

import com.example.martyrs.view.MartyrImageView
import com.facebook.drawee.view.SimpleDraweeView

class FrescoImageLoadingService : ImageLoadingService {
    override fun loadImage(imageView: MartyrImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView) {
            imageView.setImageURI(imageUrl)
        } else
            throw IllegalStateException("ImageView must be instance of SimpleDraweeView")

    }
}