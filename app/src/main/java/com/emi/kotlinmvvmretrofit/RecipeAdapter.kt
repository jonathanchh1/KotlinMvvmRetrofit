package com.emi.kotlinmvvmretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.content_items.view.*


class RecipeAdapter(private val context : Context) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

   private var recipeList = emptyList<Recipe>()
   private var Inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
     val attachView = Inflater.inflate(R.layout.content_items, parent, false)
        return RecipeViewHolder(attachView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val current = recipeList[position]
        holder.itemView.title.text = current.title
        holder.itemView.href.text = current.href
        holder.itemView.ingredient.text = current.desc
        Glide.with(context)
            .load(current.thumbnail)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.thumbnail)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    internal fun updateAdapter(recipe : List<Recipe>){
        this.recipeList = recipe
        notifyDataSetChanged()

    }


    inner class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}