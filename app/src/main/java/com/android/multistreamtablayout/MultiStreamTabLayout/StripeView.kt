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
    lateinit var underLine: Rect
    lateinit var underLinePaint: Paint

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
        underLinePaint = Paint().apply {
            this.alpha = 15
        }
        underLine = Rect()
        rect = Rect()
        stripePaint = Paint().apply {
            isAntiAlias = true
            this.style = Paint.Style.FILL

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

            underLine.apply {
                top = this@StripeView.height - 508
                bottom = this@StripeView.height - 500
                left = index * stripeWidth
                right = index * stripeWidth + stripeWidth
            }
            val paintColor =  Color.parseColor(stripe.colorString)
            stripePaint.apply {

                alpha = 70
                shader = LinearGradient(
                     0f, canvas?.height?.toFloat()!!, 0f, 0f, Color.TRANSPARENT, paintColor, Shader.TileMode.CLAMP)
            }
            underLinePaint.apply {
                color = Color.parseColor(stripe.colorString)

            }
            canvas?.drawRect(rect, stripePaint)
            canvas?.drawRect(underLine, underLinePaint)
        }

    }

}