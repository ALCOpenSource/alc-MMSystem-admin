package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.mentorRquest

import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.domain.models.MentorModel
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseInteractionListener


/**
 * mentors adapter class for the approval mentor request
 */
class ApprovalMentorRequestAdapter(
    items: List<MentorModel>,
    listener: ApprovalMentorRequestInteractionListener
) : BaseAdapter<MentorModel>(items, listener) {
    override val layoutID: Int = R.layout.item_approval_mnetor_request
}

/**
 * mentors interaction listener for the approval mentor request
 */
interface ApprovalMentorRequestInteractionListener : BaseInteractionListener {
    /**
     * done when the Notification item is Clicked
     */
    fun onSelectMentor(item: MentorModel)
}