package com.example.madd_project.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madd_project.R
import com.example.madd_project.models.Donate
import com.example.madd_project.models.Posts

class DonateAdapter(private val donateList:ArrayList<Donate>) :RecyclerView.Adapter<DonateAdapter.DonateViewHolder>() {

    class DonateViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val donateName : TextView = itemView.findViewById(R.id.donateName)
        val donateQty : TextView = itemView.findViewById(R.id.quaty)
        val submitDate : TextView =itemView.findViewById(R.id.textdate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.donate_item,parent,false)
        return DonateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return donateList.size
    }

    override fun onBindViewHolder(holder: DonateViewHolder, position: Int) {

        val donate = donateList[position]
//        holder.donateName.text =
        holder.donateQty.text = donate.donates
//        holder.submitDate.text = donate
    }

}