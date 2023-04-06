package com.example.appgallery_raphael.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appgallery_raphael.R
import com.example.appgallery_raphael.model.Cats
import com.example.appgallery_raphael.presentation.ui.home.GalleryHomeFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class CatsAdapter (
    private val itemClick: (item: Cats) -> Unit
): RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    private var listCats = emptyList<Cats>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(listCats[position])

        holder.itemView.setOnClickListener { _ ->
            itemClick(listCats[position])
        }
    }

    override fun getItemCount(): Int {
        return listCats.size
    }

    fun setCharacters(cats: List<Cats>){
        listCats = cats
        notifyDataSetChanged()
    }

    class CatsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image_character = itemView.cats_img
        var img_type = itemView.catsTypeImg
        var followers_number = itemView.followersCats
        var name_cats = itemView.nameCats

        fun bind(cats: Cats){
            if (cats.images?.isNotEmpty() == true) {
                cats.images?.first()?.link?.let {
                    Picasso.get().load(it).into(image_character)
                }
            }
            followers_number.text= cats.followers.toString()
            name_cats.text= cats.title
        }
    }

}