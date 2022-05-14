package com.polishone.animalblog.common.presentation.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.polishone.animalblog.common.domain.model.AniBlog
import com.polishone.animalblog.common.utils.bindImage
import com.polishone.animalblog.databinding.AniblogListItemBinding

class AniBlogAdapter {

    class AniBlogViewHolder(private val binding: AniblogListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(aniBlog:AniBlog){
            val userImage = binding.aniblogListItemUserImage
            val username = binding.aniblogListItemNameText
            val blogImage = binding.aniblogListItemAnimaliImage
            val blogDescription = binding.aniblogListItemDescriptionText
            // set the data to the view
            val userImageUrl = aniBlog.owner.picture
            bindImage(userImageUrl, userImage)
            val blogImageUrl = aniBlog.image
            bindImage(blogImageUrl, blogImage)
            //  get the first and last name
            val firstName = aniBlog.owner.firstName
            val lastName = aniBlog.owner.lastName
            username.text = "${firstName} ${lastName}"
            blogDescription.text = aniBlog.text
        }
    }
}