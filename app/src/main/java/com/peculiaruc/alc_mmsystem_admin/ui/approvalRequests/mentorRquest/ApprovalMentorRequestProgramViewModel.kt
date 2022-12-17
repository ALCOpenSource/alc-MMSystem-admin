package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.mentorRquest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.domain.models.MentorModel
import com.peculiaruc.alc_mmsystem_admin.utilities.event.Event

class ApprovalMentorRequestProgramViewModel : ViewModel(),
    ApprovalMentorRequestInteractionListener {

    private val _mentorChipSelected = MutableLiveData<Boolean>()
    val mentorChipSelected: LiveData<Boolean> = _mentorChipSelected

    private val _mentorMangerChipSelected = MutableLiveData<Boolean>()
    val mentorMangerChipSelected: LiveData<Boolean> = _mentorMangerChipSelected

    private val _mentorEvent = MutableLiveData<Event<MentorModel>>()
    val mentorEvent: LiveData<Event<MentorModel>> = _mentorEvent

    private val _mentors = MutableLiveData<List<MentorModel>>()
    val mentors: LiveData<List<MentorModel>> = _mentors

    fun setType(mentorType: Int) {
        if (mentorType == 1) {
            _mentorMangerChipSelected.postValue(true)
            _mentorChipSelected.postValue(false)
            _mentors.postValue(initMentors())
        } else {
            _mentorMangerChipSelected.postValue(false)
            _mentorChipSelected.postValue(true)
            _mentors.postValue(initMentorManger())
        }
    }

    fun onMentorType(type: Int) {
        setType(type)
    }

    private fun initMentors(): List<MentorModel> {
        return listOf(
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
        )
    }

    private fun initMentorManger(): List<MentorModel> {
        return listOf(
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
            MentorModel("", "", "", listOf("")),
        )
    }

    override fun onSelectMentor(item: MentorModel) {
        _mentorEvent.postValue(Event(item))
    }
}