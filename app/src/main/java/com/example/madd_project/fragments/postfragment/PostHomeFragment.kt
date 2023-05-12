//package com.example.madd_project.fragments.postfragment
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//
//import com.example.madd_project.R
//import com.example.madd_project.databinding.FragmentHomeBinding
//import com.example.madd_project.models.Posts
//import com.example.madd_project.utils.adapter.RvPostAdapter
//import com.google.firebase.database.*
//
//class PostHomeFragment : Fragment() {
//    private var _binding:FragmentHomeBinding?=null
//    private val binding get() = _binding!!
//    private lateinit var postList: java.util.ArrayList<Posts>
//    private lateinit var firebaseRef: DatabaseReference
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentHomeBinding.inflate(inflater,container,false)
//
//        firebaseRef = FirebaseDatabase.getInstance().getReference("posts")
//        postList = arrayListOf()
//
//        fetchData()
//        binding.postRecycler.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(this.context)
//        }
//        return binding.root
//    }
//
//    private fun fetchData() {
//        firebaseRef.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                    postList.clear()
//                    if(snapshot.exists()){
//                        for (postSnap in snapshot.children){
//                            val posts = postSnap.getValue(Posts::class.java)
//                            postList.add(posts!!)
//                        }
//                    }
//                    val rvAdapter = RvPostAdapter(postList)
//                    binding.postRecycler.adapter = rvAdapter
//            }
//
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context,"error : $error",Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }
//
//}