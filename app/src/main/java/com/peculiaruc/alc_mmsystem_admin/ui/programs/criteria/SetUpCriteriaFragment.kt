package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaSetupBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.FileField

/**
 * Set up criteria fragment
 *
 * @constructor Create empty Set up criteria fragment
 */
class SetUpCriteriaFragment : BaseFragment<FragmentCriteriaSetupBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_setup
    override val viewModel = CriteriaViewModel.getInstance()

    var criteriaMultipleInputs = HashMap<String, Int>()
    var multiChoicesInputs = HashMap<String, ArrayList<String>>()
    var criteriaSingleInputs = ArrayList<String>()
    var criteriaYesNoInputs = ArrayList<String>()
    var criteriaFileInputs = ArrayList<FileField>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.criteria_setup_title))

        binding.criteriaSingleInputsContainer.isVisible = false
        binding.criteriaMultipleInputsContainer.isVisible = false
        binding.criteriaYesNoContainer.isVisible = false
        binding.fileInputContainer.isVisible = false
        binding.multiChoiceContainer.isVisible = false

        viewModel.criteriaFileInputs.observe(this.viewLifecycleOwner) {
            binding.criteriaFileInputsContainer.removeAllViews()
            binding.fileInputContainer.isVisible = false
            setUpFileInputsLayout()
        }
        viewModel.criteriaMultipleInputs.observe(this.viewLifecycleOwner) {
            binding.criteriaMultipleInputsContainer.removeAllViews()
            binding.criteriaMultipleInputsContainer.isVisible = false
            setUpMultipleInputsLayout()
        }
        viewModel.criteriaSingleInputs.observe(this.viewLifecycleOwner) {
            binding.criteriaSingleInputsContainer.removeAllViews()
            binding.criteriaSingleInputsContainer.isVisible = false
            setUpSingleInputsLayout()
        }
        viewModel.criteriaMultiChoicesInputs.observe(this.viewLifecycleOwner) {
            binding.multiChoiceChipGroup.removeAllViews()
            binding.multiChoiceContainer.isVisible = false
            setUpMultiChoiceInputsLayout()
        }
        viewModel.criteriaYesNoInputs.observe(this.viewLifecycleOwner) {
            binding.criteriaYesNoContainer.removeAllViews()
            binding.criteriaYesNoContainer.isVisible = false
            setUpYesNoInputsLayout()
        }


        binding.addCriteriaButton.setOnClickListener {
            openSetCriteriaBottomSheet()
        }
        binding.createCriteriaButton.setOnClickListener {
            showSuccessfulCreateDialog()
        }
    }

    private fun showSuccessfulCreateDialog() {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_success)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val successTitle = dialog.findViewById<TextView>(R.id.success_dialog_title)
            successTitle.text = getString(R.string.criteria_setup_create_successful)
            val downloadImage = dialog.findViewById(R.id.success_image) as ImageView
            downloadImage.setImageResource(R.drawable.download_dialog_backgroun_2)
            val doneButton = dialog.findViewById(R.id.downloadDoneButton) as Button
            doneButton.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }


    private fun openSetCriteriaBottomSheet() {
        val dialog = BottomSheetDialog(requireContext())
        //val dialogView =
        //  layoutInflater.inflate(R.layout.fragment_criteria_input_selection_dialog, null)
        dialog.setContentView(R.layout.fragment_criteria_input_selection_dialog)
        val btnCancel = dialog.findViewById<Button>(R.id.criteriaSelectionCancelButton)
        val btnSingleInput = dialog.findViewById<Button>(R.id.singleInputButton)
        val btnYesNoInput = dialog.findViewById<Button>(R.id.inputButtonYesNo)
        val btnFileInput = dialog.findViewById<Button>(R.id.inputButtonFile)
        val btnMultipleInput = dialog.findViewById<Button>(R.id.inputButtonMultiple)
        val btnMultiChoiceInput =
            dialog.findViewById<Button>(R.id.inputButtonMultiChoice)

        btnCancel?.setOnClickListener {
            dialog.dismiss()
        }
        btnSingleInput?.setOnClickListener {
            val action =
                SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaSingleInputFragment(
                    ""
                )
            view?.findNavController()?.navigate(action)
            dialog.dismiss()
        }
        btnYesNoInput?.setOnClickListener {
            val action =
                SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaYesNoInputFragment(
                    ""
                )
            view?.findNavController()?.navigate(action)
            dialog.dismiss()
        }
        btnFileInput?.setOnClickListener {
            val action =
                SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaFileInputFragment(
                    false
                )
            view?.findNavController()?.navigate(action)
            dialog.dismiss()
        }
        btnMultipleInput?.setOnClickListener {
            val action =
                SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaMultipleInputsFragment(
                    ""
                )
            view?.findNavController()?.navigate(action)
            dialog.dismiss()
        }
        btnMultiChoiceInput?.setOnClickListener {
            val action =
                SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaMultiChoiceFragment(
                    ""
                )
            view?.findNavController()?.navigate(action)
            dialog.dismiss()
        }

        dialog.setCancelable(false)

        //dialog.setContentView(dialogView)

        dialog.show()
    }

    /**
     * Set up single inputs layout
     * show already created single inputs criteria
     */
    fun setUpSingleInputsLayout() {
        viewModel.criteriaSingleInputs.value?.let {
            if (it.size > 0) {
                criteriaSingleInputs = it
                for (i in 0 until criteriaSingleInputs.size) {
                    val item = criteriaSingleInputs[i]
                    val input: ConstraintLayout =
                        layoutInflater.inflate(
                            R.layout.item_criteria_setup_input,
                            binding.criteriaSingleInputsContainer, false
                        ) as ConstraintLayout
                    input.id = View.generateViewId()
                    val questionTextView = input.findViewById<TextView>(R.id.question_textview)
                    questionTextView.text = item
                    addTextInput(input.findViewById<LinearLayout>(R.id.item_input_container))
                    val deleteButton =
                        input.findViewById<ImageButton>(R.id.item_delete_input_button)
                    deleteButton.tag = i
                    deleteButton.setOnClickListener {

                        criteriaSingleInputs.remove(criteriaSingleInputs[it.tag as Int])

                        viewModel.setCriteriaSingleInputs(criteriaSingleInputs)


                    }
                    val editButton = input.findViewById<ImageButton>(R.id.item_edit_input_button)
                    editButton.tag = item
                    editButton.setOnClickListener {
                        val action =
                            SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaSingleInputFragment(
                                editButton.tag as String
                            )
                        view?.findNavController()?.navigate(action)
                    }
                    binding.criteriaSingleInputsContainer.addView(input)
                }
                binding.criteriaSingleInputsContainer.isVisible = true
            }
        }
    }

    /**
     * Set up yes no inputs layout
     * show already created yes/no inputs criteria
     */
    fun setUpYesNoInputsLayout() {
        viewModel.criteriaYesNoInputs.value?.let {
            if (it.size > 0) {
                criteriaYesNoInputs = it
                for (i in 0 until criteriaYesNoInputs.size) {
                    val item = criteriaYesNoInputs[i]
                    val input: ConstraintLayout =
                        layoutInflater.inflate(
                            R.layout.item_criteria_setup_yesno_input,
                            binding.criteriaYesNoContainer, false
                        ) as ConstraintLayout
                    input.id = View.generateViewId()
                    val questionTextView = input.findViewById<TextView>(R.id.question_textview)
                    questionTextView.text = item
                    val deleteButton =
                        input.findViewById<ImageButton>(R.id.item_delete_yesno_input_button)
                    deleteButton.tag = i
                    deleteButton.setOnClickListener {
                        criteriaYesNoInputs.remove(criteriaYesNoInputs[it.tag as Int])
                        viewModel.setCriteriaYesNoInputs(criteriaYesNoInputs)
                    }
                    val editButton =
                        input.findViewById<ImageButton>(R.id.item_edit_yesno_input_button)
                    editButton.tag = item
                    editButton.setOnClickListener {
                        val action =
                            SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaYesNoInputFragment(
                                editButton.tag as String
                            )
                        view?.findNavController()?.navigate(action)
                    }
                    binding.criteriaYesNoContainer.addView(input)
                }
                binding.criteriaYesNoContainer.isVisible = true
            }
        }
    }

    /**
     * Set up multi choice inputs layout
     *show already created multichoices inputs criteria
     */
    fun setUpMultiChoiceInputsLayout() {
        viewModel.criteriaMultiChoicesInputs.value?.let {
            multiChoicesInputs = it
            for (item in multiChoicesInputs) {
                if (item.key.isNotEmpty() && item.value.size > 0) {
                    binding.multiChoiceQuestionTextview.text = item.key
                    addChipsToChipGroupe(item.value)
                }
                binding.choicesDeleteInputButton.tag = item.key
                binding.choicesDeleteInputButton.setOnClickListener {
                    multiChoicesInputs.remove(it.tag as String)
                    viewModel.setCriteriaMultiChoicesInputs(multiChoicesInputs)
                }
                binding.choicesEditInputButton.tag = item.key
                binding.choicesEditInputButton.setOnClickListener {
                    val action =
                        SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaMultiChoiceFragment(
                            binding.choicesEditInputButton.tag as String
                        )
                    view?.findNavController()?.navigate(action)
                }

                binding.multiChoiceContainer.isVisible = true
            }


        }
    }

    private fun addChipsToChipGroupe(stringArray: ArrayList<String>) {
        for (choice in stringArray) {
            val chip: Chip =
                layoutInflater.inflate(
                    R.layout.item_criteria_chip,
                    binding.multiChoiceChipGroup, false
                ) as Chip
            chip.text = choice
            binding.multiChoiceChipGroup.addView(chip)
        }
    }

    /**
     * Set up file inputs layout
     * show already created file inputs criteria
     */
    fun setUpFileInputsLayout() {
        viewModel.criteriaFileInputs.value?.let {
            if (it.size > 0) {
                criteriaFileInputs = it
                binding.fileQuestionTextview.text = viewModel.criteriaFileQuestionInput.value
                for (i in 0 until criteriaFileInputs.size) {
                    val field = criteriaFileInputs[i]
                    val input: ConstraintLayout =
                        layoutInflater.inflate(
                            R.layout.item_criteria_setup_file_input,
                            binding.criteriaYesNoContainer, false
                        ) as ConstraintLayout
                    input.id = View.generateViewId()
                    val fileButton = input.findViewById<Button>(R.id.file_button)
                    val chooseButton = input.findViewById<Button>(R.id.choose_button)
                    val deleteButton = input.findViewById<ImageButton>(R.id.item_input_delete_file)
                    deleteButton.tag = i
                    fileButton.text = field.fileName + "." + field.fileType
                    deleteButton.setOnClickListener {
                        criteriaFileInputs.remove(criteriaFileInputs[it.tag as Int])
                        viewModel.setCriteriaFileInputs(criteriaFileInputs)
                    }
                    binding.criteriaFileInputsContainer.addView(input)

                }
                binding.fileDeleteButton.setOnClickListener {
                    criteriaFileInputs.clear()
                    viewModel.setCriteriaFileInputs(criteriaFileInputs)
                    binding.fileInputContainer.isVisible = false
                }
                binding.fileEditButton.setOnClickListener {
                    val action =
                        SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaFileInputFragment(
                            true
                        )
                    view?.findNavController()?.navigate(action)
                }
                binding.fileInputContainer.isVisible = true
            }
        }

    }

    /**
     * Set up multiple inputs layout
     * show already created multiple inputs criteria
     */
    fun setUpMultipleInputsLayout() {
        viewModel.criteriaMultipleInputs.value?.let {
            criteriaMultipleInputs = it
            for (item in criteriaMultipleInputs) {
                val input: ConstraintLayout =
                    layoutInflater.inflate(
                        R.layout.item_criteria_setup_input,
                        binding.criteriaMultipleInputsContainer, false
                    ) as ConstraintLayout
                input.id = View.generateViewId()
                val questionTextView = input.findViewById<TextView>(R.id.question_textview)
                questionTextView.text = item.key
                for (i in 1..item.value) {
                    addTextInput(input.findViewById<LinearLayout>(R.id.item_input_container))
                }
                val deleteButton =
                    input.findViewById<ImageButton>(R.id.item_delete_input_button)
                deleteButton.tag = item.key
                deleteButton.setOnClickListener {
                    criteriaMultipleInputs.remove(it.tag)
                    viewModel.setCriteriaMultipleInputs(criteriaMultipleInputs)
                }
                val editButton = input.findViewById<ImageButton>(R.id.item_edit_input_button)
                editButton.tag = item.key
                editButton.setOnClickListener {
                    val action =
                        SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaMultipleInputsFragment(
                            editButton.tag as String
                        )
                    view?.findNavController()?.navigate(action)
                }
                binding.criteriaMultipleInputsContainer.addView(input)
            }
            binding.criteriaMultipleInputsContainer.isVisible = true

        }
    }

    /**
     * Add text input
     *
     * @param mView
     */
    fun addTextInput(mView: ViewGroup) {
        val textInput: TextInputLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_input_text,
                mView, false
            ) as TextInputLayout

        textInput.id = View.generateViewId()

        mView.addView(textInput)
    }


}