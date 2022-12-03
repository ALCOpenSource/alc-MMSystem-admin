package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.approvalProgramRequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.domain.models.Program
import com.peculiaruc.alc_mmsystem_admin.type.ProgramProgress
import com.peculiaruc.alc_mmsystem_admin.utilities.event.Event

class ApprovalProgramRequestViewModel : ViewModel(), ProgramRequestInteractionListener {

    private val _programRequests = MutableLiveData<List<Program>>()
    val programRequests: LiveData<List<Program>> = _programRequests

    private val _selectProgramRequests = MutableLiveData<Event<Program>>()
    val selectProgramRequests: LiveData<Event<Program>> = _selectProgramRequests

    init {
        _programRequests.postValue(
            listOf(
                Program("", "", ProgramProgress.ADD),
                Program("", "", ProgramProgress.ADD),
                Program("", "", ProgramProgress.ADD),
                Program("", "", ProgramProgress.ADD),
                Program("", "", ProgramProgress.ADD),
                Program("", "", ProgramProgress.ADD),
            )
        )
    }

    override fun onProgramSelected(item: Program) {
        _selectProgramRequests.postValue(Event(item))
    }


}