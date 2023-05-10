package com.example.madd_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentUpdateBinding
import com.example.madd_project.models.Drivers
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateFragment : Fragment() {

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args : UpdateFragmentArgs by navArgs()

    private lateinit var firebaseRef : DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateBinding.inflate(inflater,container,false)

        firebaseRef = FirebaseDatabase.getInstance().getReference("Driver")

        binding.apply {
            updatename.setText(args.name)
            updateusername.setText(args.username)
            updateemail.setText(args.email)
            updatephone.setText(args.phone)
            updatevehiclenumber.setText(args.vehiclenumber)
            driverUpdate.setOnClickListener{
                updateData()
                findNavController().navigate(R.id.action_updateFragment_to_driverHomeFragment )
            }
        }

        return binding.root
    }

    private fun updateData() {
        val name = binding.updatename.text.toString()
        val username = binding.updateusername.text.toString()
        val phone = binding.updatephone.text.toString()
        val email = binding.updateemail.text.toString()
        val vehiclenumber = binding.updatevehiclenumber.text.toString()
        val drivers = Drivers(args.id,name,username,phone,email,vehiclenumber)

        firebaseRef.child(args.id).setValue(drivers)
            .addOnCompleteListener{
                Toast.makeText(context,"Updated Successfully",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(context,"error ${it.message}",Toast.LENGTH_SHORT).show()
            }

    }

}