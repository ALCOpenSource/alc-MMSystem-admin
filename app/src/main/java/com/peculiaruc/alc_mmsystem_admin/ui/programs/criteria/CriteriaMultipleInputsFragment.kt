package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaMultipleInputsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.Criteria

/**
 * Criteria multiple inputs fragment
 *
 * @constructor Create empty Criteria multiple inputs fragment
 */
class CriteriaMultipleInputsFragment : BaseFragment<FragmentCriteriaMultipleInputsBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_criteria_multiple_inputs
    override val viewModel: CriteriaViewModel by viewModels()
    private val TAG = "CriteriaTagMultiINput"
    private var questionsNumber = 1
    private lateinit var adapter: ArrayAdapter<CharSequence>
    private val CRITERIA_KEY = "key"
    private var criteriaKey = ""
    private var inputList = HashMap<String, Int>()
    private var questions = HashMap<Int, String>()
    private var inputs = HashMap<Int, Int>()
    private var editedInputID = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.criteria_multipule_inputs_title))
        arguments?.let {
            criteriaKey = it.getString(CRITERIA_KEY, "")
        }
        adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.inputs_string_array,
            android.R.layout.simple_list_item_1
        )

        if (criteriaKey.isEmpty()) {
            binding.inputsContainer.addView(addInput())
        } else {
            inputList = Criteria.criteriaMultipleInputs.value!!
            setUpCriteria()
        }



        binding.addInputButton.setOnClickListener() {
            val inputLayout = addInput()
            binding.inputsContainer.addView(inputLayout)
        }

        binding.multipleCriteriaCancelBT.setOnClickListener() {
            Log.i(TAG, "inputs:" + inputs)
            Log.i(TAG, "questions:" + questions)
            view.findNavController().popBackStack()
        }

        binding.multipleCriteriaDonelBT.setOnClickListener() {
            Log.i(TAG, "inputs:" + inputs)
            Log.i(TAG, "questions:" + questions)
            if (criteriaKey.isEmpty()) {
                for (key in questions.keys) {
                    questions.get(key)?.let { it1 ->
                        inputs.get(key)
                            ?.let { it2 -> inputList.put(it1, it2) }
                    }
                }
                Log.i(TAG, "input list :" + inputList)
                Criteria.criteriaMultipleInputs.value = inputList
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
    fun addInput(): LinearLayout {
        val inputLayout: LinearLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_multiple_input,
                binding.inputsContainer, false
            ) as LinearLayout
        questionsNumber++
        val textInputQuestion =
            inputLayout.findViewById(R.id.questionInputText1) as TextInputLayout
        val textInputDropDown =
            inputLayout.findViewById(R.id.inputTextDropDown1) as TextInputLayout
        textInputDropDown.id = View.generateViewId()
        textInputQuestion.id = View.generateViewId()
        var text = ""
        var inputsNb = 0
        questions.put(textInputQuestion.id, text)
        inputs.put(textInputQuestion.id, inputsNb)
        textInputQuestion.editText?.addTextChangedListener {
            text = it.toString()
            questions.put(textInputQuestion.id, text)
        }
        (textInputDropDown.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (textInputDropDown.editText as? AutoCompleteTextView)?.setOnItemClickListener { parent, view, position, id ->
            inputsNb = position + 2
            inputs.put(textInputQuestion.id, inputsNb)
        }

        return inputLayout
    }

    /**
     * Add input with data
     *
     * @param question
     * @param inputsNb
     * @return
     */
    fun addInputWithData(question: String, inputsNb: Int): LinearLayout {
        val inputLayout: LinearLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_multiple_input,
                binding.inputsContainer, false
            ) as LinearLayout
        questionsNumber++
        val textInputQuestion =
            inputLayout.findViewById(R.id.questionInputText1) as TextInputLayout
        textInputQuestion.editText?.setText(question)
        val textInputDropDown =
            inputLayout.findViewById(R.id.inputTextDropDown1) as TextInputLayout
        textInputDropDown.editText?.setText(
            resources.getStringArray(R.array.inputs_string_array).get(inputsNb - 2)
        )
        textInputDropDown.id = View.generateViewId()
        textInputQuestion.id = View.generateViewId()
        questions.put(textInputQuestion.id, question)
        inputs.put(textInputQuestion.id, inputsNb)
        textInputQuestion.editText?.addTextChangedListener {
            questions.put(textInputQuestion.id, it.toString())
        }
        (textInputDropDown.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (textInputDropDown.editText as? AutoCompleteTextView)?.setOnItemClickListener { parent, view, position, id ->
            inputs.put(textInputQuestion.id, position + 2)
        }
        editedInputID = textInputQuestion.id
        return inputLayout
    }

    /**
     * Set up criteria
     *
     */
    private fun setUpCriteria() {
        inputList.get(criteriaKey)?.let {
            binding.inputsContainer.addView(addInputWithData(criteriaKey, it))
        }
        binding.addInputButton.isVisible=false
        binding.addInputTextView.isVisible=false
    }

    /**
     * Change item in data
     *
     */
    private fun changeItemInData() {
        val editedString = questions.get(editedInputID)
        if (!editedString.isNullOrEmpty()) {
            val editedInputsNB = inputs.get(editedInputID)

            editedInputsNB?.let {
                inputList.remove(criteriaKey)
                inputList.put(editedString, it)
                Criteria.criteriaMultipleInputs.value = inputList

            }
        }
    }
}