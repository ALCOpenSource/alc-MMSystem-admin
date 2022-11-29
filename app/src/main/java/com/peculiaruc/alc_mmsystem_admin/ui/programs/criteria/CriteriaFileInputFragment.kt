package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaInputFileBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.Criteria
import com.peculiaruc.alc_mmsystem_admin.ui.programs.models.FileField
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Criteria file input fragment
 *
 * @constructor Create empty Criteria file input fragment
 */
class CriteriaFileInputFragment : BaseFragment<FragmentCriteriaInputFileBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_criteria_input_file
    override val viewModel: CriteriaViewModel by viewModels()
    private val TAG = "CriteriaTagFile"
    private lateinit var fileTypeAdapter: ArrayAdapter<CharSequence>
    private lateinit var fileQtyAdapter: ArrayAdapter<CharSequence>

    private var fileInputs = ArrayList<FileField>()
    private var fileNames = HashMap<Int, String>()
    private var fileTypes = HashMap<Int, String>()
    private var fileQty = HashMap<Int, Int>()
    private var fileQtyStringArray =
        ArrayList<String>() //array of strings from strings.xml R.array.file_qty_array
    private var fieldsNumber = 0 //number of files fields added
    private val CRITERIA_KEY = "isEdit"
    private var criteriaIsEdit =
        false // false if new criteria and true if it is editing existing criteria
    private var editedQuestion = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.criteria_file_input_title))
        arguments?.let {
            criteriaIsEdit = it.getBoolean(CRITERIA_KEY, false)
        }
        //set adapter for file type dropdown menu
        fileTypeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.file_type_array,
            android.R.layout.simple_list_item_1
        )
        fileQtyStringArray =
            ArrayList(resources.getStringArray(R.array.file_qty_array).toMutableList())
        //set adapter for file qty dropdown menu
        fileQtyAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.file_qty_array,
            android.R.layout.simple_list_item_1
        )
        if (!criteriaIsEdit) {
            //new criteria
            binding.inputsContainer.addView(addField())
            binding.inputsContainer.addView(addField())
            binding.inputsContainer.addView(addField())
        } else {
            //editing existing criteria
            fileInputs = Criteria.criteriaFileInputs.value!!
            editedQuestion = Criteria.criteriaFileQuestionInput.value!!
            setUpCriteria()
        }


        binding.addInputButton.setOnClickListener {
            val field = addField()
            binding.inputsContainer.addView(field)
        }

        binding.inputCriteriaCancelBT.setOnClickListener {
            view.findNavController().popBackStack()
        }
        binding.inputCriteriaDonelBT.setOnClickListener {
            if (criteriaIsEdit) {
                fileInputs.clear()
            }
            for (key in fileNames.keys) {
                val qty = fileQty[key] ?: 0
                val type = fileTypes[key] ?: ""
                val name = fileNames[key] ?: ""
                fileInputs.add(FileField(name, type, qty))
            }
            Criteria.criteriaFileQuestionInput.value =
                binding.questionInputText.editText?.text.toString()
            Criteria.criteriaFileInputs.value = fileInputs
            Log.i(TAG, "fileInputs:" + fileInputs)

            view.findNavController().popBackStack()
        }
    }

    /**
     * Add field
     * create LinearLayout contains file name, type and qty fields
     *
     * @return LinearLayout to be added to view
     */
    fun addField(): LinearLayout {
        val layout: LinearLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_input_file_field,
                binding.inputsContainer, false
            ) as LinearLayout

        fieldsNumber++
        val fileNameEditText = layout.findViewById<EditText>(R.id.file_name_edittext)
        val labelTextView = layout.findViewById<TextView>(R.id.file_label)
        val fileType = layout.findViewById<TextInputLayout>(R.id.type_file)
        val fileQty = layout.findViewById<TextInputLayout>(R.id.qty_file)

        fileNameEditText.id = View.generateViewId()
        labelTextView.id = View.generateViewId()
        fileType.id = View.generateViewId()
        fileQty.id = View.generateViewId()
        setupFileInputs(fieldsNumber, labelTextView, fileNameEditText, fileType, fileQty)
        return layout
    }


    /**
     * Setup file inputs
     * set adapters and listeners to previously created subviews of layout
     *
     * @param index
     * @param label
     * @param filename
     * @param filetype
     * @param fileqty
     */
    fun setupFileInputs(
        index: Int,
        label: TextView,
        filename: EditText,
        filetype: TextInputLayout,
        fileqty: TextInputLayout
    ) {
        label.text = "$index."
        filename.addTextChangedListener {
            fileNames[index] = it.toString()
        }
        (fileqty.editText as? AutoCompleteTextView)?.setAdapter(fileQtyAdapter)
        (fileqty.editText as? AutoCompleteTextView)?.setOnItemClickListener { parent, view, position, id ->

            fileQty[index] = fileQtyStringArray[position].toInt()
        }
        (filetype.editText as? AutoCompleteTextView)?.setAdapter(fileTypeAdapter)
        (filetype.editText as? AutoCompleteTextView)?.setOnItemClickListener { parent, view, position, id ->
            val text = fileTypeAdapter.getItem(position)
            fileTypes[index] = text.toString()
        }

    }

    /**
     * Add field with data to previously created subviews of layout
     *create LinearLayout contains file name, type and qty fields
     * same feature as addField but from existing data stored in FileFiedl object
     * @param field
     * @return LinearLayout to be added to view
     */
    fun addFieldWithData(field: FileField): LinearLayout {
        val layout: LinearLayout =
            layoutInflater.inflate(
                R.layout.item_criteria_input_file_field,
                binding.inputsContainer, false
            ) as LinearLayout

        fieldsNumber++
        val fileNameEditText = layout.findViewById<EditText>(R.id.file_name_edittext)
        val labelTextView = layout.findViewById<TextView>(R.id.file_label)
        val fileType = layout.findViewById<TextInputLayout>(R.id.type_file)
        val fileQty = layout.findViewById<TextInputLayout>(R.id.qty_file)

        fileNameEditText.id = View.generateViewId()
        labelTextView.id = View.generateViewId()
        fileType.id = View.generateViewId()
        fileQty.id = View.generateViewId()
        setupFileInputs(fieldsNumber, labelTextView, fileNameEditText, fileType, fileQty)
        setDataToFileInputs(fieldsNumber, fileNameEditText, fileType, fileQty, field)
        return layout
    }

    /**
     * Set data to file inputs
     *
     * @param index
     * @param filename
     * @param filetype
     * @param fileqty
     * @param field
     */

    fun setDataToFileInputs(
        index: Int,
        filename: EditText,
        filetype: TextInputLayout,
        fileqty: TextInputLayout, field: FileField
    ) {
        filename.setText(field.fileName)
        filetype.editText?.setText(field.fileType)
        fileqty.editText?.setText("" + field.fileQty)

        fileNames[index] = field.fileName
        fileQty[index] = field.fileQty
        fileTypes[index] = field.fileType
    }


    private fun setUpCriteria() {
        binding.questionInputText.editText?.setText(editedQuestion)
        for (item in fileInputs) {
            binding.inputsContainer.addView(addFieldWithData(item))
        }
    }

}