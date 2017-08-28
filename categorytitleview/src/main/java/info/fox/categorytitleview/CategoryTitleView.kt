package info.fox.categorytitleview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 *<p>
 * Created by user
 * on 2017/8/28.
 *</p>
 */
class CategoryTitleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val startText: TextView = TextView(context)
    private val endText: TextView = TextView(context)
    private val startIcon: ImageView = ImageView(context)
    private val endIcon: ImageView = ImageView(context)
    private val subText = TextView(context)

    private val container = LinearLayout(context)

    init {
        gravity = Gravity.CENTER_VERTICAL
        // START TEXT
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CategoryTitleView)
        startText.text = typedArray.getString(R.styleable.CategoryTitleView_startText)

        val titleSize = typedArray.getDimensionPixelSize(R.styleable.CategoryTitleView_startTextSize,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18f, resources.displayMetrics).toInt())
        startText.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize.toFloat())

        val titleColor = typedArray.getColor(R.styleable.CategoryTitleView_startTextColor, Color.parseColor("#212121"))
        startText.setTextColor(titleColor)
        startText.maxLines = 1
        startText.setSingleLine(true)

        // END TEXT
        endText.text = typedArray.getString(R.styleable.CategoryTitleView_endText)
        val endTitleSize = typedArray.getDimensionPixelSize(R.styleable.CategoryTitleView_endTextSize,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14f, resources.displayMetrics).toInt())
        endText.setTextSize(TypedValue.COMPLEX_UNIT_PX, endTitleSize.toFloat())

        val endTitleColor = typedArray.getColor(R.styleable.CategoryTitleView_endTextColor, Color.parseColor("#757575"))
        endText.setTextColor(endTitleColor)
        endText.setSingleLine(true)
        endText.setSingleLine(true)

        // START ICON
        val icon1 = typedArray.getResourceId(R.styleable.CategoryTitleView_startIcon, 0)
        startIcon.setImageResource(icon1)
        // END ICON
        val icon2 = typedArray.getResourceId(R.styleable.CategoryTitleView_endIcon, 0)
        endIcon.setImageResource(icon2)

        // SUB TEXT
        subText.text = typedArray.getString(R.styleable.CategoryTitleView_subText)
        val subTextSize = typedArray.getDimensionPixelSize(R.styleable.CategoryTitleView_subTextSize,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics).toInt())
        val subTextColor = typedArray.getColor(R.styleable.CategoryTitleView_subTextColor, Color.parseColor("#757575"))
        subText.setTextSize(TypedValue.COMPLEX_UNIT_PX, subTextSize.toFloat())
        subText.setTextColor(subTextColor)

        // MARGIN
        val startMargin = typedArray.getDimensionPixelSize(R.styleable.CategoryTitleView_startMargin, 0)
        val endMargin = typedArray.getDimensionPixelSize(R.styleable.CategoryTitleView_endMargin, 0)

        /** ADD CHILD VIEW*/
        /* start icon */
        val p = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        p.gravity = Gravity.CENTER_VERTICAL
        addView(startIcon, p)

        /*start text and sub text inside container*/
        container.orientation = VERTICAL
        container.gravity = Gravity.CENTER_VERTICAL
        container.addView(startText, p)
        container.addView(subText, p)
        if (subText.text == "") {
            subText.visibility = GONE
        }

        /*container*/
        val p1 = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1F)
        p1.gravity = Gravity.CENTER_VERTICAL
        p1.leftMargin = startMargin
        addView(container, p1)

        /*end text*/
        val p2 = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        endText.gravity = Gravity.END
        p2.rightMargin = endMargin
        addView(endText, p2)
        addView(endIcon, p)

        typedArray.recycle()
    }

    fun setStartText(content: String) {
        startText.text = content
    }

    fun setStartText(resId: Int) {
        startText.setText(resId)
    }

    fun setEndText(content: String) {
        endText.text = content
    }

    fun setEndText(resId: Int) {
        endText.setText(resId)
    }

    fun setSubText(content: String) {
        subText.text = content
        if (content != "") {
            subText.visibility = VISIBLE
        } else {
            subText.visibility = GONE
        }
    }



    fun getStartText() = startText.text.toString()

    fun getEndText() = endText.text.toString()

    fun getSubText() = subText.text.toString()


}