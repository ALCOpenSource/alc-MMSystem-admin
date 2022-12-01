package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentBroadcastBinding

/**
 * fragment for the broadcast page in viewpager on the message fragment
 */
class BroadcastFragment : Fragment() {

private lateinit var binding: FragmentBroadcastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBroadcastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}