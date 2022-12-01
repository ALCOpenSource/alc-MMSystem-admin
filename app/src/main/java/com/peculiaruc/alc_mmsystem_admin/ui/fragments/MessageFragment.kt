package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentMessageBinding
import com.peculiaruc.alc_mmsystem_admin.ui.adapters.MessageViewPagerAdapter


class MessageFragment : Fragment() {

    private lateinit var binding:FragmentMessageBinding

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
        val navHostFragment = parentFragment as NavHostFragment
        val parent = navHostFragment.parentFragment as Fragment
        val bottomNavigationView= parent.view?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position->
            tab.text = tabsArray[position]
        }.attach()



       viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
           override fun onPageSelected(position: Int) {
               super.onPageSelected(position)

              if (viewPager.currentItem == 1){
                  bottomNavigationView?.visibility = View.GONE
              }else{
                  bottomNavigationView?.visibility = View.VISIBLE
              }
           }
       })



    }


}