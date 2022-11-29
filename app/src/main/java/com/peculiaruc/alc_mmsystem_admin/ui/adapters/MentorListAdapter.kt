package com.peculiaruc.alc_mmsystem_admin.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.peculiaruc.alc_mmsystem_admin.databinding.ItemMentorBinding
import com.peculiaruc.alc_mmsystem_admin.ui.database.Mentor


class MentorListAdapter(): ListAdapter<Mentor, MentorListAdapter.MentorListViewHolder>(DiffCallback) {
    companion object  {
        private val DiffCallback = object : DiffUtil.ItemCallback<Mentor>(){
            override fun areContentsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
               return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
               return oldItem.email == newItem.email
            }
        }
    }

    class MentorListViewHolder(private var binding: ItemMentorBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(mentor: Mentor){
            binding.apply {
                textViewMentorName.text = mentor.name

                //String builder
                val sb =StringBuilder(", ")
                sb.append(mentor.role).append(mentor.organization).append(mentor.pronouns)
                textViewDescription.text = sb.toString()
            }
        }

    }

    override fun onBindViewHolder(holder: MentorListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorListViewHolder {
        val viewHolder = MentorListViewHolder(
            ItemMentorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            TODO("On click implementation")
        }

        return viewHolder
    }


}