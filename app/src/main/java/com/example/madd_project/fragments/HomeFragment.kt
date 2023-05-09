package com.example.madd_project.fragments

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madd_project.LoadingAlert
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentHomeBinding
import com.example.madd_project.databinding.FragmentProfileBinding
import com.example.madd_project.databinding.FragmentViewPostBinding
import com.example.madd_project.models.Posts
import com.example.madd_project.utils.adapter.FoodAdapter
import com.google.firebase.database.*


class HomeFragment : Fragment() {

    private lateinit var dbRef :  DatabaseReference
    private lateinit var binding : FragmentHomeBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var foodList : ArrayList<Posts>
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container, false)

        val loading = activity?.let { LoadingAlert(it) }
        loading?.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading?.isDismiss()
            }

        },5000)

        recyclerView = binding.postRecycler
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(activity)

        foodList = ArrayList()
        dbRef = FirebaseDatabase.getInstance().getReference("Posts")

        dbRef.addValueEventListener(object:ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(postSnapshot in snapshot.children){
                        val post = postSnapshot.getValue(Posts::class.java)
                        foodList.add(post!!)


                    }
                    foodAdapter = FoodAdapter(foodList)

                    recyclerView.adapter = foodAdapter

                    foodAdapter.onItemClick = {
//                        val intent = Intent(activity,FragmentViewPostBinding::class.java)
//                        intent.putExtra("food",it)
//                        startActivity(intent)a\

                        val bundle = Bundle()
                        bundle.putParcelable("food",it)
//                        findNavController().navigate(,)
                        val fragment = ViewPost()

                        fragment.arguments = bundle
                        fragmentManager?.beginTransaction()?.replace(R.id.container,fragment)?.commit()


                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })




        return binding.root

    }

//    private fun getPostData() {
//        dbRef = FirebaseDatabase.getInstance().getReference("Posts")
//
//        dbRef.addValueEventListener(object:ValueEventListener{
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()){
//                    for(postSnapshot in snapshot.children){
//                        val post = postSnapshot.getValue(Posts::class.java)
//                        foodList.add(post!!)
//
//                    }
//                    foodAdapter = FoodAdapter(foodList)
//                    recyclerView.adapter = foodAdapter
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//    }


}