package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentMessageBinding
import com.peculiaruc.alc_mmsystem_admin.ui.adapters.ViewPagerAdapter


class MessageFragment : Fragment() {

    private lateinit var binding:FragmentMessageBinding

    val tabsArray = arrayOf("Chats", "Broadcast Messages")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentMessageBinding.inflate(layoutInflater)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position->
            tab.text = tabsArray[position]
        }.attach()

        return binding.root
    }


}