package com.miftahulhudaf.sahabatbencana.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager
        if (layoutManager is LinearLayoutManager) {
            val position = parent.getChildAdapterPosition(view)
            val itemCount = state.itemCount

            if (layoutManager.orientation == RecyclerView.VERTICAL) {
                outRect.left = space
                outRect.right = space
                outRect.bottom = space
                outRect.top = if (position == 0) space else 0

                if (position == itemCount - 1) {
                    outRect.bottom = space
                }
            } else { // LinearLayoutManager.HORIZONTAL
                outRect.top = space
                outRect.bottom = space
                outRect.right = space
                outRect.left = if (position == 0) space else 0

                if (position == itemCount - 1) {
                    outRect.right = space
                }
            }
        }
    }
}
