package com.peculiaruc.alc_mmsystem_admin.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentMessageBinding

/**
 * fragment for message screen tab
 * hosted in the hello fragment
 */

class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding

    private val tabsArray = arrayOf("Chats", "Broadcast Messages")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentMessageBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val adapter = MessageViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()



        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

//                if (viewPager.currentItem == 1) {
//                    bottomNavigationView?.visibility = View.GONE
//                } else {
//                    bottomNavigationView?.visibility = View.VISIBLE
//                }
            }
        })


    }


}