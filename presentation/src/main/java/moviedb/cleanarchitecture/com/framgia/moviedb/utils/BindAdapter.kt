package moviedb.cleanarchitecture.com.framgia.moviedb.utils

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

object BindAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, image: String) {
        Glide.with(imageView.context).load(image).into(imageView)
    }
}
