package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report

/**
 * Task reports view model
 *
 * @constructor Create empty Task reports view model
 */
class TaskReportsViewModel : ViewModel() {

    private var taskReportsList = ArrayList<Report>()
    var filteredTaskReports = MutableLiveData<List<Report>>()
    private var _taskReports = MutableLiveData<List<Report>>()
    var taskReports: LiveData<List<Report>> = _taskReports

    /**
     * Init task reports
     *
     */
    fun initTaskReports() {
        for (i in 1..10) {
            val report =
                Report(i, "Google Africa Scholarship Report", "19th - 25th Oct 22", "Ibrahim Kabir")
            taskReportsList.add(report)
        }
        _taskReports.value = taskReportsList
    }

}

