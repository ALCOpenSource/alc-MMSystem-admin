package com.peculiaruc.alc_mmsystem_admin.ui.chat.conversation

import androidx.fragment.app.viewModels
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentChatBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

class ConversationFragment : BaseFragment<FragmentChatBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_chat
    override val viewModel: ConversationViewModel by viewModels()

}