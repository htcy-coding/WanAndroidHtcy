package com.htcy.wanandroid.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatImageView
import com.htcy.wanandroid.R

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/18
 */
class PercentImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatImageView(context, attrs, defStyleAttr) {

    @Basics
    private var mBasics: Int
    private var mPercent: Float
    private var mWidthMeasureSize = 0
    private var mHeightMeasureSize = 0
    fun setBasics(basics: Int) {
        if (mBasics == basics) {
            return
        }
        mBasics = basics
        resetNewSize()
    }

    fun setPercent(percent: Float) {
        if (mPercent == percent) {
            return
        }
        mPercent = percent
        resetNewSize()
    }

    fun setPercent(@Basics basics: Int, percent: Float) {
        if (mBasics == basics && mPercent == percent) {
            return
        }
        mBasics = basics
        mPercent = percent
        resetNewSize()
    }

    init {
        val typedArray: TypedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.PercentImageView)
        mBasics = typedArray.getInt(R.styleable.PercentImageView_piv_basics, BASICS_WIDTH)
        mPercent = typedArray.getFloat(R.styleable.PercentImageView_piv_percent, 1.0f)
        typedArray.recycle()
    }

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mWidthMeasureSize = MeasureSpec.getSize(widthMeasureSpec)
        mHeightMeasureSize = MeasureSpec.getSize(heightMeasureSpec)
        val size = calculateNewSize()
        setMeasuredDimension(size[0], size[1])
    }

    private fun calculateNewSize(): IntArray {
        val size = intArrayOf(mWidthMeasureSize, mHeightMeasureSize)
        if (mBasics == BASICS_WIDTH) {
            size[1] = (mWidthMeasureSize * mPercent).toInt()
        } else if (mBasics == BASICS_HEIGHT) {
            size[0] = (mHeightMeasureSize * mPercent).toInt()
        }
        return size
    }

    private fun resetNewSize() {
        val size = calculateNewSize()
        layoutParams.width = size[0]
        layoutParams.height = size[1]
        requestLayout()
    }

    @IntDef(*[BASICS_WIDTH, BASICS_HEIGHT])
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    internal annotation class Basics
    companion object {
        const val BASICS_WIDTH = 0
        const val BASICS_HEIGHT = 1
    }


}