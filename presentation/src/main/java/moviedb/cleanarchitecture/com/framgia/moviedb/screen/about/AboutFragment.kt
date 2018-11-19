package moviedb.cleanarchitecture.com.framgia.moviedb.screen.about

import android.app.ActionBar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about.*
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite.FavoriteFragment

class AboutFragment : Fragment() {
    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        val searView:SearchView=view.findViewById(R.id.search)
        searView?.layoutParams = ActionBar.LayoutParams(Gravity.RIGHT)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
