package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.utilities.event.Event

class ApprovalRequestsViewModel : ViewModel() {

    private val _mentorMangerRequestEvent = MutableLiveData<Event<Boolean>>()
    val mentorMangerRequestEvent: LiveData<Event<Boolean>> = _mentorMangerRequestEvent

    private val _mentorRequestEvent = MutableLiveData<Event<Boolean>>()
    val mentorRequestEvent: LiveData<Event<Boolean>> = _mentorRequestEvent

    private val _programRequestEvent = MutableLiveData<Event<Boolean>>()
    val programRequestEvent: LiveData<Event<Boolean>> = _programRequestEvent

    fun onClickMentorMangerRequest() {
        _mentorMangerRequestEvent.postValue(Event(true))
    }

    fun onClickMentorRequest() {
        _mentorRequestEvent.postValue(Event(true))
    }

    fun onClickProgramRequest() {
        _programRequestEvent.postValue(Event(true))
    }


}