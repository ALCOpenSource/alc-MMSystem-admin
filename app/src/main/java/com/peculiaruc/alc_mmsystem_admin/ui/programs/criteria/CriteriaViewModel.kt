package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.Criteria
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.FileField

class CriteriaViewModel : ViewModel() {
    lateinit var criteriaMultipleInputs : LiveData<HashMap<String, Int>>
    lateinit var multiChoicesInputs : LiveData<HashMap<String, ArrayList<String>>>
    lateinit var criteriaSingleInputs : LiveData<ArrayList<String>>
    lateinit var criteriaYesNoInputs : LiveData<ArrayList<String>>
    lateinit var criteriaFileInputs : LiveData<HashMap<String, ArrayList<FileField>>>

    class CriteriaViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CriteriaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CriteriaViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}