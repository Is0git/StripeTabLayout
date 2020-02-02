package com.android.multistreamtablayout.MultiStreamTabLayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class StripeView : View {

    lateinit var rect: Rect
    lateinit var stripePaint: Paint
    var canvas: Canvas? = null
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    fun init() {
            rect = Rect()
            stripePaint = Paint().apply {
                isAntiAlias = true
            }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas
    }

    fun drawStripe(canvas: Canvas?, width: Int, colorString: String) {
        rect.apply {
            top = 0
            bottom = this@StripeView.height
            left = 0
            right = this@StripeView.width
        }
        stripePaint.color = Color.parseColor(colorString)
        canvas?.drawRect(rect, stripePaint)

    }

}