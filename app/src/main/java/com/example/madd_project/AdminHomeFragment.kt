package com.example.madd_project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentAdminhomeBinding

class AdminHomeFragment : Fragment() {

    private var _binding : FragmentAdminhomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdminhomeBinding.inflate(inflater,container,false)

        binding.cardHome.setOnClickListener{
            findNavController().navigate(R.id.action_adminhomeFragment_to_homeMain)
        }

        binding.cardDriver.setOnClickListener{
            findNavController().navigate(R.id.action_adminhomeFragmentt_to_driverHomeFragment)

        }

        return binding.root
    }

}