package com.htcy.wanandroid.ui.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.htcy.wanandroid.R
import per.goweii.actionbarex.ActionBarEx
import per.goweii.actionbarex.common.OnActionBarChildClickListener
import per.goweii.actionbarex.common.R.color
import per.goweii.actionbarex.common.R.dimen
import per.goweii.actionbarex.common.R.layout
import per.goweii.actionbarex.common.R.styleable

class ActionBarCommon @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
): ActionBarEx(context, attrs, defStyleAttr) {
    private var leftText: String? = null
    private var leftTextSize = 0f
    private var leftTextColor = 0
    private var leftTextPaddingLeft = 0
    private var leftTextPaddingRight = 0
    private var leftIconRes = 0
    private var leftIconColor = 0
    private var leftIconPadding = 0
    private var leftIconMarginLeft = 0
    private var rightText: String? = null
    private var rightTextSize = 0f
    private var rightTextColor = 0
    private var rightTextPaddingLeft = 0
    private var rightTextPaddingRight = 0
    private var rightIconRes = 0
    private var rightIconColor = 0
    private var rightIconPadding = 0
    private var rightIconMarginRight = 0
    private var titleText: String? = null
    private var titleTextSize = 0f
    private var titleTextColor = 0
    private var titleTextMaxWidth = 0
    private var leftTextClickToFinish = false
    private var leftIconClickToFinish = false
    private var titleBarChild: RelativeLayout? = null
    var leftIconView: ImageView? = null
        private set
    var leftTextView: TextView? = null
        private set
    var titleTextView: TextView? = null
        private set
    var rightTextView: TextView? = null
        private set
    var rightIconView: ImageView? = null
        private set

    override fun getTitleBarChild(): RelativeLayout {
        return titleBarChild!!
    }

    override fun initAttrs(attrs: AttributeSet) {
        super.initAttrs(attrs)
        val typedArray = this.context.obtainStyledAttributes(attrs, styleable.ActionBarCommon)
        val titleTextMaxWidthDef =
            this.context.resources.getDimension(dimen.title_bar_title_text_max_width_def)
        val iconPaddingDef = this.context.resources.getDimension(dimen.title_bar_icon_padding_def)
        val textSizeDef = this.context.resources.getDimension(dimen.title_bar_text_size_def)
        val textPaddingLeftDef =
            this.context.resources.getDimension(dimen.title_bar_text_padding_left_def)
        val textPaddingRightDef =
            this.context.resources.getDimension(dimen.title_bar_text_padding_right_def)
        val titleTextSizeDef =
            this.context.resources.getDimension(dimen.title_bar_title_text_size_def)
        val iconColorDef = ContextCompat.getColor(this.context, color.title_bar_icon_color_def)
        val textColorDef = ContextCompat.getColor(this.context, color.title_bar_text_color_def)
        val titleTextColorDef =
            ContextCompat.getColor(this.context, color.title_bar_title_text_color_def)
        leftTextClickToFinish =
            typedArray.getBoolean(styleable.ActionBarCommon_abc_leftTextClickToFinish, false)
        leftIconClickToFinish =
            typedArray.getBoolean(styleable.ActionBarCommon_abc_leftIconClickToFinish, false)
        leftText = typedArray.getString(styleable.ActionBarCommon_abc_leftText)
        leftTextSize =
            typedArray.getDimension(styleable.ActionBarCommon_abc_leftTextSize, textSizeDef)
        leftTextColor =
            typedArray.getColor(styleable.ActionBarCommon_abc_leftTextColor, textColorDef)
        leftTextPaddingLeft = typedArray.getDimension(
            styleable.ActionBarCommon_abc_leftTextPaddingLeft,
            textPaddingLeftDef
        )
            .toInt()
        leftTextPaddingRight = typedArray.getDimension(
            styleable.ActionBarCommon_abc_leftTextPaddingRight,
            textPaddingRightDef
        )
            .toInt()
        leftIconRes = typedArray.getResourceId(styleable.ActionBarCommon_abc_leftIconRes, 0)
        leftIconColor =
            typedArray.getColor(styleable.ActionBarCommon_abc_leftIconColor, iconColorDef)
        leftIconPadding =
            typedArray.getDimension(styleable.ActionBarCommon_abc_leftIconPadding, iconPaddingDef)
                .toInt()
        leftIconMarginLeft =
            typedArray.getDimension(styleable.ActionBarCommon_abc_leftIconMarginLeft, 0.0f).toInt()
        rightText = typedArray.getString(styleable.ActionBarCommon_abc_rightText)
        rightTextSize =
            typedArray.getDimension(styleable.ActionBarCommon_abc_rightTextSize, textSizeDef)
        rightTextColor =
            typedArray.getColor(styleable.ActionBarCommon_abc_rightTextColor, textColorDef)
        rightTextPaddingLeft = typedArray.getDimension(
            styleable.ActionBarCommon_abc_rightTextPaddingLeft,
            textPaddingLeftDef
        )
            .toInt()
        rightTextPaddingRight = typedArray.getDimension(
            styleable.ActionBarCommon_abc_rightTextPaddingRight,
            textPaddingRightDef
        )
            .toInt()
        rightIconRes = typedArray.getResourceId(styleable.ActionBarCommon_abc_rightIconRes, 0)
        rightIconColor =
            typedArray.getColor(styleable.ActionBarCommon_abc_rightIconColor, iconColorDef)
        rightIconPadding =
            typedArray.getDimension(styleable.ActionBarCommon_abc_rightIconPadding, iconPaddingDef)
                .toInt()
        rightIconMarginRight =
            typedArray.getDimension(styleable.ActionBarCommon_abc_rightIconMarginRight, 0.0f)
                .toInt()
        titleText = typedArray.getString(styleable.ActionBarCommon_abc_titleText)
        titleTextSize =
            typedArray.getDimension(styleable.ActionBarCommon_abc_titleTextSize, titleTextSizeDef)
        titleTextColor =
            typedArray.getColor(styleable.ActionBarCommon_abc_titleTextColor, titleTextColorDef)
        titleTextMaxWidth = typedArray.getDimension(
            styleable.ActionBarCommon_abc_titleTextMaxWidth,
            titleTextMaxWidthDef
        )
            .toInt()
        typedArray.recycle()
    }

    override fun inflateTitleBar(): View {
        titleBarChild = inflate(
            this.context,
            layout.action_bar_title_bar_common,
            null as ViewGroup?
        ) as RelativeLayout
        leftIconView = titleBarChild!!.findViewById<View>(R.id.iv_left) as ImageView
        leftTextView = titleBarChild!!.findViewById<View>(R.id.tv_left) as TextView
        titleTextView = titleBarChild!!.findViewById<View>(R.id.tv_title) as TextView
        rightTextView = titleBarChild!!.findViewById<View>(R.id.tv_right) as TextView
        rightIconView = titleBarChild!!.findViewById<View>(R.id.iv_right) as ImageView
        val leftIconViewParams = leftIconView!!.layoutParams as LinearLayout.LayoutParams
        leftIconViewParams.leftMargin = leftIconMarginLeft
        leftIconView!!.layoutParams = leftIconViewParams
        if (leftIconRes > 0) {
            leftIconView!!.visibility = View.GONE
            leftIconView!!.setPadding(
                leftIconPadding, leftIconPadding, leftIconPadding,
                leftIconPadding
            )
            leftIconView!!.setImageResource(leftIconRes)
            leftIconView!!.setColorFilter(leftIconColor)
            if (leftIconClickToFinish) {
                leftIconView!!.setOnClickListener { finishActivity() }
            }
        } else {
            leftIconView!!.visibility = View.VISIBLE
        }
        if (!TextUtils.isEmpty(leftText)) {
            leftTextView!!.visibility = View.GONE
            leftTextView!!.text = leftText
            leftTextView!!.setTextColor(leftTextColor)
            leftTextView!!.setTextSize(0, leftTextSize)
            leftTextView!!.setPadding(leftTextPaddingLeft, 0, leftTextPaddingRight, 0)
            if (leftTextClickToFinish) {
                leftTextView!!.setOnClickListener { finishActivity() }
            }
        } else {
            leftTextView!!.visibility = View.VISIBLE
        }
        titleTextView!!.visibility = View.GONE
        titleTextView!!.text = titleText
        titleTextView!!.setTextColor(titleTextColor)
        titleTextView!!.setTextSize(0, titleTextSize)
        titleTextView!!.maxWidth = titleTextMaxWidth
        val rightIconViewParams = rightIconView!!.layoutParams as LinearLayout.LayoutParams
        rightIconViewParams.rightMargin = rightIconMarginRight
        rightIconView!!.layoutParams = rightIconViewParams
        if (rightIconRes > 0) {
            rightIconView!!.visibility = View.GONE
            rightIconView!!.setPadding(
                rightIconPadding,
                rightIconPadding, rightIconPadding, rightIconPadding
            )
            rightIconView!!.setImageResource(rightIconRes)
            rightIconView!!.setColorFilter(rightIconColor)
        } else {
            rightIconView!!.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(rightText)) {
            rightTextView!!.visibility = View.GONE
            rightTextView!!.text = rightText
            rightTextView!!.setTextColor(rightTextColor)
            rightTextView!!.setTextSize(0, rightTextSize)
            rightTextView!!.setPadding(rightTextPaddingLeft, 0, rightTextPaddingRight, 0)
        } else {
            rightTextView!!.visibility = View.VISIBLE
        }
        return titleBarChild!!
    }

    fun setOnLeftIconClickListener(onLeftIconClickListener: OnActionBarChildClickListener?) {
        leftIconView!!.setOnClickListener { v -> onLeftIconClickListener?.onClick(v) }
    }

    fun setOnLeftTextClickListener(onLeftTextClickListener: OnActionBarChildClickListener?) {
        leftTextView!!.setOnClickListener { v -> onLeftTextClickListener?.onClick(v) }
    }

    fun setOnRightTextClickListener(onRightTextClickListener: OnActionBarChildClickListener?) {
        rightTextView!!.setOnClickListener { v -> onRightTextClickListener?.onClick(v) }
    }

    fun setOnRightIconClickListener(onRightIconClickListener: OnActionBarChildClickListener?) {
        rightIconView!!.setOnClickListener { v -> onRightIconClickListener?.onClick(v) }
    }
}