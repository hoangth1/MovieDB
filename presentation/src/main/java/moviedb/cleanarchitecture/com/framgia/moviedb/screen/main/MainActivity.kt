package moviedb.cleanarchitecture.com.framgia.moviedb.screen.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseActivity
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.about.AboutFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite.FavoriteFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.home.HomeFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.search.adapter.SearchFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity() : BaseActivity<MainViewModel>(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener {
    var menuItem: MenuItem? = null
    override val viewModel: MainViewModel by viewModel()
    override fun initComponent(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        replaceFragment(HomeFragment(), R.id.child_container, "", false)
        viewModel.isShowToolBar.observe(this, Observer { ishow ->
            ishow?.let { setShowToolbar(it) }
        })
        viewModel.title.observe(this, Observer {
            title = it
        })
        viewModel.isShowArrowBack.observe(this, Observer {
            isShowArrowBack(it ?: return@Observer)
        })
        viewModel.haveStatusBar.observe(this, Observer {
            setShowStatusBar(it ?: return@Observer)
        })
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        menu?.findItem(R.id.item_search)?.apply {
            menuItem = this
            (actionView as SearchView).apply {
                setOnQueryTextListener(this@MainActivity)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query == null || query.isEmpty()) return false
        menuItem?.collapseActionView()
        replaceFragment(SearchFragment.newInstance(query), R.id.child_container, "", true)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}
