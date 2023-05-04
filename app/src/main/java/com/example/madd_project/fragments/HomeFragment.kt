package com.example.madd_project.fragments

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentHomeBinding
import com.example.madd_project.databinding.FragmentProfileBinding
import com.example.madd_project.databinding.FragmentViewPostBinding
import com.example.madd_project.utils.adapter.FoodAdapter


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var foodList : ArrayList<Food>
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container, false)

        recyclerView = binding.postRecycler
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(activity)

        foodList = ArrayList()

        foodAdapter = FoodAdapter() // need to pass array from the db

        recyclerView.adapter = foodAdapter

        foodAdapter.onItemClick = {
            val intent = Intent(activity,FragmentViewPostBinding::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }

        return binding.root

    }


}