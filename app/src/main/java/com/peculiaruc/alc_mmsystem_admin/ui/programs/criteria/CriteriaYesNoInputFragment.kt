package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaInputYesNoBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.Criteria

/**
 * Criteria yes no input fragment
 *
 * @constructor Create empty Criteria yes no input fragment
 */
class CriteriaYesNoInputFragment : BaseFragment<FragmentCriteriaInputYesNoBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_input_yes_no
    override val viewModel: CriteriaViewModel by viewModels()
    private val TAG = "CriteriaTagYesNo"
    private val CRITERIA_ITEM = "item"
    private var criteriaString = ""
    private lateinit var criteriaInput: TextInputLayout
    private var inputsIDs = ArrayList<Int>()
    private var inputs = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.criteria_yesno_input_title))

        arguments?.let {
            criteriaString = it.getString(CRITERIA_ITEM, "")
        }

        if (criteriaString.isEmpty()) {
            binding.inputsContainer.addView(addInput())
        } else {
            inputs = Criteria.criteriaYesNoInputs.value!!
            setUpCriteria()
        }
        binding.addInputButton.setOnClickListener() {
            binding.inputsContainer.addView(addInput())
        }

        binding.inputCriteriaCancelBT.setOnClickListener() {
            Log.i(TAG, "inputs:" + inputs)
            view.findNavController().popBackStack()
        }

        binding.inputCriteriaDonelBT.setOnClickListener() {
            Log.i(TAG, "inputs:" + inputs)
            if (criteriaString.isEmpty()) {
                for (key in inputsIDs) {
                    val textInput = binding.root.findViewById<TextInputLayout>(key)
                    if (!textInput.editText?.text.isNullOrEmpty()) {
                        inputs.add(textInput.editText?.text.toString())
                    }
                }
                Log.i(TAG, "input list :" + inputs)
                Criteria.criteriaYesNoInputs.value = inputs
            } else {
                changeItemInData()
            }
            view.findNavController().popBackStack()
        }
    }

    /**
     * Add input
     *
     * @return TextInputLayout to be added to view
     */
    fun addInput(): TextInputLayout {
        val textInput: TextInputLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_input_text,
                binding.inputsContainer, false
            ) as TextInputLayout

        textInput.id = View.generateViewId()
        inputsIDs.add(textInput.id)
        return textInput
    }

    private fun setUpCriteria() {
        val input = addInput()
        input.editText?.setText(criteriaString)
        binding.inputsContainer.addView(input)
        criteriaInput = input
        binding.addInputButton.isVisible=false
        binding.addInputTextView.isVisible=false
    }

    private fun changeItemInData() {
        val editedString = criteriaInput.editText?.text.toString()
        Log.i(TAG, "1 input list :" + inputs)
        Log.i(TAG, "criteriaString :" + criteriaString + "    editedString:" + editedString)
        if (!(editedString.isEmpty()) && (!editedString.equals(
                criteriaString,
                true
            ))
        ) {
            val index = inputs.indexOf(criteriaString)
            inputs[index] = editedString
            Criteria.criteriaYesNoInputs.value = inputs
            Log.i(
                TAG,
                "Criteria.criteriaYesNoInputs :" + Criteria.criteriaYesNoInputs.value
            )

        }
    }
}