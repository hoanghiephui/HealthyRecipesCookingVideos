package com.food.healthyrecipes.cookingvideo.views.widget

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.NestedScrollingChild
import android.support.v4.view.NestedScrollingChildHelper
import android.util.AttributeSet
import android.view.View

/**
 * Created by hoanghiep on 8/3/17.
 */

class NestedCoordinatorLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet = null!!, defStyleAttr: Int = 0) : CoordinatorLayout(context, attrs, defStyleAttr), NestedScrollingChild {

    private var mHelper: NestedScrollingChildHelper? = null

    init {
        run {
            mHelper = NestedScrollingChildHelper(this)
            isNestedScrollingEnabled = true
        }
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        val executedSuper = super.onStartNestedScroll(child, target, nestedScrollAxes)
        return startNestedScroll(nestedScrollAxes) || executedSuper
    }

    override fun onStopNestedScroll(target: View) {
        super.onStopNestedScroll(target)
        stopNestedScroll()
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        val consumedSuper = IntArray(2)
        val consumedNest = IntArray(2)
        run {
            super.onNestedPreScroll(target, dx, dy, consumedSuper)
            dispatchNestedPreScroll(dx, dy, consumedNest, null)
        }
        run {
            consumed[0] = consumedSuper[0] + consumedNest[0]
            consumed[1] = consumedSuper[1] + consumedNest[1]
        }
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, null)
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        val executedSuper = super.onNestedPreFling(target, velocityX, velocityY)
        return dispatchNestedPreFling(velocityX, velocityY) || executedSuper
    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        val executedSuper = super.onNestedFling(target, velocityX, velocityY, consumed)
        return dispatchNestedFling(velocityX, velocityY, consumed) || executedSuper
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        super.setNestedScrollingEnabled(enabled)
        mHelper!!.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        val executedSuper = super.isNestedScrollingEnabled()
        return mHelper!!.isNestedScrollingEnabled || executedSuper
    }

    override fun startNestedScroll(axes: Int): Boolean {

        val executedSuper = super.startNestedScroll(axes)
        return mHelper!!.startNestedScroll(axes) || executedSuper
    }

    override fun stopNestedScroll() {
        super.stopNestedScroll()
        mHelper!!.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        val executedSuper = super.hasNestedScrollingParent()
        return mHelper!!.hasNestedScrollingParent() || executedSuper
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        val executedSuper = super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
        return mHelper!!.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow) || executedSuper
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        val executedSuper = super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
        return mHelper!!.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow) || executedSuper
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        val executedSuper = super.dispatchNestedFling(velocityX, velocityY, consumed)
        return mHelper!!.dispatchNestedFling(velocityX, velocityY, consumed) || executedSuper
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        val executedSuper = super.dispatchNestedPreFling(velocityX, velocityY)
        return mHelper!!.dispatchNestedPreFling(velocityX, velocityY) || executedSuper
    }

}
