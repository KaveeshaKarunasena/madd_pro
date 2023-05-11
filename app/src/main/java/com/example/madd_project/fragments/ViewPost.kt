package com.example.madd_project.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.madd_project.LoadingAlert
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentViewPostBinding
import com.example.madd_project.models.Donate
import com.example.madd_project.models.Posts
import com.example.madd_project.utils.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class ViewPost : Fragment() {


    private lateinit var binding : FragmentViewPostBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    lateinit var user :User
    private lateinit var uid :String

    @SuppressLint("UnlocalizedSms")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        binding = FragmentViewPostBinding.inflate(layoutInflater,container, false)

        // val food = activity?.intent?.getParcelableExtra<Posts>("food")
        val loading = activity?.let { LoadingAlert(it) }
        loading?.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading?.isDismiss()
            }

        },6000)
        val food = this.arguments?.getParcelable<Posts>("food")


        if(food !=null){

            val textView = binding.textDetail
            val imageView = binding.imageView4
            val id  = food.id
//            val description = binding.textdescription
//            val quantity = binding.textQuantity
//            val date =  binding.textDueData


            textView.text = food.name
            Picasso.get().load(food.imageUrl).into(imageView)
//            description.text=food.description
//            quantity.text = food.quantity
//            date.text=food.dueDate
            if (id != null) {
                getPostData(id)
            }
        }

        binding.donateBtn.setOnClickListener {
            saveDonate()

            val transaction = fragmentManager?.beginTransaction()
            val fragmentHome = HomeFragment()
            transaction?.replace(R.id.container,fragmentHome)?.commit()

            val phoneNo = "+94766974709"
//            val smsIntent = Intent(Intent.ACTION_VIEW)
//            smsIntent.data = Uri.parse("sms:$phoneNo")
//            startActivity(smsIntent)

            val uri = Uri.parse("sms:$phoneNo")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "The SMS text")
            startActivity(intent)


        }
        checkPermissions()



        return binding.root
    }

    private fun checkPermissions(){
        if(activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.SEND_SMS) } != PackageManager.PERMISSION_GRANTED){
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.SEND_SMS),101) }
        }
    }

    private fun getPostData(id:String) {
        println("id:$id")
        dbRef = FirebaseDatabase.getInstance().getReference("Posts")
        dbRef.child(id).get().addOnSuccessListener {
            println("it: $it")
//            binding.textdescription.text = it.description
//                binding.textDueData.text = it.dueDate
//               binding.textQuantity.text = it.quantity
        }
//        addValueEventListener(object:ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//               val  post :Posts = snapshot.getValue(Posts::class.java)!!
////                println("other msg ${user.userId}")
//                binding.textdescription.text = post.description
//                binding.textDueData.text = post.dueDate
//                binding.textQuantity.text = post.quantity
//
//
//            }

//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
    }

    private fun saveDonate() {
        dbRef = FirebaseDatabase.getInstance().getReference("Posts")
        val donates = binding.editTextNumber.text.toString()

        println("userID:$uid")
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
//                println("other msg ${user.userId}")
                val fullName = user.fullName
                val userEmail = user.userEmail
                val contactNo = user.userContactNo
                val userAddress = user.userAddress

                val donate = Donate(fullName, userEmail, contactNo, userAddress, donates)
                dbRef = FirebaseDatabase.getInstance().getReference("Donate")
                dbRef.child(uid).setValue(donate).addOnCompleteListener {
                }.addOnFailureListener { err ->
                    print(err)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


}