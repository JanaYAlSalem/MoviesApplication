package com.example.moviesapplication

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.moviesapplication.network.ResultsItem
import com.example.moviesapplication.overview.OverviewFragment
import com.example.moviesapplication.overview.PhotoGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(ImageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val photoUri = imageUrl.toUri().buildUpon().build()
        Glide.with(ImageView)
            .load("https://image.tmdb.org/t/p/w500${photoUri}")
            .into(ImageView)
//            placeholder(R.drawable.loading_img)
//            error(R.drawable.ic_broken_image)
    }}


@BindingAdapter("textset")
fun bindText(textView: TextView, name : String?) {
    textView.setText(name)
}

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ResultsItem?>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}
//@BindingAdapter("Oncl")
//fun click(){
////    var action = OverviewFragment.actionoverviewFragmenttodetailsMovieFragment
//    holder.view.findNavController().navigate(action)
//}