package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.databinding.ItemReportBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report


/**
 * Task reports adapter
 *
 * @property itemClickListener
 * @constructor Create empty Task reports adapter
 */
class TaskReportsAdapter(
    private val itemClickListener: ItemClickListener
) : ListAdapter<Report, TaskReportsAdapter.TaskReportViewHolder>(DiffCallback) {

    /**
     * Item click listener
     *
     * @constructor Create empty Item click listener
     */
    interface ItemClickListener {
        /**
         * On report item click
         *
         * @param item
         */
        fun onReportItemClick(item: Report)

        /**
         * On download click
         *
         * @param item
         */
        fun onDownloadClick(item: Report)

        /**
         * On share click
         *
         * @param item
         */
        fun onShareClick(item: Report)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Report>() {
            override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskReportViewHolder {
        val viewHolder = TaskReportViewHolder(
            ItemReportBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            itemClickListener.onReportItemClick(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskReportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    /**
     * Task report view holder
     *
     * @property binding
     * @constructor Create empty Task report view holder
     */
    inner class TaskReportViewHolder(
        private var binding: ItemReportBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Bind
         *
         * @param report
         */
        fun bind(report: Report) {
            binding.itemTitle.text = report.title
            binding.itemDescription.text = "By " + report.user + " - " + report.time

            binding.downloadButton.setOnClickListener() {
                itemClickListener.onDownloadClick(report)
            }
            binding.shareButton.setOnClickListener() {
                itemClickListener.onShareClick(report)
            }
        }

    }
}
