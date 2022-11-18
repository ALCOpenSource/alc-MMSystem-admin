package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report
import com.peculiaruc.alc_mmsystem_admin.databinding.ItemTaskReportBinding


class TaskReportsAdapter(
    private val itemClickListener: ItemClickListener
) : ListAdapter<Report, TaskReportsAdapter.TaskReportViewHolder>(DiffCallback) {

    interface ItemClickListener {
        fun onReportItemClick(item: Report)
        fun onDownloadClick(item: Report)
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
            ItemTaskReportBinding.inflate(
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

    inner class TaskReportViewHolder(
        private var binding: ItemTaskReportBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(report: Report) {
            binding.title.text = report.title
            binding.description.text = "By " + report.user + " - " + report.time

            binding.downloadButton.setOnClickListener() {
                itemClickListener.onDownloadClick(report)
            }
            binding.shareButton.setOnClickListener() {
                itemClickListener.onShareClick(report)
            }
        }

    }
}
