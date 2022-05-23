package com.polishone.animalblog.common.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.polishone.animalblog.common.domain.model.AniBlog
import com.polishone.animalblog.common.utils.bindImage
import com.polishone.animalblog.databinding.AniblogListItemBinding

class AniBlogAdapter(private val aniBlog:List<AniBlog>?, val onClickListener: OnClickListener) : RecyclerView.Adapter<AniBlogAdapter.AniBlogViewHolder>(){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AniBlogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val aniblogListBinding = AniblogListItemBinding.inflate(layoutInflater, parent, false)
        return AniBlogViewHolder(aniblogListBinding)
    }

    override fun onBindViewHolder(holder: AniBlogViewHolder, position: Int) {
        val currentItem = aniBlog?.get(position)
        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                onClickListener.clickListener(currentItem)
            }
        }
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return aniBlog?.let {
            it.size
        } ?: 0
    }

    // clicklistener to handle click events
    class OnClickListener(val clickListener: (aniBlog:AniBlog) -> Unit){
        fun onClick(aniBlog: AniBlog) = clickListener(aniBlog)
    }
}