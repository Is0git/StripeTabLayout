package com.android.multistreamtablayout.MultiStreamTabLayout

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class StripeView : View {

    lateinit var rect: Rect
    lateinit var stripePaint: Paint
    val stripes = mutableListOf<Stripe>()
    var canvas: Canvas? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    fun init() {
        setWillNotDraw(false)

        rect = Rect()
        stripePaint = Paint().apply {
            isAntiAlias = true
            setAlpha(0.08f)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSize(width, widthMeasureSpec)
        val height = resolveSize(height, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawStripes(canvas)
    }

    fun addStripe(colorString: String) {
        stripes.add(Stripe(colorString))
        invalidate()

    }

    fun addStripes(stripes: Collection<Stripe>) {
        this.stripes.addAll(stripes)
    }

    fun drawStripes(canvas: Canvas?) {
        val stripeWidth = if(!stripes.isNullOrEmpty()) width / stripes.count() else return
        stripes.forEachIndexed { index, stripe ->
            rect.apply {
                top = 0
                bottom = this@StripeView.height
                left = index * stripeWidth
                right = index * stripeWidth + stripeWidth
            }
            stripePaint.color = Color.parseColor(stripe.colorString)
            canvas?.drawRect(rect, stripePaint)
        }

    }

}