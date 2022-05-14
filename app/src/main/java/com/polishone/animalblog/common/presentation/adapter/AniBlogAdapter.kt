package com.polishone.animalblog.common.presentation.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.polishone.animalblog.common.domain.model.AniBlog
import com.polishone.animalblog.databinding.AniblogListItemBinding

class AniBlogAdapter {

    class AniBlogViewHolder(private val binding: AniblogListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(aniBlog:AniBlog){
            val userImage = binding.aniblogListItemUserImage
            val username = binding.aniblogListItemNameText
            val blogImage = binding.aniblogListItemAnimaliImage
            val blogDescription = binding.aniblogListItemDescriptionText
            // set the data to the view

        }
    }
}