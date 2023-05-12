package com.example.madd_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fragments.update_post_fragmentArgs
import com.example.madd_project.models.Posts
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentUpdatePostFragmentBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 * Use the [update_post_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class update_post_fragment : Fragment() {
    private var _binding: FragmentUpdatePostFragmentBinding?=null
    private val binding get() = _binding!!

    private val args : update_post_fragmentArgs by navArgs()

    private lateinit var firebaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdatePostFragmentBinding.inflate(inflater,container,false)
//        binding.floatingActionButton.setOnClickListener{
//            findNavController().navigate(R.id.action_update_Fragment_to_homeFragment)
//        }
        firebaseRef = FirebaseDatabase.getInstance().getReference("Posts")

        binding.apply {
            name.setText(args.name)
            dueDate.setText(args.dueDate)
            description.setText(args.description)
            quantity.setText(args.quantity)


            updatePost.setOnClickListener{
                updateData()
                findNavController().navigate(R.id.action_update_Fragment_to_homeFragment)
            }
        }
        return binding.root
    }

    private fun updateData() {
        val name = binding.name.text.toString()
        val duedate = binding.dueDate.text.toString()
        val description = binding.description.text.toString()
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

