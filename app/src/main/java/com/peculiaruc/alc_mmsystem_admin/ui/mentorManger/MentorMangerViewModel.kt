package com.peculiaruc.alc_mmsystem_admin.ui.mentorManger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.domain.models.MentorModel
import com.peculiaruc.alc_mmsystem_admin.ui.mentorManagerProfile.adapters.MentorInteractionListener
import com.peculiaruc.alc_mmsystem_admin.utilities.event.Event

/**
 * this is the viewModel for MentorMangerFragment.
 */
class MentorMangerViewModel : ViewModel(), MentorInteractionListener {

    private val _selectItemMange = MutableLiveData<Event<MentorModel>>()
    val selectItemMange: LiveData<Event<MentorModel>> = _selectItemMange

    private val _addMentorEvent = MutableLiveData<Event<Boolean>>()
    val addMentorEvent: LiveData<Event<Boolean>> = _addMentorEvent

    /**
     * done when the add mentor button is clicked
     */
    fun addMentorManger() {
        _addMentorEvent.postValue(Event(true))
    }

    override fun onItemMentorSelected(item: MentorModel) {
        _selectItemMange.postValue(Event(item))

    }
}