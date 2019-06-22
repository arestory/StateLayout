package com.arestory.statelayout

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.View
import android.view.ViewStub
import android.widget.FrameLayout

class StateContentLayout : FrameLayout {

    private var dataView: View? = null
    private var loadingView: View? = null
    private var emptyView: View? = null
    private var errorView: View? = null

    private var loadingViewId: Int = 0
    private var emptyViewId: Int = 0
    private var errorViewId: Int = 0

    constructor(context: Context) : super(context) {

        init(context)

    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context)
    }


    private fun init(context: Context, attrs: AttributeSet? = null) {
        View.inflate(context, R.layout.layout_state, this)
        val att = context.obtainStyledAttributes(attrs, R.styleable.StateContentLayout)
        loadingViewId = att.getResourceId(R.styleable.StateContentLayout_loadingLayout, R.layout.layout_loading)
        emptyViewId = att.getResourceId(R.styleable.StateContentLayout_emptyLayout, R.layout.layout_empty)
        errorViewId = att.getResourceId(R.styleable.StateContentLayout_errorLayout, R.layout.layout_error)
        att.recycle()
    }

    fun showLoading(viewCallBack: ViewCallBack? = null) {
        if (loadingView == null) {
            val loadingStub = findViewById<ViewStub>(R.id.loadingStub)
            loadingStub.layoutResource = loadingViewId
            loadingView = loadingStub.inflate()
        }
        viewCallBack?.onView(loadingView!!)
        managerLayoutVisible(loadingVisible = View.VISIBLE)
    }

    fun showLoading() {

        showLoading(null)
    }

    private fun managerLayoutVisible(
        loadingVisible: Int = View.GONE,
        emptyVisible: Int = View.GONE,
        errorVisible: Int = View.GONE,
        dataVisible: Int = View.GONE
    ) {
        loadingView?.visibility = loadingVisible
        emptyView?.visibility = emptyVisible
        errorView?.visibility = errorVisible
        dataView?.visibility = dataVisible
    }

    fun showEmpty(viewCallBack: ViewCallBack? = null) {
        if (emptyView == null) {
            val emptyStub = findViewById<ViewStub>(R.id.emptyStub)
            emptyStub.layoutResource = emptyViewId
            emptyView = emptyStub.inflate()
        }
        viewCallBack?.onView(emptyView!!)

        managerLayoutVisible(emptyVisible = View.VISIBLE)
    }

    fun showEmpty() {
        showEmpty(null)
    }

    fun showError(viewCallBack: ViewCallBack? = null) {
        if (errorView == null) {
            val errorStub = findViewById<ViewStub>(R.id.errorStub)
            errorStub.layoutResource = errorViewId
            errorView = errorStub.inflate()
        }
        viewCallBack?.onView(errorView!!)

        managerLayoutVisible(errorVisible = View.VISIBLE)
    }

    fun showError(){
        showError(null)
    }

    fun showContent() {
        managerLayoutVisible(dataVisible = View.VISIBLE)
    }

    fun initStateLayout(@LayoutRes loadingViewId: Int = R.layout.layout_loading, @LayoutRes emptyViewId: Int = R.layout.layout_empty, @LayoutRes errorViewId: Int = R.layout.layout_error) {

        this.loadingViewId = loadingViewId
        this.emptyViewId = emptyViewId
        this.errorViewId = errorViewId
    }

    fun setLoadingLayout(@LayoutRes loadingViewId: Int = R.layout.layout_loading) {
        this.loadingViewId = loadingViewId
    }

    fun setEmptyLayout(@LayoutRes emptyViewId: Int = R.layout.layout_empty) {
        this.emptyViewId = emptyViewId
    }

    fun setErrorLayout(@LayoutRes errorViewId: Int = R.layout.layout_error) {
        this.errorViewId = errorViewId
    }


    public interface ViewCallBack {

        fun onView(view: View)
    }


    override fun addView(child: View?) {

        if (childCount > 1) {
            throw IllegalStateException("StateContentLayout只允许存在一个子布局\nStateContentLayout can host only one direct child")
        } else {
            super.addView(child)
        }
    }

    override fun addView(child: View, index: Int) {
        if (this.childCount > 1) {
            throw IllegalStateException("StateContentLayout只允许存在一个子布局\nStateContentLayout can host only one direct child")
        } else {
            super.addView(child, index)
        }
    }

    override fun addView(child: View, params: android.view.ViewGroup.LayoutParams) {

        if (this.childCount > 1) {
            throw IllegalStateException("StateContentLayout只允许存在一个子布局\nStateContentLayout can host only one direct child")
        } else {
            super.addView(child, params)
        }
    }

    override fun addView(child: View, index: Int, params: android.view.ViewGroup.LayoutParams) {
        if (this.childCount > 1) {
            throw IllegalStateException("StateContentLayout只允许存在一个子布局\nStateContentLayout can host only one direct child")
        } else {
            super.addView(child, index, params)
            dataView = child

        }
    }
}
