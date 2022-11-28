package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaSetupBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.Criteria
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.FileField

/**
 * Set up criteria fragment
 *
 * @constructor Create empty Set up criteria fragment
 */
class SetUpCriteriaFragment : BaseFragment<FragmentCriteriaSetupBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_setup
    override val viewModel: CriteriaViewModel by viewModels()
    private val TAG = "CriteriaTagSetup"

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

        Criteria.criteriaFileInputs.observe(this.viewLifecycleOwner) {
            clearFileInputsLayout()
            setUpFileInputsLayout()
        }
        Criteria.criteriaMultipleInputs.observe(this.viewLifecycleOwner) {
            clearMultipleInputsLayout()
            setUpMultipleInputsLayout()
        }
        Criteria.criteriaSingleInputs.observe(this.viewLifecycleOwner) {
            clearSingleInputsLayout()
            setUpSingleInputsLayout()
        }
        Criteria.criteriaMultiChoicesInputs.observe(this.viewLifecycleOwner) {
            clearMultiChoiceInputsLayout()
            setUpMultiChoiceInputsLayout()
        }
        Criteria.criteriaYesNoInputs.observe(this.viewLifecycleOwner) {
            clearYesNoInputsLayout()
            setUpYesNoInputsLayout()
        }


        binding.addCriteriaButton.setOnClickListener() {
            openSetCriteriaBottomSheet()
        }
        binding.createCriteriaButton.setOnClickListener() {
            showCreateSuccessfulDialog()
        }
    }

    /**
     * Show create successful dialog
     *
     */
    private fun showCreateSuccessfulDialog() {
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

    /**
     * Open set criteria bottom sheet
     * to navigate to different types criteria fragments
     */
    private fun openSetCriteriaBottomSheet() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogView =
            layoutInflater.inflate(R.layout.fragment_criteria_input_selection_dialog, null)

        val btnCancel = dialogView.findViewById<Button>(R.id.criteriaSelectionCancelButton)
        val btnSingleInput = dialogView.findViewById<Button>(R.id.singleInputButton)
        val btnYesNoInput = dialogView.findViewById<Button>(R.id.inputButtonYesNo)
        val btnFileInput = dialogView.findViewById<Button>(R.id.inputButtonFile)
        val btnMultipleInput = dialogView.findViewById<Button>(R.id.inputButtonMultiple)
        val btnMultiChoiceInput =
            dialogView.findViewById<Button>(R.id.inputButtonMultiChoice)

        btnCancel.setOnClickListener {
            dialog.dismiss()
            Log.i(TAG, "btn cancled clicked")
        }
        btnSingleInput.setOnClickListener {
            Log.i(TAG, "btnSingleInput clicked")
            openSingleInputFragment("")
            dialog.dismiss()
        }
        btnYesNoInput.setOnClickListener {
            Log.i(TAG, "btnYesNoInput clicked")
            openYesNoFragment("")
            dialog.dismiss()
        }
        btnFileInput.setOnClickListener {
            Log.i(TAG, "btnFileInput clicked")
            openFileINputFragment(false)
            dialog.dismiss()
        }
        btnMultipleInput.setOnClickListener {
            Log.i(TAG, "btn multiple choices clicked")
            openMultipleInputsFragment("")
            dialog.dismiss()
        }
        btnMultiChoiceInput.setOnClickListener {
            Log.i(TAG, "btnMultiChoiceInput clicked")
            openMultiChoicesFragment("")
            dialog.dismiss()
        }

        dialog.setCancelable(false)

        dialog.setContentView(dialogView)

        dialog.show()
    }

    /**
     * Set up single inputs layout
     * show already created single inputs criteria
     */
    fun setUpSingleInputsLayout() {
        Criteria.criteriaSingleInputs.value?.let {
            if (it.size > 0) {
                criteriaSingleInputs = it
                for (i in 0 until criteriaSingleInputs.size) {
                    val item = criteriaSingleInputs.get(i)
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
                    deleteButton.setOnClickListener() {
                        Log.i(TAG, "before criteriaSingleInputs:" + criteriaSingleInputs)
                        Log.i(
                            TAG,
                            "tag=" + it.tag + "  item:" + criteriaSingleInputs.get(it.tag as Int)
                        )
                        criteriaSingleInputs.remove(criteriaSingleInputs.get(it.tag as Int))
                        Log.i(TAG, "after criteriaSingleInputs:" + criteriaSingleInputs)

                        Criteria.criteriaSingleInputs.value = criteriaSingleInputs


                    }
                    val editButton = input.findViewById<ImageButton>(R.id.item_edit_input_button)
                    editButton.tag = item
                    editButton.setOnClickListener() {
                        openSingleInputFragment(editButton.tag as String)
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
        Log.i(TAG, "criteriayesnoInputs:" + Criteria.criteriaYesNoInputs.value)
        Criteria.criteriaYesNoInputs.value?.let {
            if (it.size > 0) {
                criteriaYesNoInputs = it
                for (i in 0 until criteriaYesNoInputs.size) {
                    val item = criteriaYesNoInputs.get(i)
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
                    deleteButton.setOnClickListener() {
                        criteriaYesNoInputs.remove(criteriaYesNoInputs.get(it.tag as Int))
                        Criteria.criteriaYesNoInputs.value = criteriaYesNoInputs
                    }
                    val editButton =
                        input.findViewById<ImageButton>(R.id.item_edit_yesno_input_button)
                    editButton.tag = item
                    editButton.setOnClickListener() {
                        openYesNoFragment(editButton.tag as String)
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
        Log.i(TAG, "criteriaMultiChoicesInputs:" + Criteria.criteriaMultiChoicesInputs.value)
        Criteria.criteriaMultiChoicesInputs.value?.let {
            if (it.size > 0) {
                multiChoicesInputs = it
                for (item in multiChoicesInputs) {
                    if (item.key.length > 0 && item.value.size > 0) {
                        binding.multiChoiceQuestionTextview.text = item.key
                        Log.i(TAG, "item value:" + item.value)
                        for (choice in item.value) {
                            Log.i(TAG, "choice:" + choice)

                            val chip: Chip =
                                layoutInflater.inflate(
                                    R.layout.item_criteria_chip,
                                    binding.multiChoiceChipGroupe, false
                                ) as Chip
                            chip.text = choice
                            Log.i(TAG, "chip:" + chip.text)

                            binding.multiChoiceChipGroupe.addView(chip)
                        }
                        binding.choicesDeleteInputButton.tag = item.key
                        binding.choicesDeleteInputButton.setOnClickListener() {
                            multiChoicesInputs.remove(it.tag as String)
                            Criteria.criteriaMultiChoicesInputs.value = multiChoicesInputs
                        }
                        binding.choicesEditInputButton.tag = item.key
                        binding.choicesEditInputButton.setOnClickListener() {
                            openMultiChoicesFragment(binding.choicesEditInputButton.tag as String)
                        }

                        binding.multiChoiceContainer.isVisible = true
                    }
                }
            }
        }
    }

    /**
     * Set up file inputs layout
     * show already created file inputs criteria
     */
    fun setUpFileInputsLayout() {
        Log.i(TAG, "Criteria.criteriaFileInputs:" + Criteria.criteriaFileInputs.value)

        Criteria.criteriaFileInputs.value?.let {
            if (it.size > 0) {
                criteriaFileInputs = it
                binding.fileQuestionTextview.text = Criteria.criteriaFileQuestionInput.value
                for (i in 0 until criteriaFileInputs.size) {
                    val field = criteriaFileInputs.get(i)
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
                    deleteButton.setOnClickListener() {
                        criteriaFileInputs.remove(criteriaFileInputs.get(it.tag as Int))
                        Criteria.criteriaFileInputs.value = criteriaFileInputs
                    }
                    binding.criteriaFileInputsContainer.addView(input)

                }
                binding.fileDeleteButton.setOnClickListener() {
                    criteriaFileInputs.clear()
                    Criteria.criteriaFileInputs.value = criteriaFileInputs
                    binding.fileInputContainer.isVisible = false
                }
                binding.fileEditButton.setOnClickListener() {
                    openFileINputFragment(true)
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
        Log.i(TAG, "Criteria.criteriaMultipleInputs:" + Criteria.criteriaMultipleInputs.value)
        Criteria.criteriaMultipleInputs.value?.let {
            if (it.size > 0) {
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
                    deleteButton.setOnClickListener() {
                        criteriaMultipleInputs.remove(it.tag)
                        Criteria.criteriaMultipleInputs.value = criteriaMultipleInputs
                    }
                    val editButton = input.findViewById<ImageButton>(R.id.item_edit_input_button)
                    editButton.tag = item.key
                    editButton.setOnClickListener() {
                        openMultipleInputsFragment(editButton.tag as String)
                    }
                    binding.criteriaMultipleInputsContainer.addView(input)
                }
                binding.criteriaMultipleInputsContainer.isVisible = true
            }
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

    /**
     * Clear single inputs layout
     *
     */
    fun clearSingleInputsLayout() {
        binding.criteriaSingleInputsContainer.removeAllViews()
        binding.criteriaSingleInputsContainer.isVisible = false
    }

    /**
     * Clear yes no inputs layout
     *
     */
    fun clearYesNoInputsLayout() {
        binding.criteriaYesNoContainer.removeAllViews()
        binding.criteriaYesNoContainer.isVisible = false
    }

    /**
     * Clear multi choice inputs layout
     *
     */
    fun clearMultiChoiceInputsLayout() {
        binding.multiChoiceChipGroupe.removeAllViews()
        binding.multiChoiceContainer.isVisible = false
    }

    /**
     * Clear file inputs layout
     *
     */
    fun clearFileInputsLayout() {
        binding.criteriaFileInputsContainer.removeAllViews()
        binding.fileInputContainer.isVisible = false
    }

    /**
     * Clear multiple inputs layout
     *
     */
    fun clearMultipleInputsLayout() {
        binding.criteriaMultipleInputsContainer.removeAllViews()
        binding.criteriaMultipleInputsContainer.isVisible = false
    }

    /**
     * Open yes no fragment
     *
     * @param item
     */
    private fun openYesNoFragment(item: String) {
        val action =
            SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaYesNoInputFragment(
                item
            )
        view?.findNavController()?.navigate(action)
    }

    /**
     * Open single input fragment
     *
     * @param item
     */
    private fun openSingleInputFragment(item: String) {
        val action =
            SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaSingleInputFragment(
                item
            )
        view?.findNavController()?.navigate(action)
    }

    /**
     * Open multiple inputs fragment
     *
     * @param key
     */
    private fun openMultipleInputsFragment(key: String) {
        val action =
            SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaMultipleInputsFragment(
                key
            )
        view?.findNavController()?.navigate(action)
    }

    /**
     * Open multi choices fragment
     *
     * @param key
     */
    private fun openMultiChoicesFragment(key: String) {
        val action =
            SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaMultiChoiceFragment(
                key
            )
        view?.findNavController()?.navigate(action)
    }

    /**
     * Open file i nput fragment
     *
     * @param isEdit
     */
    private fun openFileINputFragment(isEdit: Boolean) {
        val action =
            SetUpCriteriaFragmentDirections.actionSetUpCriteriaFragmentToCriteriaFileInputFragment(
                isEdit
            )
        view?.findNavController()?.navigate(action)
    }
}