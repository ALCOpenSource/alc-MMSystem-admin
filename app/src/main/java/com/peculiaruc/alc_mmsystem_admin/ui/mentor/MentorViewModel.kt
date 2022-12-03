package com.peculiaruc.alc_mmsystem_admin.ui.mentor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.domain.models.MentorModel
import com.peculiaruc.alc_mmsystem_admin.ui.mentorTypeProfile.adapters.MentorInteractionListener
import com.peculiaruc.alc_mmsystem_admin.utilities.event.Event

class MentorViewModel : ViewModel(), MentorInteractionListener {

    private val _selectItemMange = MutableLiveData<Event<MentorModel>>()
    val selectItemMange: LiveData<Event<MentorModel>> = _selectItemMange

    private val _addMentorEvent = MutableLiveData<Event<Boolean>>()
    val addMentorEvent: LiveData<Event<Boolean>> = _addMentorEvent

    /**
     * done when the add mentor button is clicked
     */
    fun addMentor() {
        _addMentorEvent.postValue(Event(true))
    }

    override fun onItemMentorSelected(item: MentorModel) {
        _selectItemMange.postValue(Event(item))
    }
}