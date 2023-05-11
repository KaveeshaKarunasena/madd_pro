package com.example.madd_project.fragments.postfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.madd_project.databinding.FragmentUpdatePostFragmentBinding
import com.example.madd_project.models.Posts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 * Use the [update_post_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class update_post_fragment : Fragment() {

    private var _binding:FragmentUpdatePostFragmentBinding?=null
    private val binding get() = _binding!!
    private val args: update_post_fragmentArgs by navArgs()

    private lateinit var firebaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdatePostFragmentBinding.inflate(inflater,container,false)
        firebaseRef = FirebaseDatabase.getInstance().getReference("posts")
        binding.apply {
            updateName.setText(args.name)
            updateDescription.setText(args.description)
            updatedueDate.setText(args.dueDate)
            updatequantity.setText(args.quantity)
            updatePost.setOnClickListener{
                updateData()
            }
        }

        return binding.root
    }

    private fun updateData() {
        val name = binding.updateName.text.toString()
        val descritpion = binding.updateDescription.text.toString()
        val dueDate = binding.updatedueDate.text.toString()
        val quantity = binding.updatequantity.text.toString()
        val posts = Posts(args.id,name,dueDate,descritpion,quantity)

        firebaseRef.child(args.id).setValue(posts)
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


