package com.example.moviesapplication.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.R
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.databinding.GridViewItemBinding
import com.example.moviesapplication.network.ResultsItem

class PhotoGridAdapter : androidx.recyclerview.widget.ListAdapter<ResultsItem, PhotoGridAdapter.ResultsItemViewHolder>(DiffCallback) {


    class ResultsItemViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(ItemOfMovies : ResultsItem ) { // FlagPhoto(name: String,flag: String
            binding.item = ItemOfMovies
            binding.executePendingBindings()
        }
        var itemOfMovie = binding.MovieItem
    } // end FlagPhotoViewHolder



    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsItemViewHolder {
        return ResultsItemViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun onBindViewHolder(holder: ResultsItemViewHolder, position: Int) {
        val listMovie = getItem(position)

        holder.bind(listMovie)
        holder.itemOfMovie.setOnClickListener {
            var action = OverviewFragmentDirections.actionOverviewFragmentToDetailsMovieFragment(id = position)
            holder.itemOfMovie.findNavController().navigate(action)


        }
    }

} // end PhotoGridAdapter