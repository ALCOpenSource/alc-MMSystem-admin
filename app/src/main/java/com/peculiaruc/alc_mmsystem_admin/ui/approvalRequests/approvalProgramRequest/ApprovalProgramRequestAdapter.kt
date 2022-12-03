package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.approvalProgramRequest

import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.domain.models.Program
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseInteractionListener


/**
 * Approval Program Request adapter class for the Approval Program Requests
 */
class ApprovalProgramRequestAdapter(
    items: List<Program>,
    listener: ProgramRequestInteractionListener
) : BaseAdapter<Program>(items, listener) {
    override val layoutID: Int = R.layout.item_program_request
}

/**
 * Mentor interaction listener for the Mentor Manger Profile fragment
 */
interface ProgramRequestInteractionListener : BaseInteractionListener {
    /**
     * done when the Mentor item is clicked
     */
    fun onProgramSelected(item: Program)
}