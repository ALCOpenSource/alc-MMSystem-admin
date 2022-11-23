package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.peculiaruc.alc_mmsystem_admin.R

class ProgramsFragment : Fragment() {


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
                val view =
                    layoutInflater.inflate(R.layout.fragment_criteria_input_selection_dialog, null)

                // on below line we are creating a variable for our button
                // which we are using to dismiss our dialog.
                val btnCancel = view.findViewById<Button>(R.id.criteriaSelectionCancelButton)
                val btnSingleInput = view.findViewById<Button>(R.id.singleInputButton)
                val btnYesNoInput = view.findViewById<Button>(R.id.inputButtonYesNo)
                val btnFileInput = view.findViewById<Button>(R.id.inputButtonFile)
                val btnMultipleInput = view.findViewById<Button>(R.id.inputButtonMultiple)
                val btnMultiChoiceInput = view.findViewById<Button>(R.id.inputButtonMultiChoice)

                btnCancel.setOnClickListener {
                    dialog.dismiss()
                }
                btnSingleInput.setOnClickListener {

                }
                btnYesNoInput.setOnClickListener {

                }
                btnFileInput.setOnClickListener {

                }
                btnMultipleInput.setOnClickListener {

                }
                btnMultiChoiceInput.setOnClickListener {

                }

                dialog.setCancelable(false)

                // on below line we are setting
                // content view to our view.
                dialog.setContentView(view)

                // on below line we are calling
                // a show method to display a dialog.
                dialog.show()
            }
        }
        return root
    }


}
