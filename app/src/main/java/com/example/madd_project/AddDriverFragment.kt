package com.example.madd_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentAddDriverBinding

import com.example.madd_project.models.Drivers
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDriverFragment : Fragment() {

    private var _binding : FragmentAddDriverBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddDriverBinding.inflate(inflater, container,false)

        firebaseRef = FirebaseDatabase.getInstance().getReference("Driver")

        binding.driverAdd.setOnClickListener{
            saveData()
            findNavController().navigate(R.id.action_addDriverFragment_to_driverHomeFragment )
        }

        return binding.root
    }

    private fun saveData() {
        val name = binding.name.text.toString()
        val username = binding.username.text.toString()
        val email = binding.email.text.toString()
        val phone = binding.phone.text.toString()
        val vehiclenumber = binding.vehiclenumber.text.toString()
        val password = binding.password.text.toString()

        if(name.isEmpty()) binding.name.error = "Add a Name"
        if(username.isEmpty()) binding.username.error = "Add a UserName"
        if(email.isEmpty()) binding.email.error = "Add a Email"
        if(phone.isEmpty()) binding.phone.error = "Add a Phone"
        if(vehiclenumber.isEmpty()) binding.vehiclenumber.error = "Add a VehicleNumber"
        if(password.isEmpty()) binding.password.error = "Add a Password"

        val driverId = firebaseRef.push().key!!
        val drivers = Drivers(driverId,name,email,phone,vehiclenumber,password )

        firebaseRef.child(driverId).setValue(drivers)
            .addOnCompleteListener {
                Toast.makeText( context,"Data Stored Successfully",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText( context,"Error ${it.message}",Toast.LENGTH_SHORT).show()
            }
    }

}