package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
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
    override val viewModel = ProgramsViewModel.getInstance()

    private lateinit var programsAdapter: ProgramsAdapter
    private var newProgramActionArg = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTitle(false)

        viewModel.initPrograms()

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
        hideBottomBarWhenNavigating()

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

        binding.chipAll.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                activateAllChip()
            }
        }

        binding.chipActive.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                activateActiveChip()
            }
        }

        binding.chipCompleted.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                activateCompletedChip()
            }
        }


        binding.addProgramFab.setOnClickListener {
            val action = ProgramsFragmentDirections.actionProgramsFragmentToNewProgramFragment(
                newProgramActionArg
            )
            view.findNavController().navigate(action)
        }
    }

    /**
     * activate chipAll
     */
    fun activateAllChip() {
        viewModel.filteredPrograms.value = viewModel.programsAllList
        binding.chipAll.isSelected = true
        binding.chipActive.isSelected = false
        binding.chipCompleted.isSelected = false
        binding.chipActive.isChecked = false
        binding.chipCompleted.isChecked = false

    }

    /**
     * activate chipActive
     */
    fun activateActiveChip() {
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
    fun activateCompletedChip() {
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
        inflater.inflate(R.menu.menu_programs_search, menu)
        val reportBt = menu.findItem(R.id.programReportsBt)
        val search: MenuItem = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView

        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = getString(R.string.search_title)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.programs.value?.let {
                    filter(newText, it)
                }
                return true
            }
        })
        searchView.setOnCloseListener {
            reportBt.isVisible = true
            false
        }
        searchView.setOnSearchClickListener {
            reportBt.isVisible = false
        }

        reportBt.actionView.setOnClickListener {

        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Filter programs from searchview
     *
     * @param text
     */
    fun filter(text: String?, list: List<ProgramAdmin>) {
        var filteredlist: ArrayList<ProgramAdmin> = ArrayList()
        val length = text?.length ?: 0
        if (length > 0) {

            for (item in list) {
                if (item.title.lowercase().contains(text?.lowercase()!!)) {
                    filteredlist.add(item)
                }

            }

        } else {
            filteredlist = viewModel.programs.value as ArrayList<ProgramAdmin>
        }

        viewModel.filteredPrograms.value = filteredlist
    }

    private fun hideBottomBarWhenNavigating() {
        val fragmentsArray = arrayOf(
            R.id.NewProgramFragment,
            R.id.programDetailsFragment,
            R.id.taskReportsFragment,
            R.id.TaskReportDetailFragment,
            R.id.reportsFragment,
            R.id.ReportDetailFragment,
            R.id.CriteriaMultiChoiceFragment,
            R.id.CriteriaMultipleInputsFragment,
            R.id.criteriaSingleInputFragment,
            R.id.setUpCriteriaFragment,
            R.id.criteriaYesNoInputFragment,
            R.id.criteriaFileInputFragment
        )
        view?.findNavController()
            ?.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, _: Bundle? ->
                val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
                navBar?.isVisible = nd.id !in fragmentsArray
            }
    }
}
