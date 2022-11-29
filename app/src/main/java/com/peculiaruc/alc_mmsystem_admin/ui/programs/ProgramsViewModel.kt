package com.peculiaruc.alc_mmsystem_admin.ui.programs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peculiaruc.alc_mmsystem_admin.domain.models.ProgramAdmin
import kotlin.random.Random

/**
 * Programs view model
 *
 * @constructor Create empty Programs view model
 */
class ProgramsViewModel : ViewModel() {
    var programsList = ArrayList<ProgramAdmin>()
    var programsAllList = ArrayList<ProgramAdmin>()
    var programsActiveList = ArrayList<ProgramAdmin>()
    var programsCompletedList = ArrayList<ProgramAdmin>()
    var filteredPrograms = MutableLiveData<List<ProgramAdmin>>()
    private var _programs = MutableLiveData<List<ProgramAdmin>>()
    var programs: LiveData<List<ProgramAdmin>> = _programs

    private var _program = MutableLiveData<ProgramAdmin>()
var program:LiveData<ProgramAdmin> = _program
    /**
     * Initprograms
     *
     */
    fun initprograms() {
        for (i in 1..20) {
            val program =
                ProgramAdmin(
                    i, "Google Africa Scholarship Program $i", "", "",
                    Random.nextBoolean(), Random.nextBoolean()
                )
            programsList.add(program)
            programsAllList.add(program)
            if (program.active) {
                programsActiveList.add(program)
            }
            if (program.complete) {
                programsCompletedList.add(program)
            }
        }
        _programs.value = programsList
    }

    /**
     * Get program
     *
     * @param programID
     * @return ProgramAdmin object or null
     */
    fun getProgram(programID:Int):LiveData<ProgramAdmin>?{
        programs.value?.let {
        if(programID>=0 && programID<it.size)
        _program.value= it.get(programID)
        return _program
        }
        return null
    }
}
