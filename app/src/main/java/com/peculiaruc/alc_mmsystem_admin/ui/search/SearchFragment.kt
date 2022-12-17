package com.peculiaruc.alc_mmsystem_admin.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentSearchBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Certificate
import com.peculiaruc.alc_mmsystem_admin.domain.models.Program
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report
import com.peculiaruc.alc_mmsystem_admin.domain.models.Task
import com.peculiaruc.alc_mmsystem_admin.type.ProgramProgress
import com.peculiaruc.alc_mmsystem_admin.type.TaskStatus
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * this represent Search screen..
 */
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    private val navController by lazy {
        NavHostFragment.findNavController(this)
    }

    private val list = listOf(
        Task("Retrofit library article write", "3 days from now", TaskStatus.ASSIGN),
        Program("Google Africa Scholarship Program", "", ProgramProgress.ADD),
        Certificate("", "GADS CLOUD 2022 CERTIFICATE"),
        Task("Room library article write", "3 days from now", TaskStatus.ASSIGN),
        Report(
            "Google Africa Scholarship Report", "By Marawan Kabir ",
            "19th - 25th Oct 22"
        ),
        Certificate("", "GADS ANDROID 2022 CERTIFICATE"),
        Task("Dagger library article write", "5 days from now", TaskStatus.COMPLETED),
        Program("Google America Scholarship Program", "", ProgramProgress.CHECK),
        Task("hilt library article write", "4 days from now", TaskStatus.ASSIGN),
        Program("Google Asia Scholarship Program", "", ProgramProgress.DOUBLE_CHECK),
        Certificate("", "GADS Web 2022 CERTIFICATE"),
        Report(
            "Google Asia Scholarship Report", "By Ibrahim Kabir ",
            "14th - 26th Oct 22"
        ),
        Certificate("", "GADS React 2022 CERTIFICATE"),
        Certificate("", "GADS Angular 2022 CERTIFICATE"),
        Task("Angular library article write", "2 days from now", TaskStatus.ASSIGNED),
        Report(
            "Google America Scholarship Report", "By Muhammad Kabir ",
            "19th - 25th Oct 22"
        ),
        Program("Google Scholarship Program", "", ProgramProgress.CHECK),
        Report(
            "Google Scholarship Report", "By Nada Kabir ",
            "11th - 15th Oct 22"
        ),
        Certificate("", "GADS TF 2022 CERTIFICATE"),
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        binding.recyclerViewSearch.adapter = SearchAdapter(list)
        binding.appBarLayout.elevation = 0f

        binding.topAppBar.setNavigationOnClickListener {
            navController.navigateUp()
        }
    }
}