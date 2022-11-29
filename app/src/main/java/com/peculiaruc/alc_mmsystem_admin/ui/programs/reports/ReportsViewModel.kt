package com.peculiaruc.alc_mmsystem_admin.ui.programs.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report

/**
* Add documentation
* */
class ReportsViewModel : ViewModel() {

    private var reportsList = ArrayList<Report>()
    var filteredReports = MutableLiveData<List<Report>>()
    private var _reports = MutableLiveData<List<Report>>()
    var reports: LiveData<List<Report>> = _reports

    fun initReports() {
        for (i in 1..10) {
            val report =
                Report(i, "Google Africa Scholarship Report", "19th - 25th Oct 22", "Ibrahim Kabir")
            reportsList.add(report)
        }
        _reports.value = reportsList
    }

}

class reportsViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReportsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}