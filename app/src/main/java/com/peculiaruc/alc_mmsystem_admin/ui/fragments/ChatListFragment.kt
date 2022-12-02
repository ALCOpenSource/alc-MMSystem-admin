package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.database.Chat
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentChatListBinding
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentMentorBinding
import com.peculiaruc.alc_mmsystem_admin.ui.adapters.MentorChatAdapter
import com.peculiaruc.alc_mmsystem_admin.ui.adapters.MentorListAdapter

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