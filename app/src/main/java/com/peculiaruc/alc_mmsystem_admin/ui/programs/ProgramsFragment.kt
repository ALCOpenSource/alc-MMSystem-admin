package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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
        return root
    }


}