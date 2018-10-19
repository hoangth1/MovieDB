package moviedb.cleanarchitecture.com.framgia.moviedb.base

import android.app.AlertDialog
import android.app.Dialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.moviedb.BR

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {
    abstract val bindingVariable: Int
    lateinit var viewBinding: ViewBinding
    abstract val viewModel: ViewModel
    abstract val layoutId: Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.apply {
            root.isClickable = true
            initComponent(viewBinding)
            setVariable(BR.viewModel, viewModel)
            setLifecycleOwner(this@BaseFragment)
            executePendingBindings()
        }
        return viewBinding.root
    }

    abstract fun initComponent(viewDataBinding: ViewBinding)
    fun findFragment(tag: String): Fragment? {
        return activity?.supportFragmentManager?.findFragmentByTag(tag)
    }

    fun findChildFragmet(parentFragment: Fragment = this, tag: String): Fragment? {
        return parentFragment.childFragmentManager.findFragmentByTag(tag)
    }

    fun replaceFragment(container: Int, fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(container, fragment)?.apply {
            if (addToBackStack) addToBackStack(tag)
        }?.commit()
    }

    fun replaceChildFragment(parentFragment: Fragment = this,
                             container: Int, fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        parentFragment.childFragmentManager.beginTransaction().replace(container, fragment).apply {
            if (addToBackStack) addToBackStack(tag)
        }?.commit()

    }

    fun addChildFragment(parentFragment: Fragment = this, container: Int,
                         fragment: Fragment, tag: String, addToBackStack: Boolean) {
        parentFragment.childFragmentManager.beginTransaction().add(container, fragment).apply {
            if (addToBackStack) addToBackStack(tag)
        }.commit()
    }

    fun popChildFragment(parentFragment: Fragment = this) {
        parentFragment.childFragmentManager.popBackStack()
    }
}
