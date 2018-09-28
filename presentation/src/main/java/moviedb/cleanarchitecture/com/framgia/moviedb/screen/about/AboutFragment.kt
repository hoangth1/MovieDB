package moviedb.cleanarchitecture.com.framgia.moviedb.screen.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite.FavoriteFragment

class AboutFragment : Fragment() {
    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}