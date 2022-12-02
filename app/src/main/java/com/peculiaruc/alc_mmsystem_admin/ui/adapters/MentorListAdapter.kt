package com.peculiaruc.alc_mmsystem_admin.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.peculiaruc.alc_mmsystem_admin.databinding.ItemMentorBinding
import com.peculiaruc.alc_mmsystem_admin.database.Mentor

/**
 * This is the adapter for the mentors
 * in the mentor list screen
 */

class MentorListAdapter(private val mentorClickListener: (Mentor)-> Unit): ListAdapter<Mentor, MentorListAdapter.MentorListViewHolder>(DiffCallback) {
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

    /**
     * For handling the view of mentor cards
     */
    class MentorListViewHolder(private var binding: ItemMentorBinding): RecyclerView.ViewHolder(binding.root) {

        /**
         * Bind the mentor object to single view items
         */
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
            val position = viewHolder.adapterPosition
            TODO("On click implementation")
        }

        return viewHolder
    }


}