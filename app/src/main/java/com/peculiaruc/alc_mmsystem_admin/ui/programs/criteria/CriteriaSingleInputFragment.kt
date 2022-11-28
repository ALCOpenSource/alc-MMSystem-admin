package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaInputSingleBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.Criteria

/**
 * Criteria single input fragment
 *
 * @constructor Create empty Criteria single input fragment
 */
class CriteriaSingleInputFragment : BaseFragment<FragmentCriteriaInputSingleBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_input_single
    override val viewModel: CriteriaViewModel by viewModels()
    private val TAG = "CriteriaTagSingle"
    private var inputsNumber = 1
    private var inputsIDs = ArrayList<Int>()
    private var inputs = ArrayList<String>()
    private val CRITERIA_ITEM = "item"
    private var criteriaString = ""
    private lateinit var criteriaInput: TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.criteria_single_input_title))
        arguments?.let {
            criteriaString = it.getString(CRITERIA_ITEM, "")
        }

        if (criteriaString.isEmpty()) {
            binding.inputsContainer.addView(addInput())
        } else {
            inputs = Criteria.criteriaSingleInputs.value!!
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
            if (criteriaString.isEmpty()) {
                Log.i(TAG, "inputsIDs:" + inputsIDs)
                for (i in 0..inputsIDs.size - 1) {
                    Log.i(TAG, "i:" + i)

                    val textInput = binding.root.findViewById<TextInputLayout>(inputsIDs.get(i))
                    if (!textInput.editText?.text.isNullOrEmpty()) {
                        inputs.add(textInput.editText?.text.toString())
                    }
                }
                Log.i(TAG, "input list :" + inputs)
                Criteria.criteriaSingleInputs.value = inputs
            } else {
                changeItemInData()
            }
            view.findNavController().popBackStack()
        }
    }

    /**
     * Add input
     *
     * @return
     */
    fun addInput(): TextInputLayout {
        val textInput: TextInputLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_input_text,
                binding.inputsContainer, false
            ) as TextInputLayout

        inputsNumber++
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
            Criteria.criteriaSingleInputs.value = inputs

        }
    }

}