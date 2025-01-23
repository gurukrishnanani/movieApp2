package com.example.photogalleryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photogalleryapp.R
import com.example.photogalleryapp.model.Photo

class PhotoAdapter(private val photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.titleTextView.text = photo.title

        // Load the image from the URL into the ImageView using Glide
        Glide.with(holder.itemView.context)
            .load(photo.thumbnailUrl) // You can also use photo.url if you prefer the full-size image
            .placeholder(R.drawable.image) // Placeholder for image loading
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}
