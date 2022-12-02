package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
                Report("Google Africa Scholarship Report", "19th - 25th Oct 22", "Ibrahim Kabir")
            taskReportsList.add(report)
        }
        _taskReports.value = taskReportsList
    }

    companion object {
        private lateinit var instance: TaskReportsViewModel

        /**
         * Get instance
         *
         * @return instance TaskReportsViewModel
         */
        fun getInstance(): TaskReportsViewModel {
            instance = if (::instance.isInitialized) instance else TaskReportsViewModel()
            return instance
        }
    }
}

