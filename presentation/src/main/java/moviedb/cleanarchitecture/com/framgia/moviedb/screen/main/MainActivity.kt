package moviedb.cleanarchitecture.com.framgia.moviedb.screen.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseActivity
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.about.AboutFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite.FavoriteFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.home.HomeFragment

class MainActivity() : BaseActivity<MainViewModel>(), BottomNavigationView.OnNavigationItemSelectedListener {


    override val viewModel: MainViewModel = MainViewModel()
    override fun initComponent(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        replaceFragment(HomeFragment(), R.id.child_container, "", false)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_home -> replaceFragment(HomeFragment.newInstance(), R.id.child_container, "", false)
            R.id.item_favorite -> replaceFragment(FavoriteFragment.newInstance(), R.id.child_container, "", false)
            R.id.item_about -> replaceFragment(AboutFragment.newInstance(), R.id.child_container, "", false)
        }
        return true
    }
}
