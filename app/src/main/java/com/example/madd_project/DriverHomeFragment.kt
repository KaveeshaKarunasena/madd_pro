package com.example.madd_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madd_project.utils.adapter.RvDriverAdapter
import com.example.madd_project.databinding.FragmentDriverHomeBinding
import com.example.madd_project.models.Drivers
import com.google.firebase.database.*

class DriverHomeFragment : Fragment() {

    private var _binding : FragmentDriverHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var driverlist : java.util.ArrayList<Drivers>
    private lateinit var firebaseRef :DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDriverHomeBinding.inflate(inflater, container,false)

        binding.newDriver.setOnClickListener{
            findNavController().navigate(R.id.action_driverHomeFragment_to_addDriverFragment)
        }

        firebaseRef = FirebaseDatabase.getInstance().getReference("Driver")
        driverlist = arrayListOf()

        fetchData()

        binding.rvDrivers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }

        return binding.root
    }

    private fun fetchData() {
        firebaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                driverlist.clear()
                if(snapshot.exists()){
                    for (driverSnap in snapshot.children){
                        val drivers = driverSnap.getValue(Drivers::class.java)
                        driverlist.add(drivers!!)
                    }
                }
                val rvAdapter = RvDriverAdapter(driverlist)
                binding.rvDrivers.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"error: $error ",Toast.LENGTH_SHORT).show()
            }

        })
    }

}