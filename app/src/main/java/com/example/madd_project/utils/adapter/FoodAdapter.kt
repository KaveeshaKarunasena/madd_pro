package com.example.madd_project.utils.adapter


import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madd_project.R
import com.example.madd_project.models.Posts
import com.squareup.picasso.Picasso

class FoodAdapter (private val foodList:ArrayList<Posts>):RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){

    var onItemClick: ((Posts) -> Unit)? =  null

    class FoodViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val imageView : ImageView = itemView.findViewById(R.id.imagePost)
        val textView : TextView = itemView.findViewById(R.id.textPost)
        val textId :TextView = itemView.findViewById(R.id.textId)

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
        Picasso.get().load(food.imageUrl).into(holder.imageView)
        holder.textView.text=food.name
        holder.textId.text = food.id

        holder.itemView.setOnClickListener{
             onItemClick?.invoke(food)
        }
    }
}