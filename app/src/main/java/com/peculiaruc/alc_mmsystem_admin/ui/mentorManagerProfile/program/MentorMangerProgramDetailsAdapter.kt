package com.peculiaruc.alc_mmsystem_admin.ui.mentorManagerProfile.program

import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.domain.models.MentorMangersProgram
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseInteractionListener


/**
 * program details adapter class for the program details fragment
 */
class MentorMangerProgramDetailsAdapter(
    items: List<MentorMangersProgram>,
    listener: MMProgramDetailInteractionListener
) : BaseAdapter<MentorMangersProgram>(items, listener) {
    override val layoutID: Int = R.layout.item_mm_program_details
}

/**
 * program details interaction listener for the program details fragment
 */
interface MMProgramDetailInteractionListener : BaseInteractionListener {

    /**
     * done when the task details item is viewed
     */
    fun onProgramDetailsView(item: MentorMangersProgram)
}