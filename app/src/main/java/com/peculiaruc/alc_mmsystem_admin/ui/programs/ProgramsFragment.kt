package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentProgramsBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.ProgramAdmin
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * Programs fragment
 *
 * @constructor Create empty Programs fragment
 */
class ProgramsFragment : BaseFragment<FragmentProgramsBinding>(),
    ProgramsAdapter.ItemClickListener {

    override val layoutIdFragment: Int = R.layout.fragment_programs
    override val viewModel: ProgramsViewModel by viewModels()
    private lateinit var programsAdapter: ProgramsAdapter
    private val TAG = "ProgramsTag"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.initprograms()
        viewModel.programs.observe(viewLifecycleOwner) {
            programsAdapter.submitList(it)
        }
        viewModel.filteredPrograms.observe(viewLifecycleOwner) {
            programsAdapter.submitList(it)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.programs_title))
        setHasOptionsMenu(true)


        setUpRecycleView()
        activateAllChip()


        binding.chipAll.setOnClickListener {
            activateAllChip()
        }
        binding.chipActive.setOnClickListener {
            activateActiveChip()
        }
        binding.chipCompleted.setOnClickListener {
            activateCompletedChip()
        }

        binding.chipAll.setOnCheckedChangeListener() {compoundButton, b ->
            if (compoundButton.isChecked==true) {
                activateAllChip()
            }
        }

        binding.chipActive.setOnCheckedChangeListener() {compoundButton, b ->
            if (compoundButton.isChecked==true) {
                activateActiveChip()
            }
        }

        binding.chipCompleted.setOnCheckedChangeListener() {compoundButton, b ->
            if (compoundButton.isChecked==true) {
                activateCompletedChip()
            }
        }


        binding.addProgramFab.setOnClickListener {
            val action = ProgramsFragmentDirections.actionProgramsFragmentToNewProgramFragment()
            view.findNavController().navigate(action)
        }
    }

    /**
     * activate chipAll
     */
    private fun activateAllChip() {
        viewModel.filteredPrograms.value = viewModel.programsAllList
        binding.chipAll.isSelected = true
        binding.chipActive.isSelected = false
        binding.chipCompleted.isSelected = false
        binding.chipActive.isChecked = false
        binding.chipCompleted.isChecked = false
        Log.i(TAG,"chipAll.isSelected: "+binding.chipAll.isSelected+"chipAll.ischecked: "+binding.chipAll.isChecked)
        Log.i(TAG,"chipActive.isSelected: "+binding.chipActive.isSelected+"chipActive.ischecked: "+binding.chipActive.isChecked)
        Log.i(TAG,"chipCompleted.isSelected: "+binding.chipCompleted.isSelected+"chipCompleted.ischecked: "+binding.chipCompleted.isChecked)
    }

    /**
     * activate chipActive
     */
    private fun activateActiveChip() {
        viewModel.filteredPrograms.value = viewModel.programsActiveList
        binding.chipAll.isSelected = false
        binding.chipActive.isSelected = true
        binding.chipCompleted.isSelected = false
        binding.chipAll.isChecked = false
        binding.chipCompleted.isChecked = false
    }

    /**
     * activate chipComplete
     */
    private fun activateCompletedChip() {
        viewModel.filteredPrograms.value = viewModel.programsCompletedList
        binding.chipAll.isSelected = false
        binding.chipActive.isSelected = false
        binding.chipCompleted.isSelected = true
        binding.chipActive.isChecked = false
        binding.chipAll.isChecked = false
    }

    /**
     * Set up recycle view
     *
     */
    fun setUpRecycleView() {
        binding.programsListView.layoutManager = LinearLayoutManager(requireContext())
        programsAdapter = ProgramsAdapter(this)
        binding.programsListView.adapter = programsAdapter

    }

    /**
     * On program item click
     *
     * @param item
     */
    override fun onItemClick(item: ProgramAdmin) {
        val action =
            ProgramsFragmentDirections.actionProgramsFragmentToProgramDetailsFragment(item.id)
        view?.findNavController()?.navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i(TAG, "onCreateOptionsMenu")
        //menu.clear()
        inflater.inflate(R.menu.menu_programs_search, menu)
        //topmenu = menu
        var reportBt = menu.findItem(R.id.programReportsBt)
        val search: MenuItem = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView

        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = getString(R.string.search_title)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //reportBt.isVisible=false
                filter(newText)
                return true
            }
        })
        searchView.setOnCloseListener {
            reportBt.isVisible = true
            Log.i(TAG, "searchview onclose")
            false
        }
        searchView.setOnSearchClickListener {
            Log.i(TAG, "search clicked")
            reportBt.isVisible = false
        }

        reportBt.actionView.setOnClickListener {
            Log.i(TAG, "report bt clicked")

        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Filter programs from searchview
     *
     * @param text
     */
    private fun filter(text: String?) {
        var filteredlist: ArrayList<ProgramAdmin> = ArrayList()
        val length = text?.length ?: 0
        if (length > 0) {
            viewModel.programs.value?.let {
                for (item in it) {
                    if (item.title.lowercase().contains(text?.lowercase()!!)) {
                        filteredlist.add(item)
                    }
                }
            }

        } else {
            filteredlist = viewModel.programs.value as ArrayList<ProgramAdmin>
        }

        viewModel.filteredPrograms.value = filteredlist
    }

}
