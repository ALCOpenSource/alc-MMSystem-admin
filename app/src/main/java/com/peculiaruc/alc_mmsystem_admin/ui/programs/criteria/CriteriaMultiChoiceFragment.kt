package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaMultiChoiceBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/*
* Add documentation
* */
class CriteriaMultiChoiceFragment : BaseFragment<FragmentCriteriaMultiChoiceBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_multi_choice
    override val viewModel: CriteriaViewModel by viewModels()
    private val TAG = "CriteriaMultiChoiceTag"
    private var choicesInputs = HashMap<String, Int>()
    private var choicesNumber = 3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.criteria_multi_choice_title))

        choicesInputs.put("choiceInputText1", binding.choiceInputText1.id)
        choicesInputs.put("choiceInputText2", binding.choiceInputText2.id)
        choicesInputs.put("choiceInputText3", binding.choiceInputText3.id)
        binding.choiceInputText1.setEndIconOnClickListener {
            if (choicesNumber > 1) {
                binding.choiceInputText1.visibility = View.GONE
                choicesInputs.remove("choiceInputText1") // .remove("choiceInputText1",binding.choiceInputText1.id)
                choicesNumber--
            }
        }
        binding.choiceInputText2.setEndIconOnClickListener {
            if (choicesNumber > 1) {
                binding.choiceInputText2.visibility = View.GONE
                choicesInputs.remove("choiceInputText2")
                choicesNumber--
            }
        }
        binding.choiceInputText3.setEndIconOnClickListener {
            if (choicesNumber > 1) {
                binding.choiceInputText3.visibility = View.GONE
                choicesInputs.remove("choiceInputText3")
                choicesNumber--
            }
        }
        binding.addChoiceButton.setOnClickListener() {

            val textInput: TextInputLayout =
                layoutInflater.inflate(
                    R.layout.item_criteria_input_text_with_icon,
                    binding.choicesContainer, false
                ) as TextInputLayout

            textInput.setEndIconOnClickListener {
                if (choicesNumber > 1) {
                    textInput.visibility = View.GONE
                    choicesInputs.remove("choiceInputText" + choicesNumber)
                    choicesNumber--
                }
            }
            binding.choicesContainer.addView(textInput)
            choicesNumber++
            textInput.id = View.generateViewId()
            textInput.hint = "hint" + choicesNumber
            choicesInputs.put("choiceInputText" + choicesNumber, textInput.id)
        }

        binding.multichoiceCriteriaCancelBT.setOnClickListener() {
            view.findNavController().popBackStack()
        }
        binding.multichoiceCriteriaDonelBT.setOnClickListener() {
            /***** in the previous Fragment
             val navController = findNavController()
             // Instead of String any types of data can be used
             navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("key")
             ?.observe(viewLifecycleOwner) {

             }
             }*/
            // view.findNavController().previousBackStackEntry?.savedStateHandle?.set("key", "value that needs to be passed")
            view.findNavController().popBackStack()
        }
    }
}
