package com.example.madd_project.utils.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.Navigation.findNavController
import com.example.madd_project.DriverHomeFragmentDirections
import com.example.madd_project.databinding.RvDriversItemsBinding
import com.example.madd_project.models.Drivers
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase


class RvDriverAdapter(private val driverList : java.util.ArrayList<Drivers>) : RecyclerView.Adapter<RvDriverAdapter.ViewHolder>(){

    class ViewHolder(val binding: RvDriversItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvDriversItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return driverList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = driverList[position]
        holder.apply{
            binding.apply{
                drNameItem.text = currentItem.name
                drUserNameItem.text = currentItem.username
                drEmailItem.text = currentItem.email
                drPhoneItem.text = currentItem.phone
                drVhNum.text = currentItem.vehiclenumber
                drId.text = currentItem.id
                rvContainer.setOnClickListener{

                    val action = DriverHomeFragmentDirections.actionDriverHomeFragmentToUpdateFragment(
                        currentItem.id.toString(),
                        currentItem.name.toString(),
                        currentItem.username.toString(),
                        currentItem.email.toString(),
                        currentItem.phone.toString(),
                        currentItem.vehiclenumber.toString(),
                        currentItem.dpassword.toString()
                    )
                    findNavController(holder.itemView).navigate(action)
                }

                rvContainer.setOnLongClickListener{
                    MaterialAlertDialogBuilder(holder.itemView.context)
                        .setTitle("Delete driver permanently")
                        .setMessage("Are you sure you want to delete this driver?")
                        .setPositiveButton("Yes"){_,_->
                            val firebaaseRef = FirebaseDatabase.getInstance().getReference("Driver")
                            firebaaseRef.child(currentItem.id.toString()).removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(holder.itemView.context,"Driver removed successfully",Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener{error->
                                    Toast.makeText(holder.itemView.context,"error ${error.message}",Toast.LENGTH_SHORT).show()
                                }
                        }
                        .setNegativeButton("No"){_,_->
                            Toast.makeText(holder.itemView.context,"Cancelled",Toast.LENGTH_SHORT).show()
                        }
                        .show()

                    return@setOnLongClickListener true
                }
            }
        }
    }




}