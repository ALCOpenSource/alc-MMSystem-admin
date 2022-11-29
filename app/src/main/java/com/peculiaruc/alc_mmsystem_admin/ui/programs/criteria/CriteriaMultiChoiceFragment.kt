package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaMultiChoiceBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.Criteria

/**
 * Criteria multi choice fragment
 *
 * @constructor Create empty Criteria multi choice fragment
 */
class CriteriaMultiChoiceFragment : BaseFragment<FragmentCriteriaMultiChoiceBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_multi_choice
    override val viewModel: CriteriaViewModel by viewModels()
    private val TAG = "CriteriaTagChoices"
    private var choicesInputsIDs = ArrayList<Int>()
    private var choicesNumber = 3
    private var choicesInputs = HashMap<String, ArrayList<String>>()
    private val CRITERIA_KEY = "key"
    private var criteriaKey = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.criteria_multi_choice_title))

        arguments?.let {
            criteriaKey = it.getString(CRITERIA_KEY, "")
        }
        choicesInputsIDs.clear()
        if (criteriaKey.isEmpty()) {
            binding.inputsContainer.addView(addChoiceInput())
            binding.inputsContainer.addView(addChoiceInput())
            binding.inputsContainer.addView(addChoiceInput())
        } else {
            choicesInputs = Criteria.criteriaMultiChoicesInputs.value!!
            setUpCriteria()
        }


        binding.addChoiceButton.setOnClickListener() {
            binding.inputsContainer.addView(addChoiceInput())
        }

        binding.multichoiceCriteriaCancelBT.setOnClickListener() {
            view.findNavController().popBackStack()
        }
        binding.multichoiceCriteriaDonelBT.setOnClickListener() {
            if (criteriaKey.isEmpty()) {
                val choices = getChoicesFromTextInputs()
                Log.i(TAG, "choices ids:" + choicesInputsIDs)
                Log.i(TAG, "choices:" + choices)

                if (!binding.questionInputText.editText?.text.isNullOrEmpty()) {

                    choicesInputs.put(binding.questionInputText.editText?.text.toString(), choices)
                }
                Log.i(TAG, "choicesInputs:" + choicesInputs)

                Criteria.criteriaMultiChoicesInputs.value = choicesInputs
                Log.i(
                    TAG,
                    "criteriaMultiChoicesInputs:" + Criteria.criteriaMultiChoicesInputs.value
                )
            } else {
                changeItemInData()
            }
            view.findNavController().popBackStack()
        }
    }

    /**
     * Get choices from text inputs
     *
     * @return arraylist of all editTexts text
     */
    fun getChoicesFromTextInputs(): ArrayList<String> {
        val choices = ArrayList<String>()
        for (id in choicesInputsIDs) {
            val textInput = binding.root.findViewById<TextInputLayout>(id)
            if (!textInput.editText?.text.isNullOrEmpty()) {

                choices.add(textInput.editText?.text.toString())
            }
        }
        return choices
    }

    /**
     * Add choice input
     *
     * @return TextInputLayout to be added to view
     */
    fun addChoiceInput(): TextInputLayout {
        val textInput: TextInputLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_input_text_with_icon,
                binding.inputsContainer, false
            ) as TextInputLayout
        textInput.id = View.generateViewId()
        textInput.setEndIconOnClickListener {
            if (choicesNumber > 1) {
                textInput.visibility = View.GONE
                choicesInputsIDs.remove(textInput.id)
                Log.i(TAG, "remove:" + textInput.editText?.text + "    id:" + textInput.id)
                Log.i(TAG, "choicesInputsIDs: " + choicesInputsIDs)
                choicesNumber--
            }
        }
        choicesNumber++
        choicesInputsIDs.add(textInput.id)
        return textInput
    }

    private fun setUpCriteria() {
        val item = choicesInputs.get(criteriaKey)
        binding.questionInputText.editText?.setText(criteriaKey)
        if (!item.isNullOrEmpty()) {
            for (choice in item) {
                val input = addChoiceInput()
                input.editText?.setText(choice)
                binding.inputsContainer.addView(input)
            }
        }
    }

    private fun changeItemInData() {
        val editedString = binding.questionInputText.editText?.text.toString()
        Log.i(TAG, "1 input list :" + choicesInputs)
        if (!(editedString.isEmpty())) {
            val choices = getChoicesFromTextInputs()
            choicesInputs.remove(criteriaKey)
            choicesInputs.put(editedString, choices)
            Log.i(TAG, "2 input list :" + choicesInputs)

            Criteria.criteriaMultiChoicesInputs.value = choicesInputs
            Log.i(
                TAG,
                "Criteria.criteriaMultiChoicesInputs :" + Criteria.criteriaMultiChoicesInputs.value
            )

        }
    }

}