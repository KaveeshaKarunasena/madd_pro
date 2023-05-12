package com.example.madd_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentUserDonatesBinding
import com.example.madd_project.databinding.FragmentViewPostBinding
import com.example.madd_project.models.Donate
import com.example.madd_project.models.Posts
import com.example.madd_project.utils.adapter.DonateAdapter
import com.example.madd_project.utils.adapter.FoodAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserDonates : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var donateList: ArrayList<Donate>
    private lateinit var donateAdapter: DonateAdapter

    private lateinit var binding: FragmentUserDonatesBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var uid : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        binding = FragmentUserDonatesBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.donateRecycler
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        donateList = ArrayList()

        dbRef = FirebaseDatabase.getInstance().getReference("Donate")

        dbRef.child(uid).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (donateSnapshot in snapshot.children) {
                        val donate = donateSnapshot.getValue(Donate::class.java)
                        donateList.add(donate!!)


                    }
                    donateAdapter = DonateAdapter(donateList)
                    recyclerView.adapter = donateAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

//        donateList.add()


        return binding.root


    }

}