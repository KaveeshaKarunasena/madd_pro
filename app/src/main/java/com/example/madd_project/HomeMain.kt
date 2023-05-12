package com.example.madd_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.madd_project.databinding.FragmentHomeMainBinding
import com.google.firebase.database.*


class HomeMain : Fragment() {

    private var _binding: FragmentHomeMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        val view = binding.root

        val tableLayout = binding.tableLayout
        firebaseRef = FirebaseDatabase.getInstance().getReference("Driver")

        firebaseRef.child("Driver").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (data in dataSnapshot.children) {
                    val childName = data.key
                    val childCount = data.childrenCount

                    // Create a new table row
                    val newRow = TableRow(requireContext())

                    // Create a TextView for the child name
                    val childNameTextView = TextView(requireContext())
                    childNameTextView.text = childName
                    childNameTextView.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    newRow.addView(childNameTextView)

                    // Create a TextView for the child count
                    val childCountTextView = TextView(requireContext())
                    childCountTextView.text = childCount.toString()
                    childCountTextView.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    newRow.addView(childCountTextView)

                    // Add the new row to the table layout
                    tableLayout.addView(newRow)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled event here if needed
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


