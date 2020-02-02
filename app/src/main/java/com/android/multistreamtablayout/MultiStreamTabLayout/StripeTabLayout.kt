package com.android.multistreamtablayout.MultiStreamTabLayout

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewpager2.widget.ViewPager2
import com.android.multistreamtablayout.R

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StripeTabLayout : ConstraintLayout {
    lateinit var typedArray: TypedArray
    var showStripes: Boolean = true
    lateinit var tabLayout: TabLayout
    lateinit var stripe: StripeView
    var indicatorColor = Color.BLUE


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }



    fun init() {
        typedArray = context.obtainStyledAttributes(R.styleable.StripeTabLayout).apply {
            showStripes = getBoolean(R.styleable.StripeTabLayout_showStripes, true)
            indicatorColor = getColor(R.styleable.StripeTabLayout_indicatorColor, Color.BLUE)
            recycle()
        }


        tabLayout = TabLayout(context, null, R.style.tabCustomStyle).apply {
            this.id = R.id.tabLayout
            this.tabMode = TabLayout.MODE_FIXED
            this.tabGravity = TabLayout.GRAVITY_FILL
            this.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            this.isInlineLabel = true
            this.elevation = 2f
            this.isTabIndicatorFullWidth = true
            this.minimumWidth = 0
            this.setSelectedTabIndicatorColor(indicatorColor)
        }

//        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.default_tab))
//        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.default_tab))
//        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.default_tab))
        addView(tabLayout)

        stripe = StripeView(context).apply {
            this.id = R.id.stripeView
            this.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }

        addView(stripe)

        setConstraints()
        val stripes = mutableListOf<Stripe>(Stripe("#5e4eba"), Stripe("#fe0000"), Stripe("#172450"))
        addStripes(stripes)
    }


    private fun setConstraints() {

        val constraintSet = ConstraintSet().apply {
            clone(this@StripeTabLayout)
            connect(tabLayout.id, ConstraintSet.BOTTOM, id, ConstraintSet.BOTTOM, (250).toInt())
        }
        setConstraintSet(constraintSet)
    }


    fun addTab(title: String, color: Color, tabViewId: Int = R.layout.default_tab) {
        tabLayout.addTab(tabLayout.newTab().apply {

            setCustomView(tabViewId)
        })
    }

    fun addStripe(colorString: String) {
        stripe.addStripe(colorString)
    }


    fun addStripes(stripes: Collection<Stripe>) {
        this.stripe.addStripes(stripes)
    }

    fun setupWithViewPager(viewPager: ViewPager2, setupTabs:(TabLayout.Tab, Int) -> Unit) {
        TabLayoutMediator(tabLayout, viewPager, true, setupTabs).attach()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }
}