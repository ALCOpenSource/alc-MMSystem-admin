package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaInputYesNoBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * Criteria yes no input fragment
 *
 * @constructor Create empty Criteria yes no input fragment
 */
class CriteriaYesNoInputFragment : BaseFragment<FragmentCriteriaInputYesNoBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_input_yes_no
    override val viewModel = CriteriaViewModel.getInstance()
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
            inputs = viewModel.criteriaYesNoInputs.value!!
            setUpCriteria()
        }
        binding.addInputButton.setOnClickListener {
            binding.inputsContainer.addView(addInput())
        }

        binding.inputCriteriaCancelBT.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.inputCriteriaDoneBT.setOnClickListener {
            if (criteriaString.isEmpty()) {
                for (key in inputsIDs) {
                    val textInput = binding.root.findViewById<TextInputLayout>(key)
                    if (!textInput.editText?.text.isNullOrEmpty()) {
                        inputs.add(textInput.editText?.text.toString())
                    }
                }
                viewModel.setCriteriaYesNoInputs(inputs)
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
        binding.addInputButton.isVisible = false
        binding.addInputTextView.isVisible = false
    }

    private fun changeItemInData() {
        val editedString = criteriaInput.editText?.text.toString()
        if (editedString.isNotEmpty() && (!editedString.equals(
                criteriaString,
                true
            ))
        ) {
            val index = inputs.indexOf(criteriaString)
            inputs[index] = editedString
            viewModel.setCriteriaYesNoInputs(inputs)
        }
    }
}