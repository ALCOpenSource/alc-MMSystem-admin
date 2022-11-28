package com.peculiaruc.alc_mmsystem_admin.ui.programs.models

import androidx.lifecycle.MutableLiveData

/**
 * Criteria
 *
 * @constructor Create empty Criteria
 */
object Criteria {
    var criteriaMultipleInputs = MutableLiveData<HashMap<String, Int>>()
    var criteriaMultiChoicesInputs = MutableLiveData<HashMap<String, ArrayList<String>>>()
    var criteriaSingleInputs = MutableLiveData<ArrayList<String>>()
    var criteriaYesNoInputs = MutableLiveData<ArrayList<String>>()
    var criteriaFileInputs = MutableLiveData<ArrayList<FileField>>()
    var criteriaFileQuestionInput = MutableLiveData<String>()

}