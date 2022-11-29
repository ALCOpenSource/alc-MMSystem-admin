package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentChatBinding
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentLoginBinding

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val navHostFragment = parentFragment as NavHostFragment
        navHostFragment?.let {
            navHostFragment ->
            navHostFragment.parentFragment?.let {
                parentFragment ->
                var toolbar = parentFragment.view?.findViewById<Toolbar>(R.id.toolbar_main)
                toolbar?.title = "Kenny Dabiri"
            }
        }

        binding.apply {
            recyclerViewChat.layoutManager = layoutManager


        }
    }
}