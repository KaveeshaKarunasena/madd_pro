package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.models.Posts
import com.example.newapp.R
import com.example.newapp.databinding.FragmentUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 * Use the [update_post_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class update_post_fragment : Fragment() {
    private var _binding: FragmentUpdateBinding?=null
    private val binding get() = _binding!!

    private val args : update_post_fragmentArgs by navArgs()

    private lateinit var firebaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_update_Fragment_to_homeFragment)
        }
        firebaseRef = FirebaseDatabase.getInstance().getReference("Posts")

        binding.apply {
            Name.setText(args.name)
            dueDate.setText(args.dueDate)
            Description.setText(args.description)
            quantity.setText(args.quantity)

            UpdatePost.setOnClickListener{
                updateData()
            }
        }
        return binding.root
    }

    private fun updateData() {
        val name = binding.Name.text.toString()
        val duedate = binding.dueDate.text.toString()
        val description = binding.Description.text.toString()
        val quantity = binding.quantity.text.toString()
        val Posts = Posts(args.id,name,duedate,description,quantity)
         firebaseRef.child(args.id).setValue(Posts)
             .addOnCompleteListener {
                 Toast.makeText(
                     context,
                     "Post has been updated successfully",
                     Toast.LENGTH_SHORT
                 ).show()
             }
             .addOnFailureListener {
                 Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT)
                     .show()
             }
    }


}

