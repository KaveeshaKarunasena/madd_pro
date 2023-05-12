package com.example.madd_project.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.PostHomeFragmentDirections


import com.example.madd_project.databinding.RvPostsBinding
import com.example.madd_project.models.Posts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class RvPostAdapter(private val postList: java.util.ArrayList<Posts>) : RecyclerView.Adapter< RvPostAdapter.ViewHolder>(){
    class ViewHolder(val binding: RvPostsBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(RvPostsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = postList[position]
        holder.apply {
            binding.apply {
                tvNameItem.text = currentItem.name
                tvDueDate.text = currentItem.dueDate
                tvdescription.text = currentItem.description
                tvQuantity.text = currentItem.quantity

                rvDesc.setOnClickListener{
                    val action = PostHomeFragmentDirections.actionHomeFragmentToUpdateFragment(
                        currentItem.id.toString(),
                        currentItem.name.toString(),
                        currentItem.dueDate.toString(),
                        currentItem.description.toString(),
                        currentItem.quantity.toString(),
                        currentItem.imageUrl.toString()
                    )
                    findNavController(holder.itemView).navigate(action)
                }
                deletebtn.setOnClickListener{
                    MaterialAlertDialogBuilder(holder.itemView.context)
                        .setTitle("post permanently")
                        .setMessage("Are you sure you want to delete this item")
                        .setPositiveButton("Yes"){
                                _,_-> val firebaseRef = FirebaseDatabase.getInstance().getReference("Posts")
                            val storageRef = FirebaseStorage.getInstance().getReference("Images")
                            storageRef.child(currentItem.id.toString()).delete()

                            currentItem.id?.let { it ->
                                println("id : $it")
                                firebaseRef.child(it).removeValue()
                                    .addOnSuccessListener {
                                        Toast.makeText(holder.itemView.context,"Item removed successfully",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener{error ->
                                        Toast.makeText(holder.itemView.context,"error ${error.message}",
                                            Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                        .setNegativeButton("no"){
                                _,_-> Toast.makeText(holder.itemView.context,"cancelled", Toast.LENGTH_SHORT).show()
                        }
                        .show()
                }
            }
        }
    }
}