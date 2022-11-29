package com.peculiaruc.alc_mmsystem_admin.ui.programs.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report

/**
 * Reports view model
 *
 * @constructor Create empty Reports view model
 */
class ReportsViewModel : ViewModel() {

    private var reportsList = ArrayList<Report>()
    var filteredReports = MutableLiveData<List<Report>>()
    private var _reports = MutableLiveData<List<Report>>()
    var reports: LiveData<List<Report>> = _reports

    /**
     * Init reports
     *
     */
    fun initReports() {
        for (i in 1..10) {
            val report =
                Report(i, "Google Africa Scholarship Report", "19th - 25th Oct 22", "Ibrahim Kabir")
            reportsList.add(report)
        }
        _reports.value = reportsList
    }

    companion object {
        private lateinit var instance: ReportsViewModel

        fun getInstance(): ReportsViewModel {
            instance = if (::instance.isInitialized) instance else ReportsViewModel()
            return instance
        }
    }
}
