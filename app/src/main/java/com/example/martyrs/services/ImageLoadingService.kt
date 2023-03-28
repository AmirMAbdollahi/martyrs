package com.example.martyrs.services

import com.example.martyrs.view.MartyrImageView

interface ImageLoadingService {

    fun loadImage(imageView: MartyrImageView, imageUrl: String)

}