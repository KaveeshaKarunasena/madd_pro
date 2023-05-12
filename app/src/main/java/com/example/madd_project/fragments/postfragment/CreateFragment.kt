package com.example.fragments

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.models.Posts
import com.example.newapp.R
import com.example.newapp.databinding.FragmentCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.time.format.DateTimeParseException

class CreateFragment : Fragment() {
    private var _binding: FragmentCreateBinding?=null
    private val binding get() = _binding!!
    private lateinit var firebaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    private var uri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       _binding = FragmentCreateBinding.inflate(inflater,container,false)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_createFragment_to_homeFragment)
        }
        firebaseRef = FirebaseDatabase.getInstance().getReference("Posts")
       storageRef = FirebaseStorage.getInstance().getReference("Images")
        binding.CreatePost.setOnClickListener{
            saveData()
        }
        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.imageView2Create.setImageURI(it)
            if(it!=null){
                uri = it
            }
        }
        binding.buttonAddImages.setOnClickListener{
            pickImage.launch("image/*")
        }


        return binding.root
    }

    private fun saveData() {
        val name = binding.Name.text.toString()
        val dueDate = binding.dueDate.text.toString()
        val description = binding.Description.text.toString()
        val quantity = binding.quantity.text.toString()
        val imageUrl = binding.buttonAddImages.text.toString()

        if (name.isEmpty()) binding.Name.error = "Enter a Text"
        if (dueDate.isEmpty()) binding.dueDate.error = "Enter Due Date"
        if (!isValidate(dueDate)) binding.dueDate.error = "Enter the date correctly"
        if (description.isEmpty()) binding.Description.error = "Enter the description"
        if (quantity.isEmpty()) binding.quantity.error = "Enter the Quantity"
        if (imageUrl.isEmpty()) binding.buttonAddImages.error = "Add the Image"

        val id = firebaseRef.push().key!!


        uri?.let {
            storageRef.child(id).putFile(it).addOnSuccessListener { task ->
                task.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { url ->
                        Toast.makeText(
                            context,
                            "image has been saved successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        val imgUrl = url.toString()
                        firebaseRef.child(id)
                            .setValue(Posts(id, name, dueDate, description, quantity, imgUrl))
                            .addOnCompleteListener {
                                Toast.makeText(
                                    context,
                                    "Post has been saved successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
            }
        }


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun isValidate(date:String):Boolean{
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        dateFormat.isLenient = false
        try {
            dateFormat.parse(date)
            return true;
        }catch (e: DateTimeParseException){
            return false;
        }

    }

}