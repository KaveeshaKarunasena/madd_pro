package com.example.madd_project.fragments

import android.content.Intent
import android.location.Address
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentProfileBinding
import com.example.madd_project.utils.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.HashMap


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    private lateinit var database : FirebaseDatabase
    private lateinit var storage : FirebaseStorage
    private var selectedImg : Uri? = null
    lateinit var uid :String
    lateinit var user :User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container, false)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        uid = auth.currentUser?.uid.toString()
//         userEmail = auth.currentUser?.

//         uid="-NUVd8RIOfSkvvrERD4n"
//       println("user : $userEmail")
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        if(uid.isNotEmpty()){
            getUserData()
        }

        binding.proPic.setOnClickListener{
            resultLauncher.launch("image/*")
//            val intent = Intent()
//            intent.action =  Intent.ACTION_GET_CONTENT
//            intent.type="image/*"
//            startActivityForResult(intent,1)
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

    private val resultLauncher = registerForActivityResult(

        ActivityResultContracts.GetContent()){
            selectedImg = it
        binding.proPic.setImageURI(it)

    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(data != null){
//            println("data : $data")
//
//            if(data.data != null){
//                selectedImg = data.data!!
//
//                binding.proPic.setImageURI(selectedImg)
//            }
//        }
//    }

    private fun updateData(userName: String, userAddress: String, userEmail: String, userContactNo: String) {
        val userHash : HashMap<String,String> = HashMap<String, String> ()

        userHash["fullName"] = userName
        userHash["userEmail"] = userEmail
        userHash["userAddress"] = userAddress
        userHash["userContactNo"] = userContactNo


        val reference = storage.reference.child("Profile").child(Date().time.toString())
        selectedImg?.let {
            reference.putFile(it).addOnCompleteListener{

                if(it.isSuccessful){
                    reference.downloadUrl.addOnSuccessListener {task->
                        val userPic : HashMap<String,String> = HashMap<String, String> ()
                        val tsk = task.toString()
                        println("URL :  $tsk")
                        userPic["Url"] = task.toString()

                        dbRef = FirebaseDatabase.getInstance().getReference("Users")
                        dbRef.child(uid).updateChildren(userPic as Map<String, Any>)
                        Toast.makeText(activity,"profile pic  updated",Toast.LENGTH_SHORT).show()
                    }
                }
            }.addOnFailureListener { err ->
                Toast.makeText(activity,"profile pic not updated",Toast.LENGTH_SHORT).show()
            }
        }

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
                Picasso.get().load(user.Url).into(binding.proPic)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


}