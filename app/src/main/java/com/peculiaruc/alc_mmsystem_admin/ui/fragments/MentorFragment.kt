package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentMentorBinding

import com.peculiaruc.alc_mmsystem_admin.ui.database.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class MentorFragment : Fragment() {

    private lateinit var binding: FragmentMentorBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMentorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            mentorToolbar.inflateMenu(R.menu.mentor_list_menu)
            mentorToolbar.setNavigationIcon(R.drawable.arrow_left)
            mentorToolbar.title = "Mentors"

            fabAddMentor.setOnClickListener {
                findNavController().navigate(MentorFragmentDirections.actionMentorFragmentToAddMentorDialog())
            }
        }



        recyclerView = binding.recyclerViewMentor
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val mentorListAdapter = MentorListAdapter()

        recyclerView.adapter = mentorListAdapter



    }


}