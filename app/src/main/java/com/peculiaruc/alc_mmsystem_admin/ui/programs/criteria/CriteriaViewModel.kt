package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.FileField

/**
 * Criteria view model
 *
 * @constructor Create empty Criteria view model
 */
class CriteriaViewModel : ViewModel() {
    var _criteriaMultipleInputs = MutableLiveData<HashMap<String, Int>>()
    var _criteriaMultiChoicesInputs = MutableLiveData<HashMap<String, ArrayList<String>>>()
    var _criteriaSingleInputs = MutableLiveData<ArrayList<String>>()
    var _criteriaYesNoInputs = MutableLiveData<ArrayList<String>>()
    var _criteriaFileInputs = MutableLiveData<ArrayList<FileField>>()
    var _criteriaFileQuestionInput = MutableLiveData<String>()

    var criteriaMultipleInputs: LiveData<HashMap<String, Int>> = _criteriaMultipleInputs
    var multiChoicesInputs: LiveData<HashMap<String, ArrayList<String>>> = _criteriaMultiChoicesInputs
    var criteriaSingleInputs: LiveData<ArrayList<String>> = _criteriaSingleInputs
    var criteriaYesNoInputs: LiveData<ArrayList<String>> = _criteriaYesNoInputs
    var criteriaFileInputs: LiveData<ArrayList<FileField>> = _criteriaFileInputs
    var criteriaFileQuestionInput: LiveData<String> = _criteriaFileQuestionInput

    companion object {
        private lateinit var instance: CriteriaViewModel

        fun getInstance(): CriteriaViewModel {
            instance = if (::instance.isInitialized) instance else CriteriaViewModel()
            return instance
        }
    }
}