package com.peculiaruc.alc_mmsystem_admin.ui.mentorManagerProfile.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.domain.models.TaskDetails
import com.peculiaruc.alc_mmsystem_admin.utilities.event.Event


/**
 * task view model class to handle the logic for the task details fragment
 */
class MentorMangerTaskDetailsViewModel : ViewModel(), MentorMangerTaskDetailInteractionListener {

    private val _isAssigned = MutableLiveData(false)
    val isAssigned: LiveData<Boolean> = _isAssigned

    private val _assignToTaskEvent = MutableLiveData<Event<Boolean>>()
    val assignToTaskEvent: LiveData<Event<Boolean>> = _assignToTaskEvent

    private val _viewTaskEvent = MutableLiveData<Event<TaskDetails>>()
    val viewTaskEvent: LiveData<Event<TaskDetails>> = _viewTaskEvent

    override fun onTaskDetailsView(item: TaskDetails) {
        _viewTaskEvent.postValue(Event(item))
    }

    /**
     * done when the assign to task button is clicked
     */
    fun onClickAssignToTask() {
        _assignToTaskEvent.postValue(Event(_isAssigned.value!!))
    }

    /**
     * set the new value of assign.
     */
    fun setAssigned(isAssigned: Boolean) {
        _isAssigned.postValue(isAssigned)
    }
}