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
    private var _criteriaMultipleInputs = MutableLiveData<HashMap<String, Int>>()
    private var _criteriaMultiChoicesInputs = MutableLiveData<HashMap<String, ArrayList<String>>>()
    private var _criteriaSingleInputs = MutableLiveData<ArrayList<String>>()
    private var _criteriaYesNoInputs = MutableLiveData<ArrayList<String>>()
    private var _criteriaFileInputs = MutableLiveData<ArrayList<FileField>>()
    private var _criteriaFileQuestionInput = MutableLiveData<String>()

    var criteriaMultipleInputs: LiveData<HashMap<String, Int>> = _criteriaMultipleInputs
    var criteriaMultiChoicesInputs: LiveData<HashMap<String, ArrayList<String>>> =
        _criteriaMultiChoicesInputs
    var criteriaSingleInputs: LiveData<ArrayList<String>> = _criteriaSingleInputs
    var criteriaYesNoInputs: LiveData<ArrayList<String>> = _criteriaYesNoInputs
    var criteriaFileInputs: LiveData<ArrayList<FileField>> = _criteriaFileInputs
    var criteriaFileQuestionInput: LiveData<String> = _criteriaFileQuestionInput

    /**
     * Set criteria single inputs
     *
     * @param inputs
     */
    fun setCriteriaSingleInputs(inputs: ArrayList<String>) {
        _criteriaSingleInputs.value = inputs
    }

    /**
     * Set criteria yes no inputs
     *
     * @param inputs
     */
    fun setCriteriaYesNoInputs(inputs: ArrayList<String>) {
        _criteriaYesNoInputs.value = inputs
    }

    /**
     * Set criteria file inputs
     *
     * @param inputs
     */
    fun setCriteriaFileInputs(inputs: ArrayList<FileField>) {
        _criteriaFileInputs.value = inputs
    }

    /**
     * Set criteria multiple inputs
     *
     * @param inputs
     */
    fun setCriteriaMultipleInputs(inputs: HashMap<String, Int>) {
        _criteriaMultipleInputs.value = inputs
    }

    /**
     * Set criteria multi choices inputs
     *
     * @param inputs
     */
    fun setCriteriaMultiChoicesInputs(inputs: HashMap<String, ArrayList<String>>) {
        _criteriaMultiChoicesInputs.value = inputs
    }

    /**
     * Set criteria file question input
     *
     * @param question
     */
    fun setCriteriaFileQuestionInput(question: String) {
        _criteriaFileQuestionInput.value = question
    }

    companion object {
        private lateinit var instance: CriteriaViewModel

        /**
         * Get instance
         *
         * @return instance CriteriaViewModel
         */
        fun getInstance(): CriteriaViewModel {
            instance = if (::instance.isInitialized) instance else CriteriaViewModel()
            return instance
        }
    }
}