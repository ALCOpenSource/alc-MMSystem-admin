package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.peculiaruc.alc_mmsystem_admin.R

class ProgramsFragment : Fragment() {

    private val TAG = "ProgramsTag"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_programs, container, false)
        val root = inflater.inflate(R.layout.fragment_programs, container, false)
        val bt = root.findViewById<Button>(R.id.programDetailsButton)
        bt.setOnClickListener {
            val action =
                ProgramsFragmentDirections.actionProgramsFragmentToProgramDetailsFragment(1)
            view?.findNavController()?.navigate(action)
        }
        val criteriaBt = root.findViewById<Button>(R.id.criteriaButton)
        criteriaBt.setOnClickListener {
            /*var dialogFragment=CriteriaInputSelectionDialogFragment()
            dialogFragment.view?.layoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            dialogFragment.show(childFragmentManager,"")*/
            // on below line we are creating a new bottom sheet dialog.
            context?.let { it1 ->
                val dialog = BottomSheetDialog(it1)

                // on below line we are inflating a layout file which we have created.
                val dialogView =
                    layoutInflater.inflate(R.layout.fragment_criteria_input_selection_dialog, null)

                // on below line we are creating a variable for our button
                // which we are using to dismiss our dialog.
                val btnCancel = dialogView.findViewById<Button>(R.id.criteriaSelectionCancelButton)
                val btnSingleInput = dialogView.findViewById<Button>(R.id.singleInputButton)
                val btnYesNoInput = dialogView.findViewById<Button>(R.id.inputButtonYesNo)
                val btnFileInput = dialogView.findViewById<Button>(R.id.inputButtonFile)
                val btnMultipleInput = dialogView.findViewById<Button>(R.id.inputButtonMultiple)
                val btnMultiChoiceInput =
                    dialogView.findViewById<Button>(R.id.inputButtonMultiChoice)

                btnCancel.setOnClickListener {
                    //dialog.dismiss()
                    Log.i(TAG, "btn cancled clicked")
                    val action =
                        ProgramsFragmentDirections.actionProgramsFragmentToCriteriaMultiChoiceFragment()
                    view?.findNavController()?.navigate(action)
                }
                btnSingleInput.setOnClickListener {
                    Log.i(TAG, "btnSingleInput clicked")
                }
                btnYesNoInput.setOnClickListener {
                    Log.i(TAG, "btnYesNoInput clicked")
                }
                btnFileInput.setOnClickListener {
                    Log.i(TAG, "btnFileInput clicked")
                }
                btnMultipleInput.setOnClickListener {
                    Log.i(TAG, "btn multiple choices clicked")
                }
                btnMultiChoiceInput.setOnClickListener {
                    Log.i(TAG, "btnMultiChoiceInput clicked")
                    val action =
                        ProgramsFragmentDirections.actionProgramsFragmentToCriteriaMultiChoiceFragment()
                    view?.findNavController()?.navigate(action)
                    dialog.dismiss()
                }

                dialog.setCancelable(false)

                // on below line we are setting
                // content view to our view.
                dialog.setContentView(dialogView)

                // on below line we are calling
                // a show method to display a dialog.
                dialog.show()
            }
        }
        return root
    }


}
