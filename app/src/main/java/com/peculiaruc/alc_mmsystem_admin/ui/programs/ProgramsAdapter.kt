package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.databinding.ItemAdminProgramBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.ProgramAdmin


/**
 * Programs adapter
 *
 * @property itemClickListener
 * @constructor Create empty Programs adapter
 */
class ProgramsAdapter(
    private val itemClickListener: ItemClickListener
) : ListAdapter<ProgramAdmin, ProgramsAdapter.ProgramViewHolder>(DiffCallback) {

    /**
     * Item click listener
     *
     * @constructor Create empty Item click listener
     */
    interface ItemClickListener {
        /**
         * On item click
         *
         * @param item
         */
        fun onItemClick(item: ProgramAdmin)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ProgramAdmin>() {
            override fun areItemsTheSame(oldItem: ProgramAdmin, newItem: ProgramAdmin): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProgramAdmin, newItem: ProgramAdmin): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val viewHolder = ProgramViewHolder(
            ItemAdminProgramBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            itemClickListener.onItemClick(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    /**
     * Program view holder
     *
     * @property binding
     * @constructor Create empty Program view holder
     */
    inner class ProgramViewHolder(
        private var binding: ItemAdminProgramBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Bind
         *
         * @param item
         */
        fun bind(item: ProgramAdmin) {
            binding.programTitle.text = item.title
        }
    }
}
