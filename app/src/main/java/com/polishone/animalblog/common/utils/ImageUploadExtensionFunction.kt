package com.polishone.animalblog.common.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * to bind the image date to the view
 */
fun bindImage(imageUrl:String, imageView: ImageView){
    Glide.with(imageView.context)
        .load(imageUrl).into(imageView)
}