package com.example.appgallery_raphael.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appgallery_raphael.R
import com.example.appgallery_raphael.model.Gallery
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class GalleryAdapter (
    private val itemClick: (item: Gallery) -> Unit
): RecyclerView.Adapter<GalleryAdapter.CatsViewHolder>() {

    private var listCats = emptyList<Gallery>()

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

    fun setCharacters(cats: List<Gallery>){
        listCats = cats
        notifyDataSetChanged()
    }

    class CatsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image_character = itemView.cats_img
        var img_type = itemView.catsTypeImg
        var followers_number = itemView.followersGallery
        var name_cats = itemView.nameCats

        fun bind(gallery: Gallery){
            if (gallery.images?.isNotEmpty() == true) {
                gallery.images?.first()?.link?.let {
                    Picasso.get().load(it).into(image_character)
                }
            }
            followers_number.text= gallery.followers.toString()
            name_cats.text= gallery.title
        }
    }

}