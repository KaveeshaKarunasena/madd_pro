package com.example.madd_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentUserDonatesBinding
import com.example.madd_project.databinding.FragmentViewPostBinding
import com.example.madd_project.models.Donate
import com.example.madd_project.utils.adapter.DonateAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class UserDonates : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var donateList:ArrayList<Donate>
    private lateinit var donateAdapter: DonateAdapter

    private lateinit var binding : FragmentUserDonatesBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()

        binding = FragmentUserDonatesBinding.inflate(layoutInflater,container, false)

        recyclerView = binding.donateRecycler
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        donateList = ArrayList()

//        donateList.add()

        donateAdapter =  DonateAdapter(donateList)
        recyclerView.adapter = donateAdapter

        return binding.root
    }

}