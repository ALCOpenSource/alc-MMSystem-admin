package com.peculiaruc.alc_mmsystem_admin.ui.mentorManagerProfile.adapters

import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.domain.models.MentorModel
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseInteractionListener

class MentorAdapter(items: List<MentorModel>, listener: MentorInteractionListener) :
    BaseAdapter<MentorModel>(items, listener) {
    override val layoutID: Int = R.layout.item_mentor_manger
}

interface MentorInteractionListener : BaseInteractionListener {
    fun onItemMentorSelected(item: MentorModel)
}