package com.peculiaruc.alc_mmsystem_admin.ui.mentorTypeProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.domain.models.*
import com.peculiaruc.alc_mmsystem_admin.type.MentorType
import com.peculiaruc.alc_mmsystem_admin.ui.mentorTypeProfile.adapters.*
import com.peculiaruc.alc_mmsystem_admin.utilities.event.Event

/**
 * this is the viewModel for MentorManagerProfileFragment.
 */
class MentorManagerProfileViewModel : ViewModel(), CertificateInteractionListener,
    TaskInteractionListener, MentorInteractionListener, ProgramInteractionListener,
    ReportInteractionListener {

    private val _selectCertificateEvent = MutableLiveData<Event<Certificate>>()
    val selectCertificateEvent: LiveData<Event<Certificate>> = _selectCertificateEvent

    private val _selectTaskEvent = MutableLiveData<Event<Task>>()
    val selectTaskEvent: LiveData<Event<Task>> = _selectTaskEvent

    private val _downloadReportEvent = MutableLiveData<Event<Report>>()
    val downloadReportEvent: LiveData<Event<Report>> = _downloadReportEvent

    private val _shareReportEvent = MutableLiveData<Event<Report>>()
    val shareReportEvent: LiveData<Event<Report>> = _shareReportEvent

    private val _openReportDetailsEvent = MutableLiveData<Event<Report>>()
    val openReportDetailsEvent: LiveData<Event<Report>> = _openReportDetailsEvent

    private val _selectProgramEvent = MutableLiveData<Event<Program>>()
    val selectProgramEvent: LiveData<Event<Program>> = _selectProgramEvent

    private val _selectMentorEvent = MutableLiveData<Event<MentorModel>>()
    val selectMentorEvent: LiveData<Event<MentorModel>> = _selectMentorEvent

    private val _openLinkEvent = MutableLiveData<String>()
    val openLinkEvent: MutableLiveData<String> = _openLinkEvent

    private val _mentorType = MutableLiveData<MentorType>()
    val mentorType: MutableLiveData<MentorType> = _mentorType

    val checkChip = MutableLiveData(R.id.chip_about)

    override fun onItemCertificateSelected(item: Certificate) {
        _selectCertificateEvent.postValue(Event(item))
    }

    override fun onItemTaskSelected(item: Task) {
        _selectTaskEvent.postValue(Event(item))
    }

    /**
     * set event to open selected mentor manger.
     * */
    override fun onItemMentorSelected(item: MentorModel) {
        _selectMentorEvent.postValue(Event(item))
    }

    override fun onProgramSelected(item: Program) {
        _selectProgramEvent.postValue(Event(item))
    }

    override fun onItemReportShare(item: Report) {
        _shareReportEvent.postValue(Event(item))
    }

    override fun onItemReportDownload(item: Report) {
        _downloadReportEvent.postValue(Event(item))
    }

    override fun onItemClick(item: Report) {
        _openReportDetailsEvent.postValue(Event(item))
    }


    fun onClickGithub() {
        _openLinkEvent.postValue("https://github.com/")
    }

    fun onClickLinkedIn() {
        _openLinkEvent.postValue("https://www.linkedin.com/feed/")
    }

    fun onClickInstagram() {
        _openLinkEvent.postValue("https://www.instagram.com/")
    }

    fun onClickTwitter() {
        _openLinkEvent.postValue("https://twitter.com/home")
    }

    fun setMentorType(mentorType: MentorType, mentorID: Int) {
        _mentorType.postValue(mentorType)
    }

}