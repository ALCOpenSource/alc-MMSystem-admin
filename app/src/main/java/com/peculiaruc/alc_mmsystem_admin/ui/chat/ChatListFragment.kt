package com.peculiaruc.alc_mmsystem_admin.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentChatListBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Chat
import com.peculiaruc.alc_mmsystem_admin.ui.adapters.MentorChatAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * Fragment for mentor chat list
 */

class ChatListFragment : BaseFragment<FragmentChatListBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_chat_list
    override val viewModel: ChatViewModel by viewModels()

    lateinit var recyclerView: RecyclerView
    private var chatList: List<Chat>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true,getString(R.string.messages_title))
        binding.apply {
            fabAddChat.setOnClickListener {
                findNavController().navigate(MessageFragmentDirections.actionMessageFragmentToChatFragment())
            }
        }

        recyclerView = binding.recyclerViewMentorChat
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val mentorListAdapter = MentorChatAdapter(requireContext(), null)

        recyclerView.adapter = mentorListAdapter
    }

}