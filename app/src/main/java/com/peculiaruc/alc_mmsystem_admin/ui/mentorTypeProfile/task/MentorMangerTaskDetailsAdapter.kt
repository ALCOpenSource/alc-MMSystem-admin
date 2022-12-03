package com.peculiaruc.alc_mmsystem_admin.ui.mentorTypeProfile.task

import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.domain.models.TaskDetails
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseInteractionListener


/**
 * task details adapter class for the task details fragment
 */
class MentorMangerTaskDetailsAdapter(
    items: List<TaskDetails>,
    listener: MentorMangerTaskDetailInteractionListener
) : BaseAdapter<TaskDetails>(items, listener) {
    override val layoutID: Int = R.layout.item_mm_task_details
}

/**
 * task details interaction listener for the task details fragment
 */
interface MentorMangerTaskDetailInteractionListener : BaseInteractionListener {

    /**
     * done when the task details item is viewed
     */
    fun onTaskDetailsView(item: TaskDetails)
}