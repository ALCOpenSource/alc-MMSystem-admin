package com.peculiaruc.alc_mmsystem_admin.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentChatListBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Chat
import com.peculiaruc.alc_mmsystem_admin.ui.adapters.MentorChatAdapter

/**
 * Fragment for mentor chat list
 */

class ChatListFragment : Fragment() {


    private lateinit var binding: FragmentChatListBinding
    lateinit var recyclerView: RecyclerView
    private var chatList: List<Chat>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChatListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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