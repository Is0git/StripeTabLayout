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

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    lateinit var tabLayout: TabLayout

    fun init() {
        typedArray = context.obtainStyledAttributes(R.styleable.StripeTabLayout).apply {
            showStripes = getBoolean(R.styleable.StripeTabLayout_showStripes, true)
            recycle()
        }


        tabLayout = TabLayout(context, null, R.style.tabCustomStyle).apply {
            id = R.id.tabLayout
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            this.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            this.isInlineLabel = true
            this.isTabIndicatorFullWidth = true
        }

        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.default_tab))
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.default_tab))


        addView(tabLayout)
        setConstraints()


    }


    fun setConstraints() {

        val constraintSet = ConstraintSet().apply {
            clone(this@StripeTabLayout)
            connect(tabLayout.id, ConstraintSet.BOTTOM, id, ConstraintSet.BOTTOM)
        }
        setConstraintSet(constraintSet)
    }


    fun addTab(title: String, color: Color, tabViewId: Int = R.layout.default_tab) {
        tabLayout.addTab(tabLayout.newTab().apply {

            setCustomView(tabViewId) })
    }

    fun setupWithViewPager(viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setCustomView(R.layout.default_tab)
        }
    }
}