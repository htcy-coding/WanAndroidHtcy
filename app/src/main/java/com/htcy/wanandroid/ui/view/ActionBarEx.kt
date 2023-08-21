package com.htcy.wanandroid.ui.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.htcy.arti.utils.Utils
import per.goweii.actionbarex.R
import per.goweii.statusbarcompat.StatusBarCompat

/**
 * 高拓展性和定制性的ActionBar
 * 整个ActionBar分为3层：
 * ----BackgroundLayer 背景层：可自定义布局
 * ----ActionBarLayer 主体层：改层为垂直线性布局，包含下面三个部分：
 * --------StatusBar：系统状态栏
 * --------TitleBar：位于StatusBar和BottomLine之间，可自定义布局
 * --------BottomLine：分割线
 * ----ForegroundLayer 前景层：可自定义布局
 *
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
open class ActionBarEx @JvmOverloads constructor(
    context: Context?,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context!!, attrs, defStyleAttr) {
    private var mAutoImmersion = false
    private var mBackgroundLayerLayoutRes = 0
    private var mBackgroundLayerImageRes = 0
    private var mStatusBarVisible = false
    private var mStatusBarDarkMode = false
    private val mStatusBarHeight: Int
    private var mStatusBarColor = 0
    private var mTitleBarLayoutRes = 0
    var titleBarHeight = 0
        private set
    private var mBottomLineColor = 0
    private var mBottomLineResId = 0
    var bottomHeight = 0
        private set
    private var mForegroundLayerLayoutRes = 0
    private var mClickToFinishViewId = 0
    private var mBottomLineOutside = false
    var backgroundLayer: View? = null
        private set
    var actionBar: LinearLayout? = null
        private set
    var statusBar: View? = null
        private set
    var titleBar: FrameLayout? = null
        private set
    var titleBarChild: View? = null
        private set
    var bottomLine: View? = null
        private set
    var foregroundLayer: View? = null
        private set
    private var views: SparseArray<View?>? = null

    /**
     * 获取View并缓存，以便下次获取，避免频繁调用findViewById
     *
     * @param id View的id
     * @return View
     */
    fun <V : View?> getView(@IdRes id: Int): V? {
        if (views == null) {
            views = SparseArray()
        }
        var view = views!![id]
        if (view == null) {
            view = findViewById(id)
            views!!.put(id, view)
        }
        return view as V?
    }

    val actionBarHeight: Int
        get() = if (mBottomLineOutside) {
            statusBarHeight + titleBarHeight
        } else {
            statusBarHeight + titleBarHeight + bottomHeight
        }
    val statusBarHeight: Int
        get() = if (mStatusBarVisible) mStatusBarHeight else 0

    /**
     * 初始化布局文件传入的配置
     * 子类可重写并初始化自己定义的属性配置，必须保证在第一行调用super方法
     *
     * @param attrs AttributeSet
     */
    protected open fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ActionBarEx)
        val titleBarHeightDef = context.resources.getDimension(R.dimen.title_bar_height_def)
        val bottomLineHeightDef = context.resources.getDimension(R.dimen.bottom_line_height_def)
        val bottomLineColorDef: Int = ContextCompat.getColor(context, R.color.bottom_line_color_def)
        val statusBarColorDef: Int = ContextCompat.getColor(context, R.color.status_bar_color_def)
        mAutoImmersion = typedArray.getBoolean(R.styleable.ActionBarEx_ab_autoImmersion, true)
        mBackgroundLayerLayoutRes =
            typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerLayout, 0)
        mBackgroundLayerImageRes =
            typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerImageRes, 0)
        mStatusBarVisible = typedArray.getBoolean(R.styleable.ActionBarEx_ab_statusBarVisible, true)
        mStatusBarDarkMode = typedArray.getInt(
            R.styleable.ActionBarEx_ab_statusBarMode,
            STATUS_BAR_MODE_LIGHT
        ) == STATUS_BAR_MODE_DARK
        mStatusBarColor =
            typedArray.getColor(R.styleable.ActionBarEx_ab_statusBarColor, statusBarColorDef)
        mTitleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_titleBarLayout, 0)
        titleBarHeight =
            typedArray.getDimension(R.styleable.ActionBarEx_ab_titleBarHeight, titleBarHeightDef)
                .toInt()
        bottomHeight =
            typedArray.getDimension(
                R.styleable.ActionBarEx_ab_bottomLineHeight,
                bottomLineHeightDef
            )
                .toInt()
        mBottomLineColor =
            typedArray.getColor(R.styleable.ActionBarEx_ab_bottomLineColor, bottomLineColorDef)
        mBottomLineResId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_bottomLineResId, 0)
        mBottomLineOutside =
            typedArray.getBoolean(R.styleable.ActionBarEx_ab_bottomLineOutside, false)
        mForegroundLayerLayoutRes =
            typedArray.getResourceId(R.styleable.ActionBarEx_ab_foregroundLayerLayout, 0)
        mClickToFinishViewId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_clickToFinish, 0)
        typedArray.recycle()
    }

    /**
     * 初始化子TitleBar
     *
     * @return TitleBarChild
     */
    protected open fun inflateTitleBar(): View? {
        return if (mTitleBarLayoutRes > 0) {
            inflate(context, mTitleBarLayoutRes, null)
        } else null
    }

    private fun initView() {
        // 1 初始化BackgroundLayer
        if (mBackgroundLayerLayoutRes > 0) {
            backgroundLayer = inflate(context, mBackgroundLayerLayoutRes, null)
            addViewInLayout(backgroundLayer, childCount, makeLayerLayoutParams(), true)
        } else {
            if (mBackgroundLayerImageRes > 0) {
                val actionBarImageView = ImageView(
                    context
                )
                actionBarImageView.setImageResource(mBackgroundLayerImageRes)
                actionBarImageView.scaleType = ImageView.ScaleType.FIT_XY
                addViewInLayout(actionBarImageView, childCount, makeLayerLayoutParams(), true)
            }
        }

        // 2 初始ActionBarLayer
        actionBar = inflate(context, R.layout.action_bar, null) as LinearLayout
        actionBar!!.layoutParams = makeLayoutParamsWithHeight(actionBarHeight)
        // 2.1 初始StatusBar
        statusBar = actionBar!!.findViewById(R.id.status_bar)
        statusBar?.layoutParams = makeLayoutParamsWithHeight(mStatusBarHeight)
        statusBar?.setBackgroundColor(mStatusBarColor)
        statusBar?.setVisibility(if (mStatusBarVisible) VISIBLE else GONE)
        // 2.2 初始TitleBar
        titleBar = actionBar!!.findViewById(R.id.title_bar)
        titleBar?.setClickable(true)
        titleBar?.setFocusable(true)
        titleBar?.setFocusableInTouchMode(true)
        titleBar?.setLayoutParams(makeLayoutParamsWithHeight(titleBarHeight))
        titleBarChild = inflateTitleBar()
        if (titleBarChild != null) {
            titleBar?.addView(titleBarChild)
        }
        // 2.3 初始BottomLine
        bottomLine = actionBar!!.findViewById(R.id.bottom_line)
        bottomLine?.setLayoutParams(makeLayoutParamsWithHeight(bottomHeight))
        if (mBottomLineResId > 0) {
            bottomLine?.setBackgroundResource(mBottomLineResId)
        } else {
            bottomLine?.setBackgroundColor(mBottomLineColor)
        }
        if (mBottomLineOutside) {
            actionBar!!.clipChildren = false
            clipChildren = false
        }
        addViewInLayout(
            actionBar,
            childCount, makeLayoutParamsWithHeight(actionBarHeight), true
        )

        // 3 初始ForegroundLayer
        if (mForegroundLayerLayoutRes > 0) {
            foregroundLayer = inflate(context, mForegroundLayerLayoutRes, null)
            addViewInLayout(foregroundLayer, childCount, makeLayerLayoutParams(), true)
        }
        performClickToFinish()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mBottomLineOutside) {
            val parent = parent
            if (parent is ViewGroup) {
                parent.clipChildren = false
            }
        }
    }

    private fun performClickToFinish() {
        if (titleBarChild == null) {
            return
        }
        val clickToFinishView = getView<View>(mClickToFinishViewId) ?: return
        clickToFinishView.setOnClickListener { finishActivity() }
    }

    private fun makeLayerLayoutParams(): LayoutParams {
        return LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            actionBarHeight
        )
    }

    private fun makeLayoutParamsWithHeight(height: Int): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    }

    /**
     * 设置沉浸模式
     */
    private fun makeImmersion() {
        hintSystemActionBar()
        refreshStatusBar()
    }

    /**
     * 透明状态栏，改变状态栏图标颜色模式
     */
    fun refreshStatusBar() {
        val activity = activity ?: return
        StatusBarCompat.setIconMode(activity, mStatusBarDarkMode)
        if (mAutoImmersion) {
            StatusBarCompat.transparent(activity)
        } else {
            val window = activity.window
            StatusBarCompat.setColor(window, mStatusBarColor)
        }
    }

    /**
     * 隐藏默认的ActionBar
     */
    private fun hintSystemActionBar() {
        val activity = activity ?: return
        if (activity.actionBar != null) {
            activity.actionBar!!.hide()
        }
        if (activity is AppCompatActivity) {
            val compatActivity: AppCompatActivity = activity as AppCompatActivity
            if (compatActivity.getSupportActionBar() != null) {
                compatActivity.getSupportActionBar()?.hide()
            }
        }
    }

    /**
     * 从当前上下文获取Activity
     */
    @get:Nullable
    private val activity: Activity?
        private get() = Utils.getActivity(context)

    private fun finishActivity() {
        Utils.finishActivity(context)
    }

    override fun isInEditMode(): Boolean {
        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    companion object {
        private const val STATUS_BAR_MODE_LIGHT = 0
        private const val STATUS_BAR_MODE_DARK = 1
    }

    init {
        mStatusBarHeight = StatusBarCompat.getHeight(context)
        initAttrs(attrs)
        makeImmersion()
        initView()
    }
}