package com.peculiaruc.alc_mmsystem_admin.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.fragments.BroadcastFragment
import com.peculiaruc.alc_mmsystem_admin.ui.fragments.ChatListFragment

/**
 * Viewpager adapter for the tab layout on the messsage fragment
 * hosted in the home screen
 */

class MessageViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
     return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return ChatListFragment()
        }
        return BroadcastFragment()
    }
}