package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.FileField

/**
 * Criteria view model
 *
 * @constructor Create empty Criteria view model
 */
class CriteriaViewModel : ViewModel() {
    lateinit var criteriaMultipleInputs: LiveData<HashMap<String, Int>>
    lateinit var multiChoicesInputs: LiveData<HashMap<String, ArrayList<String>>>
    lateinit var criteriaSingleInputs: LiveData<ArrayList<String>>
    lateinit var criteriaYesNoInputs: LiveData<ArrayList<String>>
    lateinit var criteriaFileInputs: LiveData<HashMap<String, ArrayList<FileField>>>

    companion object {
        private lateinit var instance: CriteriaViewModel

        fun getInstance(): CriteriaViewModel {
            instance = if (::instance.isInitialized) instance else CriteriaViewModel()
            return instance
        }
    }
}