package com.htcy.wanandroid.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ActionIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightSize: Int = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(heightSize, heightSize)
    }

}