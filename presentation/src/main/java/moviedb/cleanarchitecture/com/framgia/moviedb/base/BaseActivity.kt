package moviedb.cleanarchitecture.com.framgia.moviedb.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {
    abstract val viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initComponent(savedInstanceState)
    }

    abstract fun initComponent(savedInstanceState: Bundle?)
    abstract fun getLayout(): Int
    open fun addFragment(fragment: Fragment, container: Int, tag: String, addBackStack: Boolean) {
        supportFragmentManager.beginTransaction().add(container, fragment).apply {
            if (addBackStack) addToBackStack(tag)
        }.commit()
    }

    open fun replaceFragment(fragment: Fragment, container: Int, tag: String, addBackStack: Boolean) {
        supportFragmentManager.beginTransaction().replace(container, fragment).apply {
            if (addBackStack) addToBackStack(tag)
        }.commit()
    }

    fun showArrowBackButton(isEnable: Boolean) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(isEnable)
            setDisplayShowHomeEnabled(isEnable)
        }

    }

    fun setShowToolbar(isShowToolbar: Boolean) {
        if (isShowToolbar) supportActionBar?.show() else supportActionBar?.hide()
    }

    fun isShowArrowBack(isShowArrowBack: Boolean) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(isShowArrowBack)
            setDisplayShowHomeEnabled(isShowArrowBack)
        }
    }

    fun setShowStatusBar(isShowStatusBar: Boolean) {
        if (isShowStatusBar) window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        else window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
