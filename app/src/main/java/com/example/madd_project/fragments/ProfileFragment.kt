package com.example.madd_project.fragments

import android.location.Address
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentProfileBinding
import com.example.madd_project.utils.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    lateinit var uid :String
    lateinit var user :User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container, false)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
//         userEmail = auth.currentUser?.

//         uid="-NUVd8RIOfSkvvrERD4n"
//       println("user : $userEmail")
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        if(uid.isNotEmpty()){
            getUserData()
        }

        binding.submitBtn.setOnClickListener{
            val userName = binding.nameText3.text.toString()
            val userAddress = binding.addressText3.text.toString()
            val userEmail = binding.emailText3.text.toString()
            val userContactNo = binding.contactText3.text.toString()

            updateData(userName,userAddress,userEmail,userContactNo)
        }
        return binding.root


    }

    private fun updateData(userName: String, userAddress: String, userEmail: String, userContactNo: String) {
        val userHash : HashMap<String,String> = HashMap<String, String> ()

        userHash["fullName"] = userName
        userHash["userEmail"] = userEmail
        userHash["userAddress"] = userAddress
        userHash["userContactNo"] = userContactNo

        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.child(uid).updateChildren(userHash as Map<String, Any>).addOnCompleteListener {

        }.addOnFailureListener { err ->
            print(err)
        }
        }



    private fun getUserData() {
        println("other msg:  $uid")
        dbRef.child(uid).addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
//                println("other msg ${user.userId}")
                binding.proCardView.setText( user.fullName)
                binding.emailText3.setText(user.userEmail)
                binding.nameText3.setText(user.fullName)
                binding.contactText3.setText(user.userContactNo)
                binding.addressText3.setText(user.userAddress)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}