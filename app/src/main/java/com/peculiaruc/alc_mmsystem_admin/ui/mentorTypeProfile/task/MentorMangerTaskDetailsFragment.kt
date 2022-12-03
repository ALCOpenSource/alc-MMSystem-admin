package com.peculiaruc.alc_mmsystem_admin.ui.mentorTypeProfile.task

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentMentorMangerTaskBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.TaskDetails
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.dialogs.DialogTypes
import com.peculiaruc.alc_mmsystem_admin.utilities.event.EventObserve


/**
 * task details fragment to display the task details
 */
class MentorMangerTaskDetailsFragment : BaseFragment<FragmentMentorMangerTaskBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_mentor_manger_task
    override val viewModel: MentorMangerTaskDetailsViewModel by viewModels()

    //Just For Test
    private val list = listOf(
        TaskDetails("Mentor Managers assigned to this Task", 12),
        TaskDetails("Mentor Managers assigned to this Task", 12),
        TaskDetails("Task Reports", 12),
        TaskDetails("Task Reports", 12),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.task_title))
        setBottomNavigationVisibility(false)
        binding.taskRecycler.adapter = MentorMangerTaskDetailsAdapter(list, viewModel)
        onEvents()
    }

    private fun onEvents() {
        viewModel.assignToTaskEvent.observe(viewLifecycleOwner, EventObserve {
            val type = if (it) {
                DialogTypes.UNASSIGNED_TASK
            } else {
                DialogTypes.ASSIGNED_TASK
            }
            findNavController().navigate(
                MentorMangerTaskDetailsFragmentDirections.actionMentorMangerTaskDetailsFragmentToBasicDialog(
                    type
                )
            )
            viewModel.setAssigned(!it)
        })

        viewModel.viewTaskEvent.observe(viewLifecycleOwner, EventObserve {
            Toast.makeText(requireContext(), "Open another Fragment", Toast.LENGTH_LONG).show()
        })
    }
}