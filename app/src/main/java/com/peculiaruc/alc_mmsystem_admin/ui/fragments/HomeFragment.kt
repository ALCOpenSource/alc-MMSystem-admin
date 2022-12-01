package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentHomeBinding

/**
 * Fragment for home page
 */

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        binding.apply {
            bottomNavigation.setupWithNavController(navHostFragment.navController)
            toolbarMain.setupWithNavController(navHostFragment.navController)
            navHostFragment.navController.addOnDestinationChangedListener{
                    _, destination, _ ->
                toolbarMain.title = destination.label

                if (destination.id == R.id.chatFragment){
                    bottomNavigation.visibility = View.GONE
                    toolbarMain.visibility = View.GONE

                }else{
                    bottomNavigation.visibility = View.VISIBLE
                    toolbarMain.visibility = View.VISIBLE
                }
            }
        }
    }
}