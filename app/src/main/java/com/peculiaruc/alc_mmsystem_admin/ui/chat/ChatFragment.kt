package com.peculiaruc.alc_mmsystem_admin.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentChatBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * fragment for the chatting with unique mentor
 */

class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_chat
    override val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        setBottomNavigationVisibility(false)

    }
}