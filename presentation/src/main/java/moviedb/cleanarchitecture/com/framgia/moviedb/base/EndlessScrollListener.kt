package moviedb.cleanarchitecture.com.framgia.moviedb.base

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

class EndlessScrollListener(val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {
    var isLoadding = false
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var lastItem = 0
        var visibleItem = 0
        var totalItem = 0
        when (recyclerView?.layoutManager) {
            is LinearLayoutManager -> {
                (recyclerView.layoutManager as LinearLayoutManager).apply {
                    lastItem = findLastVisibleItemPosition()
                    totalItem = itemCount
                }
            }
            is GridLayoutManager -> {
                (recyclerView.layoutManager as GridLayoutManager).apply {
                    lastItem = findFirstVisibleItemPosition()
                    totalItem = itemCount
                }
            }
        }
        if (!isLoadding && lastItem + 1 == totalItem) {
            onLoadMore.invoke()
            isLoadding = true
        }
    }
}