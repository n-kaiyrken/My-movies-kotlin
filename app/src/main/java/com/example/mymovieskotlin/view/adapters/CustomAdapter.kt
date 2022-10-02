package com.example.mymovieskotlin.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieskotlin.data.Result
import com.example.mymovieskotlin.databinding.ItemViewBinding
import com.squareup.picasso.Picasso

class CustomAdapter(private val moviesList: List<Result>?, val mItemClickListener: ItemClickListenter
): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    interface ItemClickListenter{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movie = moviesList?.get(position)

        //Picasso.get().load("https://image.tmdb.org/t/p/w500" + moviesList?.get(position)?.poster_path).into(holder.binding.imageView);
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie?.poster_path}").into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return moviesList?.size!!
    }

    inner class CustomViewHolder(val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                moviesList?.get(position)?.id?.let { it1 -> mItemClickListener.onItemClick(it1) }
            }
        }
    }
}