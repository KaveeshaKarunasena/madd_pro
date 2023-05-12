package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.rvPostAdapter
import com.example.models.Posts
import com.example.newapp.R
import com.example.newapp.databinding.FragmentHomeBinding
import com.google.firebase.database.*

class PostHomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var postList: java.util.ArrayList<Posts>
    private lateinit var firebaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        binding.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_createFragment)
        }
        firebaseRef = FirebaseDatabase.getInstance().getReference("Posts")
        postList = arrayListOf()

        fetchData()
        binding.postrecyclerview.apply{
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this.context)
        }
        return binding.root
    }

    private fun fetchData() {
        firebaseRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                postList.clear()
                if (snapshot.exists()) {
                    for (postSnap in snapshot.children) {
                        val posts = postSnap.getValue(Posts::class.java)
                        postList.add(posts!!)
                    }
                }
                val rvAdapter = rvPostAdapter(postList)
                binding.postrecyclerview.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"Error : $error",Toast.LENGTH_SHORT).show()
            }

        })
    }


}