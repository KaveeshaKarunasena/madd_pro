package com.example.madd_project.utils.adapter


import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madd_project.R
import com.example.madd_project.models.Posts

class FoodAdapter (private val foodList:ArrayList<Posts>):RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){

    var onItemClick: ((Posts) -> Unit)? =  null

    class FoodViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val imageView : ImageView = itemView.findViewById(R.id.imagePost)
        val textView : TextView = itemView.findViewById(R.id.textPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.imageView.setImageResource(food.imageUrl)
        holder.textView.text=food.name

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(food)
        }
    }
}